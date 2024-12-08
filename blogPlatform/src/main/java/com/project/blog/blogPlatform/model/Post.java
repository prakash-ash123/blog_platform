package com.project.blog.blogPlatform.model;


import jakarta.persistence.*;


import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Entity

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;
    private Date createdAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    private int likes;
    private int dislikes;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reaction> reactions;

    
    
    
    public Post() {}
	public Post(String title, String content, User user) {
		super();
		
		this.title = title;
		this.content = content;
		this.createdAt = new Date();
		this.user = user;
		this.comments = new ArrayList<>();
		this.reactions = new ArrayList<>();
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long id) {
		this.postId = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public void setLikeCount() {
		this.likes +=1;
	}
	public void setDislikeCount() {
		this.dislikes += 1;
	}
	public void decrementDislikeCount() {
		this.dislikes -=1;
		
	}
	public void decrementLikeCount() {
		this.likes -=1;
		
	}
	








}

