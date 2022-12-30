package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    private String name;
	
    private String description;
	
    private String json;
	
    private String template;
	
    @Column(name = "sql_str")
    private String sqlStr;
	
    @Column(name = "sql_part")
    private String sqlPart;

	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		super();
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}
    
}
