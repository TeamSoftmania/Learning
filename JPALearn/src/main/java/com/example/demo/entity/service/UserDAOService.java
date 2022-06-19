package com.example.demo.entity.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.AppUser;

@Repository
@Transactional
public class UserDAOService {
	@PersistenceContext
	private EntityManager manager;
	
	public long insertUser(AppUser user) {
		manager.persist(user);
		return user.getId();
	}
}
