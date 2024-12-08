package com.project.blog.blogPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.blog.blogPlatform.model.User;
import com.project.blog.blogPlatform.repository.UserRepository;

@Service
	public class UserService {

	    @Autowired
	    private UserRepository userRepository;
	    
	  

	    // Save new user
	    public void saveUser(User user) {
	        userRepository.save(user);
	    }

	    // Login user
	    public User loginUser(String email, String password) {
	        return userRepository.findByEmailAndPassword(email, password);
	    }
	    
	    public User authenticateUser(String email, String password) {
	        User user = userRepository.findByEmail(email); // Fetch user by email
	        if (user != null && password.equals(user.getPassword())) { // Directly compare plain-text passwords
	            return user; // Authentication successful
	        }
	        return null; // Authentication failed
	    }
 // Authentication failed
	    }
	
