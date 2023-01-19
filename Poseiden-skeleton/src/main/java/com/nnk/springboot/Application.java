package com.nnk.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.IBidListService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private IBidListService bidListService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("-------------------------------------------");
		
		/*
		BidListDTO bidListDTO = BidListDTO.builder()
				.account("104")
				.type("Type4")
				.bidQuantity(4.0)
				.build();
		
		BidList bidList = bidListService.addBidList(bidListDTO);

		System.out.println(bidList);
		*/
		
		System.out.println("-------------------------------------------");

	}
}
