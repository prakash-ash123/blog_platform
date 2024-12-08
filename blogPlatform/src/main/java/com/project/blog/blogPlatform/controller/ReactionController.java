package com.project.blog.blogPlatform.controller;

import com.project.blog.blogPlatform.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/like/{postId}")
    public String likePost(@PathVariable Long postId, @RequestParam Long userId) {
   
        reactionService.likePost(postId, userId);
        return "redirect:/";
         }
    

    @PostMapping("/dislike/{postId}")
    public String dislikePost(@PathVariable Long postId, @RequestParam Long userId) {
    	
    	
        reactionService.dislikePost(postId, userId);
        return "redirect:/";
         }
   
}

