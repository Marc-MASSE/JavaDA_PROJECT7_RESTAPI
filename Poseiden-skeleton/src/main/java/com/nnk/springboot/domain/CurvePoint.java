package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;
    
    @Column(name = "CurveId")
    private Integer curveId;
    
    private Timestamp asOfDate;
    
    private double term;
    
    private double value;
    
    private Timestamp creationDate;

	public CurvePoint(Integer curveId, double term, double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}
    
}
