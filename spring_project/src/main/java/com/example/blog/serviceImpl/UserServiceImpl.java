package com.example.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payLoads.UserDto;
import com.example.blog.repo.UserRep;
import com.example.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRep userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.userDtoToUser(userDto);
		User createUser = this.userRepo.save(user);
		return this.userToUserDto(createUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("User"," id ",userId));
		
		User u = this.userDtoToUser(userDto);
		user.setAbout(u.getAbout());
		user.setEmail(u.getEmail());
		user.setName(u.getName());
		user.setPassword(u.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		
		return this.userToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub	
		this.userRepo.deleteById(userId);
	}

	@Override
	public UserDto getUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("User"," id ",userId));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return userDtos;
	}
	
	private User userDtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	private UserDto userToUserDto(User user) {
		UserDto UserDto = new UserDto();
		UserDto.setId(user.getId());
		UserDto.setName(user.getName());
		UserDto.setEmail(user.getEmail());
		UserDto.setAbout(user.getAbout());
		UserDto.setPassword(user.getPassword());
		return UserDto;
	}
	
}