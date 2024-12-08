package com.project.blog.blogPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.blog.blogPlatform.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	
	@Query("SELECT c FROM Comment c WHERE c.post.postId = :postId")
	List<Comment> findByPostId(@Param("postId") Long postId);

}

