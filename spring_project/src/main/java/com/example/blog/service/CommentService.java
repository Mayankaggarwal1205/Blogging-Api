package com.example.blog.service;

import java.util.Set;

import com.example.blog.payLoads.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto, Integer postId);
	
	public CommentDto updateComment(CommentDto commentDto,Integer commentId);
	
	public void deleteComment(Integer commentId);
	
	public CommentDto getComment(Integer commentId);
	
	public Set<CommentDto> getAllComment();

}
