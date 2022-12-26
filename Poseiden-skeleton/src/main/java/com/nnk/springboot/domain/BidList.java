package com.nnk.springboot.domain;

//import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "BidListId")
    private Integer bidListId;
    
    @NotBlank(message = "Account is mandatory")
    private String account;
	
    @NotBlank(message = "Type is mandatory")
    private String type;
    
    private double bidQuantity;
	
    private double askQuantity;
	
    private double bid;
	
    private double ask;
	
    private String benchmark;
	
    private Timestamp bidListDate;
	
    private String commentary;
	
    private String security;
	
    private String status;
	
    private String trader;
	
    private String book;
	
    private String creationName;
	
    private Timestamp creationDate;
	
    private String revisionName;
	
    private Timestamp revisionDate;
	
    private String dealName;
	
    private String dealType;
	
    private String sourceListId;
	
    private String side;

	public BidList(@NotBlank(message = "Account is mandatory") String account,
			@NotBlank(message = "Type is mandatory") String type, double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}
	
}
