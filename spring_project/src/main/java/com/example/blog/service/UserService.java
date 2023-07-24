package com.example.blog.service;

import java.util.List;

import com.example.blog.entity.User;
import com.example.blog.payLoads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	void deleteUser(Integer userId);
	
	UserDto getUser(Integer userId);
	
	List<UserDto> getAllUser();

}
