package com.example.demo.post.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.post.bean.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
