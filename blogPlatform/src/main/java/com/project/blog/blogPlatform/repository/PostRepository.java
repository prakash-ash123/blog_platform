package com.project.blog.blogPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.blog.blogPlatform.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	
	 @Query("SELECT p FROM Post p WHERE p.id = :postId")
	    Post findPostById(@Param("postId") Long postId);
}
