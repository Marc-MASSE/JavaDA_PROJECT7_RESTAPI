package com.nnk.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.IRatingService;

public class RatingServiceImpl implements IRatingService {
	
	private RatingRepository ratingRepository;
	
	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

}
