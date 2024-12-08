package com.project.blog.blogPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.blogPlatform.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByIdIs(Long id);
	 User findByEmailAndPassword(String email, String password);
	 User findByEmail(String email);
}

