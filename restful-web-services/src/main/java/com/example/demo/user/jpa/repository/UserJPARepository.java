package com.example.demo.user.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.bean.User;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer> {

}
