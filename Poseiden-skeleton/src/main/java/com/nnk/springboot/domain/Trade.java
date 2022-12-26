package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "TradeId")
    private Integer tradeId;
    
    @NotBlank(message = "Account is mandatory")
    private String account;
    
    @NotBlank(message = "Type is mandatory")
    private String type;
    
    private double buyQuantity;
	
    private double sellQuantity;
	
    private double buyPrice;
	
    private double sellPrice;
    
    private Timestamp tradeDate;
    
    private String security;
    
    // 10 characters max
    private String status;
    
    private String trader;
    
    private String benchmark;
    
    private String book;
    
    private String creationName;
    
    private Timestamp creationDate;
    
    private String revisionName;
    
    private Timestamp revisionDate;
    
    private String dealName;
    
    private String dealType;
    
    private String sourceListId;
    
    private String side;

	public Trade(@NotBlank(message = "Account is mandatory") String account,
			@NotBlank(message = "Type is mandatory") String type) {
		super();
		this.account = account;
		this.type = type;
	}
    
    
    
}
