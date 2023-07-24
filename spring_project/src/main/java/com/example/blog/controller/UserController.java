package com.example.blog.controller;

import java.util.List;
import java.util.Map;

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

import com.example.blog.payLoads.ApiResponse;
import com.example.blog.payLoads.UserDto;
import com.example.blog.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	// create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	//update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.ACCEPTED);	
	}
	
	// delete user
	@DeleteMapping("{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uId){
		this.userService.deleteUser(uId);
		return new ResponseEntity(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
	}
	
	// get - get user
    @GetMapping("/{userId}")
//    public UserDto getUser(@PathVariable("userId") Integer userId){
//        return this.userService.getUserById(userId);
//    }

    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(this.userService.getUser(userId));
    }


    // get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }
	
}
