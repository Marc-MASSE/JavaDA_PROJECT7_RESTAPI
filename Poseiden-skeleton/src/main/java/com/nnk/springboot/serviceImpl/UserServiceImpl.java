package com.nnk.springboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.DTO.UserDTO;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User addUser(UserDTO userDTO) {
		User user = User.builder()
				.fullname(userDTO.getFullname())
				.username(userDTO.getUsername())
				.role(userDTO.getRole())
				.password(passwordEncoder.encode(userDTO.getPassword()))
				.build();
		return userRepository.save(user);
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> userList = userRepository.findAllByOrderByIdDesc();
		return userList.stream()
				.map(u -> userToDTOMapper(u))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(Integer id) {
		if (userRepository.existsById(id)) {
			return userToDTOMapper(userRepository.getById(id));
		}
		return new UserDTO();
	}

	@Override
	public User updateUser(Integer id, UserDTO userDTO) {
		if (userRepository.existsById(id)) {
			User user = userRepository.getById(id);
			user.setFullname(userDTO.getFullname());
			user.setUsername(userDTO.getUsername());
			user.setRole(userDTO.getRole());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			return userRepository.save(user);
		}
		return new User();
	}

	@Override
	public void deleteUser(Integer id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
	}
	
	@Override
	public UserDTO userToDTOMapper(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.fullname(user.getFullname())
				.username(user.getUsername())
				.role(user.getRole())
				.password(user.getPassword())
				.build();
	}

}
