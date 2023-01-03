package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.DTO.RatingDTO;
import com.nnk.springboot.domain.Rating;

public interface IRatingService {
	
    Rating addRating(RatingDTO ratingDTO);

    List<RatingDTO> getRatings();

    RatingDTO getRatingById(Integer id);

    Rating updateRating(Integer id, RatingDTO ratingDTO);

    void deleteRating(Integer id);

}
