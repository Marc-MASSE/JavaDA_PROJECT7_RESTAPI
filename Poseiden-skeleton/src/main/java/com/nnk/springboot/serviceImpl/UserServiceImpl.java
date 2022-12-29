package com.nnk.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	

}
