package com.example.blog.payLoads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.blog.entity.Category;
import com.example.blog.entity.Comment;
import com.example.blog.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	
	private Integer post_id;
	
	private String post_title;
	
	private String post_content;
	
	private String post_imageName;
	
	private Date dateTime;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comment = new HashSet<>();
	
	
	
	
	
	

	public Set<CommentDto> getComment() {
		return comment;
	}

	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getPost_imageName() {
		return post_imageName;
	}

	public void setPost_imageName(String post_imageName) {
		this.post_imageName = post_imageName;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public PostDto(Integer post_id, String post_title, String post_content, String post_imageName, Date dateTime,
			CategoryDto category, UserDto user, Set<CommentDto> comment) {
		super();
		this.post_id = post_id;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_imageName = post_imageName;
		this.dateTime = dateTime;
		this.category = category;
		this.user = user;
		this.comment = comment;
	}

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	

}
