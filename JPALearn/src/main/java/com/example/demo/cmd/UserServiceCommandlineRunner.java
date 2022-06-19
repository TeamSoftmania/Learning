package com.example.demo.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.service.UserDAOService;

@Component
public class UserServiceCommandlineRunner implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceCommandlineRunner.class);

	@Autowired
	private UserDAOService daosvc;
	
	@Override
	public void run(String... args) throws Exception {
		AppUser user = new AppUser("Jack", "Admin");
		long id = daosvc.insertUser(user);
		
		log.info("New User Crreated" + user);
	}

}
