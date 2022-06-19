package com.example.demo.user.jpa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.post.bean.Post;
import com.example.demo.post.jpa.repository.PostRepository;
import com.example.demo.user.bean.User;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.jpa.repository.UserJPARepository;
import com.example.demo.user.service.UserDAOService;

@RestController
public class UserJPAController {
	@Autowired
	private UserDAOService service;
	
	@Autowired
	private UserJPARepository repository;
	
	@Autowired
	private PostRepository postRepo;

	@GetMapping(path = "/jpa/users")
	public List<User> retriveAllUSers() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("ID : " + id);
		}
		
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUSers());
		model.add(link.withRel("All-Users"));
		return model;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retriveAllUserPost(@PathVariable int id) {
		Optional<User> userOb = repository.findById(id);
		if(userOb.isEmpty()) {
			throw new UserNotFoundException("ID : " + id);
		}
		return userOb.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> retriveAllUserPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOb = repository.findById(id);
		if(userOb.isEmpty()) {
			throw new UserNotFoundException("ID : " + id);
		}
		
		post.setUser(userOb.get());
		postRepo.save(post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
