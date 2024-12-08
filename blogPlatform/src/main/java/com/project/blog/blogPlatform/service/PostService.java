package com.project.blog.blogPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.blogPlatform.model.Post;
import com.project.blog.blogPlatform.repository.PostRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
	
	@Autowired
    private PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Iterable<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post createPost(Post post) {
    	
        post.setCreatedAt(new Date());
        return repository.save(post);
    }

    public void deletePost(Long id) {
        repository.deleteById(id);
    }

	public Optional<Post> findById(Long id) {
		Optional<Post> post = repository.findById(id);
		return post;
	}
	
	public Post getPostById(Long postId) {
		return repository.findPostById(postId);
	}
	
	public void savePost(Post post) {
        repository.save(post);
    }
	
	 public void likePost(Long postId) {
	        Post post = repository.findPostById(postId);
	                
	        post.setLikes(post.getLikes() + 1);
	        repository.save(post);
	    }

	    public void dislikePost(Long postId) {
	        Post post = repository.findPostById(postId);
	                
	        post.setDislikes(post.getDislikes() + 1);
	        repository.save(post);
	    }
}

