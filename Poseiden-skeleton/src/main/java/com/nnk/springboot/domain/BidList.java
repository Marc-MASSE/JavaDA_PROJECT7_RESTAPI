package com.nnk.springboot.domain;

//import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "bid_list_id")
    private Integer bidListId;
    
    @NotBlank(message = "Account is mandatory")
    private String account;
	
    @NotBlank(message = "Type is mandatory")
    private String type;
    
    @Column(name = "bid_quantity")
    private Double bidQuantity;
	
    @Column(name = "ask_quantity")
    private Double askQuantity;
	
    private Double bid;
	
    private Double ask;
	
    private String benchmark;
	
    @Column(name = "bid_list_date")
    private Timestamp bidListDate;
	
    private String commentary;
	
    private String security;
	
    private String status;
	
    private String trader;
	
    private String book;
	
    @Column(name = "creation_name")
    private String creationName;
	
    @Column(name = "creation_date")
    private Timestamp creationDate;
	
    @Column(name = "revision_name")
    private String revisionName;
	
    @Column(name = "revision_date")
    private Timestamp revisionDate;
	
    @Column(name = "deal_name")
    private String dealName;
	
    @Column(name = "deal_type")
    private String dealType;
	
    @Column(name = "source_list_id")
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
