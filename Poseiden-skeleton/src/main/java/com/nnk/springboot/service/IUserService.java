package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.DTO.UserDTO;
import com.nnk.springboot.domain.User;

public interface IUserService {
	
    User addUser(UserDTO userDTO);

    List<UserDTO> getUsers();

    UserDTO getUserById(Integer id);

    User updateUser(Integer id, UserDTO userDTO);

    void deleteUser(Integer id);

}
