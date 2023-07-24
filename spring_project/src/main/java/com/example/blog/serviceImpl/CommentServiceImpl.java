package com.example.blog.serviceImpl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payLoads.CommentDto;
import com.example.blog.repo.CommentRepo;
import com.example.blog.repo.PostRepo;
import com.example.blog.service.CommentService;

import jakarta.persistence.PostRemove;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		comment.setPost(post);
		Comment createdComment = this.commentRepo.save(comment);
		return this.modelMapper.map(createdComment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
		this.commentRepo.delete(comment);
	}

	@Override
	public CommentDto getComment(Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CommentDto> getAllComment() {
		// TODO Auto-generated method stub
		return null;
	}

}
