package com.example.blog.payLoads;

import com.example.blog.entity.Post;
import com.example.blog.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

public class CommentDto {

	private int comment_id;
	
	private String content;
	
//	private PostDto post;


	
	
	
	
	

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

//	public PostDto getPost() {
//		return post;
//	}
//
//	public void setPost(PostDto post) {
//		this.post = post;
//	}


	public CommentDto(int comment_id, String content) {
		super();
		this.comment_id = comment_id;
		this.content = content;
//		this.post = post;
	}

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
