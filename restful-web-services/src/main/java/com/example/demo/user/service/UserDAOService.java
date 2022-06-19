package com.example.demo.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.user.bean.User;

@Component
public class UserDAOService {
	
	private static ArrayList<User> users = new ArrayList<>();
	private static int count = 3;
	
	static {
		users.add(new User(1, "Samridhh", new Date()));
		users.add(new User(2, "Babli", new Date()));
		users.add(new User(3, "Sushil", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++count);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> userIterator = users.iterator();
		while(userIterator.hasNext()) {
			User user = userIterator.next();
			if(user.getId() == id) {
				userIterator.remove();
				return user;
			}
		}
		return null;
	}
}
