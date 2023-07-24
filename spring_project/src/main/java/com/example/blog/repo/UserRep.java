package com.example.blog.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.User;

public interface UserRep extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
//	Optional<User> findByUsernameOrEmail(String username, String email);
//
//    Optional<User> findByUsername(String username);
//
//    Boolean existsByUsername(String username);
//
//    Boolean existsByEmail(String email);
	
}