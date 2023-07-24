package com.example.blog.service;

import java.util.List;

import com.example.blog.payLoads.PostDto;

public interface PostService {
	
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	public PostDto updatePost(PostDto postDto, Integer postId);
	
	public void deletePost(Integer postId);
	
	public PostDto getPost(Integer postId);
	
	public List<PostDto> getAllPost();
	
//	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	
	public List<PostDto> getAllPostByCategory(Integer categoryId);
	
	public List<PostDto> getAllPostByUser(Integer userId);
	
	public List<PostDto> searchPost(String keyword);

}
