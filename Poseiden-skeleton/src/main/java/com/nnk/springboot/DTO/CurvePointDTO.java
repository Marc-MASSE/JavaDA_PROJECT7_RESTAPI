package com.nnk.springboot.DTO;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurvePointDTO {
	
    private Integer id;
    
    @NotNull(message = "Must not be null")
    private Integer curveId;
    
    private Double term;
    
    private Double value;

}
