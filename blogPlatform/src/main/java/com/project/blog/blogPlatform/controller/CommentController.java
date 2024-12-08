package com.project.blog.blogPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.blog.blogPlatform.model.Comment;
import com.project.blog.blogPlatform.model.Post;
import com.project.blog.blogPlatform.model.User;
import com.project.blog.blogPlatform.repository.UserRepository;
import com.project.blog.blogPlatform.service.CommentService;
import com.project.blog.blogPlatform.service.PostService;

import java.util.List;

@Controller
@RequestMapping("post/comments")
public class CommentController {
		
	
	 @Autowired
	 private PostService postService; 
	 
	 @Autowired
	 private UserRepository userRepository;
	 
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    // Get all comments for a specific post
    @GetMapping("/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return service.getCommentsByPostId(postId);
    }

    // Add a new comment
    
    @PostMapping("/{postId}")
    public String addComment(@PathVariable Long postId,@RequestParam Long userId, @ModelAttribute Comment comment, Model model) {
        // Log the received comment
        System.out.println("Received Comment: " + comment);

        Post post = postService.getPostById(postId);
        System.out.println("Received post: " + post);
        comment.setPost(post);
        
        User user = userRepository.findByIdIs(userId);
       comment.setUser(user);

        // Save the comment using the service
        service.addComment(comment);
        model.addAttribute("post", post);
        return "redirect:/";
    }

//    @PostMapping("/{postId}")
//    public String addComment(@ModelAttribute Comment comment) {
//    	System.out.println("Received Comment: " + comment);
//        Comment savedComment = service.addComment(comment);
//        ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
//        return "redirect: index";
//    }


    // Delete a comment by its ID
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        if (service.commentExists(id)) {
            service.deleteComment(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
