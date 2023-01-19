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
public class BidListDTO {
	
    private Integer bidListId;

    @NotBlank(message = "Account is mandatory")
    @Size(max = 30, message = "30 characters maximum are allowed")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 30, message = "30 characters maximum are allowed")
    private String type;

    private Double bidQuantity;

}
