package com.nnk.springboot.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleNameDTO {
	
    private Integer id;
    
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String name;
	
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String description;
	
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String json;
	
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String template;
	
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String sqlStr;
	
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String sqlPart;

}
