package com.indoorpositioning.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.indoorpositioning.persistence.User;
import com.indoorpositioning.persistence.UserRepository;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initDatabase() {
    	
    	//userRepository.save(new User("user", "pass", "ROLE_USER"));
		//userRepository.save(new User("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN"));
    }

}
