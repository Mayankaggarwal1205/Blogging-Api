package com.example.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

//    List<Comment> findByPostId(long postId);

}
