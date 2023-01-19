package com.nnk.springboot.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
    private Integer id;
    
    @NotBlank(message = "Username is mandatory")
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String username;
    
    @NotBlank(message = "Password is mandatory")
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String password;
    
    @NotBlank(message = "FullName is mandatory")
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String fullname;
    
    @NotBlank(message = "Role is mandatory")
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String role;

}
