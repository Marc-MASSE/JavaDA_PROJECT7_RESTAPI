package com.nnk.springboot.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Size(min = 8, message = "8 characters minimum are allowed")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,125}$", 
    	message = "Password must contain at least 1 uppercase, 1 special character and 1 digit.")
    private String password;
    
    @NotBlank(message = "FullName is mandatory")
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String fullname;
    
    @NotBlank(message = "Role is mandatory")
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String role;

}
