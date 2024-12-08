package com.project.blog.blogPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.blog.blogPlatform.model.Post;
import com.project.blog.blogPlatform.model.Reaction;
import com.project.blog.blogPlatform.model.User;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
	Reaction findByPostAndUser(Post post, User user);


}
