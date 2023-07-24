package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.payLoads.ApiResponse;
import com.example.blog.payLoads.PostDto;
import com.example.blog.service.PostService;

@Controller
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	// create post
	@PostMapping("user/{userId}/category/{categoryId}/post/")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId, 
			@PathVariable Integer categoryId){
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	// update post
	@PutMapping("post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto p = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(p,HttpStatus.OK);
	}
	
	// delete post
	@DeleteMapping("post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>( new ApiResponse("deleted post successfully",true), HttpStatus.OK );
	}
	
	// get post by id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto post = this.postService.getPost(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	// get all posts
	@GetMapping("/post/")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> post = this.postService.getAllPost();
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	
	// get all posts using pageable concept
//	@GetMapping("/post")
//	public ResponseEntity<List<PostDto>> getAllPosts(
//			@RequestParam(value="pageNumber",defaultValue="1",required=false) Integer pageNumber,
//			@RequestParam(value="pageSize",defaultValue="5",required=false) Integer pageSize
//			){
//		List<PostDto> post = this.postService.getAllPost(pageNumber,pageSize);
//		return new ResponseEntity<>(post,HttpStatus.OK);
//	}
	
	// get by user
	@GetMapping("/user/{userId}/post/")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getAllPostByUser(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	// get by category
	@GetMapping("/category/{categoryId}/post/")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	// search
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword")  String keyword){
		List<PostDto> posts = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
}
