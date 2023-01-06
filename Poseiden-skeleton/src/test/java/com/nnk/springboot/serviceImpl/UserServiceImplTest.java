package com.nnk.springboot.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.DTO.UserDTO;
import com.nnk.springboot.configuration.SpringSecurityConfig;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.IUserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	private IUserService userService;
	
    //@Autowired
    //private PasswordEncoder passwordEncoder;
	
	@Mock
	private UserRepository userRepository;
	@Mock
    private PasswordEncoder passwordEncoder;
	
	private User user1;
	private User user2;
	private UserDTO userDTO1;
	private UserDTO userDTO2;
	
	@Captor
	ArgumentCaptor<User> userCaptor;
	@Captor
	ArgumentCaptor<Integer> idCaptor;
	
	@BeforeEach
	public void init() {
		userService = new UserServiceImpl(userRepository,passwordEncoder);
		user1 = User.builder()
				.id(1)
				.username("Usuel1")
				.password("MdP1")
				.fullname("Complet1")
				.role("USER")
				.build();
		user2 = User.builder()
				.id(2)
				.username("Usuel2")
				.password("MdP2")
				.fullname("Complet2")
				.role("USER")
				.build();
		userDTO1 = UserDTO.builder()
				.id(1)
				.username("Usuel1")
				.password("MdP1")
				.fullname("Complet1")
				.role("USER")
				.build();
		userDTO2 = UserDTO.builder()
				.id(2)
				.username("Usuel2")
				.password("MdP2")
				.fullname("Complet2")
				.role("USER")
				.build();
	}
	
	// Test addUser
	@Test
	public void addUser_success() {
		UserDTO userDTO = UserDTO.builder()
				.username("Usuel")
				.password("MdP")
				.fullname("Complet")
				.role("USER")
				.build();
		User user = User.builder()
				.username("Usuel")
				.password("MdPCodé")
				.fullname("Complet")
				.role("USER")
				.build();
		when(userRepository.save(any(User.class)))
			.thenReturn(user);
		when(passwordEncoder.encode("MdP"))
			.thenReturn("MdPCodé");
		userService.addUser(userDTO);
		verify(userRepository).save(userCaptor.capture());
		assertThat(userCaptor.getValue()).isEqualTo(user);
		verify(userRepository).save(user);
		verify(passwordEncoder).encode("MdP");
	}
	
	// Test getUsers
	@Test
	public void getUsers_success() {
		when(userRepository.findAllByOrderByIdDesc())
			.thenReturn(Arrays.asList(user2,user1));
		assertThat(userService.getUsers())
			.contains(userDTO1)
			.contains(userDTO2);
		verify(userRepository).findAllByOrderByIdDesc();
	}
	
	// Test getUserById
	@Nested
	class GetUserById {
		@Test
		public void success() {
			when(userRepository.existsById(1))
				.thenReturn(true);
			when(userRepository.getById(1))
				.thenReturn(user1);
			assertThat(userService.getUserById(1)
				.equals(userDTO1));
			verify(userRepository).existsById(1);
			verify(userRepository).getById(1);
		}
		@Test
		public void no_user() {
			when(userRepository.existsById(15))
				.thenReturn(false);
			assertThat(userService.getUserById(15))
				.isEqualTo(new UserDTO());
			verify(userRepository).existsById(15);
		}
	}
	
	// Test updateUser
	@Nested
	class UpdateUserById {
		@Test
		public void success() {
			UserDTO userDTOToUpdate = UserDTO.builder()
					.username("Usuel")
					.password("MdPàCoder")
					.fullname("Complet")
					.role("USER")
					.build();
			User user1Updated = User.builder()
					.id(1)
					.username("Usuel")
					.password("MdP")
					.fullname("Complet")
					.role("USER")
					.build();
			when(userRepository.existsById(1))
				.thenReturn(true);
			when(userRepository.getById(1))
				.thenReturn(user1);
			when(passwordEncoder.encode("MdPàCoder"))
				.thenReturn("MdP");
			when(userRepository.save(user1Updated))
				.thenReturn(user1Updated);
			assertThat(userService.updateUser(1,userDTOToUpdate)
				.equals(user1Updated));
			verify(userRepository).existsById(1);
			verify(userRepository).getById(1);
			verify(passwordEncoder).encode("MdPàCoder");
			verify(userRepository).save(user1Updated);
		}
		@Test
		public void no_user() {
			UserDTO userDTOToUpdate = UserDTO.builder()
					.username("Usuel")
					.password("MdPàCoder")
					.fullname("Complet")
					.role("USER")
					.build();
			when(userRepository.existsById(15))
				.thenReturn(false);
			assertThat(userService.updateUser(15,userDTOToUpdate))
				.isEqualTo(new User());
			verify(userRepository).existsById(15);
		}
	}
	
	// Test deleteUser
	@Nested
	class DeleteUserById {
		@Test
		public void success() {
			when(userRepository.existsById(1))
				.thenReturn(true);
			userService.deleteUser(1);
			verify(userRepository).existsById(1);
			verify(userRepository).deleteById(idCaptor.capture());
			assertThat(idCaptor.getValue()).isEqualTo(1);
			verify(userRepository).deleteById(1);
		}
		@Test
		public void no_user() {
			when(userRepository.existsById(15))
				.thenReturn(false);
			userService.deleteUser(15);
			verify(userRepository).existsById(15);
		}
	}
	
	// Test userToDTOMapper
	@Test
	public void mapper_success() {
		assertThat(userService.userToDTOMapper(user1))
			.isEqualTo(userDTO1);
	}

}
