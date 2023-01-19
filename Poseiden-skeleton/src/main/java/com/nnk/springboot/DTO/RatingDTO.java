package com.nnk.springboot.DTO;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
	
    private Integer id;
    
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String moodysRating;
    
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String sandPRating;
	
    @Size(max = 125, message = "125 characters maximum are allowed")
    private String fitchRating;
	
    private Integer orderNumber;

}
