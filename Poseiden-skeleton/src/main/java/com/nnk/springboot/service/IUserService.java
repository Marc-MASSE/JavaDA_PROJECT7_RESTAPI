package com.nnk.springboot.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nnk.springboot.DTO.UserDTO;
import com.nnk.springboot.domain.User;

// "extends UserDetailsService" to use DaoAuthenticationProvider in SpringSecurityConfig
public interface IUserService extends UserDetailsService {
	
    User addUser(UserDTO userDTO);

    List<UserDTO> getUsers();

    UserDTO getUserById(Integer id);

    User updateUser(Integer id, UserDTO userDTO);

    void deleteUser(Integer id);

	UserDTO userToDTOMapper(User user);

	User getConnectedUser();

}
