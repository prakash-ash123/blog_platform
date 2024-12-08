package com.project.blog.blogPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.blogPlatform.model.Post;
import com.project.blog.blogPlatform.model.Reaction;
import com.project.blog.blogPlatform.model.User;
import com.project.blog.blogPlatform.repository.PostRepository;
import com.project.blog.blogPlatform.repository.ReactionRepository;
import com.project.blog.blogPlatform.repository.UserRepository;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

	@Autowired
    private UserRepository userRepository;
	
	 @Autowired
	    private PostRepository postRepository;


	    public void likePost(Long postId, Long userId) {
	        Post post = postRepository.findById(postId)
	                                   .orElseThrow(() -> new RuntimeException("Post not found"));
	        User user = userRepository.findByIdIs(userId);
	        Reaction existingReaction = reactionRepository.findByPostAndUser(post, user);
	        
	        if (existingReaction == null) {
	            Reaction reaction = new Reaction(user, post, true);
	            reactionRepository.save(reaction);
	            post.setLikeCount(); // Increment likes
	        } else if (!existingReaction.isLike()) {
	            // Change from dislike to like
	            existingReaction.setLike(true);
	            reactionRepository.save(existingReaction);
	            post.setLikeCount(); // Increment likes
	            post.decrementDislikeCount(); // Decrement dislikes
	        }

	        postRepository.save(post);
	    }

	    public void dislikePost(Long postId, Long userId) {
	        Post post = postRepository.findById(postId)
	                                   .orElseThrow(() -> new RuntimeException("Post not found"));
	        User user = userRepository.findByIdIs(userId);
	        Reaction existingReaction = reactionRepository.findByPostAndUser(post, user);
	        
	        if (existingReaction == null) {
	            Reaction reaction = new Reaction(user, post, false);
	            reactionRepository.save(reaction);
	            post.setDislikeCount(); // Increment dislikes
	        } else if (existingReaction.isLike()) {
	            // Change from like to dislike
	            existingReaction.setLike(false);
	            reactionRepository.save(existingReaction);
	            post.setDislikeCount(); // Increment dislikes
	           post.decrementLikeCount(); // Decrement likes
	        }

	        postRepository.save(post);
	    }
	}