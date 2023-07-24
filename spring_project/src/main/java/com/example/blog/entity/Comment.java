package com.example.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int comment_id;
	
	@Column(nullable = false,length=1000)
	private String content;
	
	@ManyToOne
	private Post post;
	
	
	
	
	
	
	

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}


	public Comment(int comment_id, String content, Post post) {
		super();
		this.comment_id = comment_id;
		this.content = content;
		this.post = post;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
