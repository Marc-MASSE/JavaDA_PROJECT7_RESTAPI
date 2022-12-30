package com.nnk.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.IRatingService;

@Service
public class RatingServiceImpl implements IRatingService {
	
	private RatingRepository ratingRepository;
	
	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

}
