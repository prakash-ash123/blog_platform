package com.project.blog.blogPlatform.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.blog.blogPlatform.model.Post;
import com.project.blog.blogPlatform.model.User;
import com.project.blog.blogPlatform.service.PostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("posts", service.getAllPosts());
        return "index"; // Renders /WEB-INF/views/index.jsp
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Optional<Post> post = service.findById(id);
        model.addAttribute("post", post);
        if (post.isPresent()) {
            model.addAttribute("post", post.get()); // Unwrap the Optional and add it to the model
            System.out.println(post.get().getContent()); // Access the content after unwrapping
        } else {
            // Handle the case where the post does not exist
            System.out.println("Post not found with id: " + id);
            model.addAttribute("error", "Post not found with id: " + id);
            return "error"; // Render an error page (e.g., error.jsp)
        }
        return "comment"; // Renders /WEB-INF/views/post.jsp
    }

    
    @GetMapping("/new-post")
    public String showNewPostForm(HttpSession session, Model model) {
        // Retrieve the logged-in user from the session
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            model.addAttribute("error", "You need to log in to create a post.");
            return "login"; // Redirect to the login page if not logged in
        }
        model.addAttribute("loggedInUser", loggedInUser);
        // Allow access to the new post page
        return "new-post"; // This should map to your JSP or view for creating a new post
    }


        // Handle post creation
        @PostMapping("/posts")
        public String createPost(@RequestParam String title, @RequestParam String content, HttpSession session, Model model) {
            User user = (User) session.getAttribute("loggedInUser");  // Get the logged-in user from session
            
            if (user == null) {
                model.addAttribute("error", "You need to log in to create a post.");
                return "login"; // Redirect to login page if no user is logged in
  
            } else {
            	Post post = new Post(title, content, user);  // Create new post associated with the user
            	
            	System.out.println("Saved Post ID: " + post.getPostId());
                service.savePost(post);  // Save the post
                return "redirect:/";  // Redirect to home page after post creation
        }
     }
}

//    @PostMapping("/posts")
//    public String createPost(@ModelAttribute Post post) {
//        service.createPost(post);
//        return "redirect:/"; // Redirect to homepage after creating a post
//    }

