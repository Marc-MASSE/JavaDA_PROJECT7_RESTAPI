package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotBlank(message = "Username is mandatory")
    private String username;
    
    @NotBlank(message = "Password is mandatory")
    private String password;
    
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    
    @NotBlank(message = "Role is mandatory")
    private String role;

}
