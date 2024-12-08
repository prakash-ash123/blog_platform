package com.project.blog.blogPlatform.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.project.blog.blogPlatform.model.User;
import com.project.blog.blogPlatform.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
	public class UserController {

	    @Autowired
	    private UserService userService;
	    
	    @GetMapping("/register")
	    public String getRegister() {
	    	return "register";
	    }

	    @RequestMapping("/register")
	    public String registerUser(
	            @RequestParam String username,
	            @RequestParam String email,
	            @RequestParam String password,
	            @RequestParam("profilePicture") MultipartFile profilePicture,
	            Model model) {
	        User user = new User(username, email, password);

	        try {
	            // Save profile picture
	            if (!profilePicture.isEmpty()) {
	                String uploadDir = "src/main/resources/static/uploads/";
	                Path uploadPath = Paths.get(uploadDir);
	                if (!Files.exists(uploadPath)) {
	                    Files.createDirectories(uploadPath);
	                }

	                String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(profilePicture.getOriginalFilename());
	                Path filePath = uploadPath.resolve(fileName);
	                Files.copy(profilePicture.getInputStream(), filePath);

	                user.setProfilePictureUrl("/uploads/" + fileName);
	            }

	          
	            // Save user
	            userService.saveUser(user);

	            model.addAttribute("success", "Registration successful! You can now log in.");
	            return "login";
	        } catch (DataIntegrityViolationException e) {
	            // Handle specific database constraint violations (e.g., duplicate email or username)
	            model.addAttribute("error", "Email or username already exists. Please use a different one.");
	            return "register";
	        } catch (IOException e) {
	            // Handle file-related exceptions
	            model.addAttribute("error", "Failed to save profile picture. Please try again.");
	            return "register";
	        }
	    }


	    
	    @GetMapping("/login")
	    public String getLogin() {
	    	return "login";
	    }
	    // Login User
	    
	    @PostMapping("/login")
	    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
	        User user = userService.authenticateUser(email, password);
	        if (user != null) {
	            session.setAttribute("loggedInUser", user);
	            return "redirect:/";
	        } else {
	        	model.addAttribute("error", "Invalid email or password. Please try again.");
	            return "login";
	        }
	    }

//	    @RequestMapping("/login")
//	    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
//	        User user = userService.loginUser(email, password);
//	        
//	        System.out.println(user);
//	        if (user != null) {
//	            session.setAttribute("user", user);  // Set user in session
//	            return "redirect:/";  // Redirect to homepage after login
//	        } else
//	        return "register";  // Redirect to login with error
//	    }
	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/";
	    }

	    
	    
	}

