package com.example.blog.serviceImpl;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payLoads.PostDto;
import com.example.blog.repo.CategoryRepo;
import com.example.blog.repo.PostRepo;
import com.example.blog.repo.UserRep;
import com.example.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRep userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		Post post = this.modelMapper.map(postDto, Post.class);
        post.setDateTime(new Date());

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        post.setUser(user);

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        post.setCategory(category);

        Post createdPost = this.postRepo.save(post);

        return this.modelMapper.map(createdPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post p = this.postRepo.findById(postId)
				.orElseThrow( ()-> new ResourceNotFoundException("Post", "id", postId));
		p.setPost_content(postDto.getPost_content());
		p.setPost_imageName(postDto.getPost_imageName());
		p.setDateTime(new Date());
		Post updatedPost = this.postRepo.save(p);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post p = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		this.postRepo.delete(p);
	}

	@Override
	public PostDto getPost(Integer postId) {
		// TODO Auto-generated method stub
		Post p = this.postRepo.findById(postId).
				orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		return this.modelMapper.map(p, PostDto.class);
	}

	@Override
//	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
//		// TODO Auto-generated method stub
//		
//		Pageable p = (Pageable) PageRequest.of(pageNumber, pageSize);
//		Page<Post> pagePost = (Page<Post>) this.postRepo.findAll((Sort) p);
//		List<Post> allPosts = pagePost.getContent();
//		
//		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//		return postDtos;
//		
////		List<Post> posts = this.postRepo.findAll();
////		List<PostDto> posts1 = posts1.stream().map( (post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
////		return posts1;
//	}
	
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		
		List<Post> posts = this.postRepo.findAll();
		List<PostDto> posts1 = posts.stream().map( (post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return posts1;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> allPosts = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPosts;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> allPosts = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPosts;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> allPosts = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPosts;
	}
	
	

}
