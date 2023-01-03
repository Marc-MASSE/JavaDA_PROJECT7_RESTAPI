package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import com.nnk.springboot.DTO.CurvePointDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "curve_id")
    private Integer curveId;
    
    @Column(name = "as_of_date")
    private Timestamp asOfDate;
    
    private Double term;
    
    private Double value;
    
    @Column(name = "creation_date")
    private Timestamp creationDate;

	public CurvePoint(Integer curveId, double term, double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}
    
}
