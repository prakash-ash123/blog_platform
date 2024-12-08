package com.project.blog.blogPlatform.service;

import org.springframework.stereotype.Service;

import com.project.blog.blogPlatform.model.Comment;
import com.project.blog.blogPlatform.repository.CommentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> getCommentsByPostId(Long post_Id) {
        return repository.findByPostId(post_Id);
    }

    public Comment addComment(Comment comment) {
        comment.setCreatedAt(new Date());
        return repository.save(comment);
    }

    public void deleteComment(Long id) {
        repository.deleteById(id);
    }

	public boolean commentExists(Long id) {
		repository.existsById(id);
		return false;
	}
}

