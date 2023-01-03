package com.nnk.springboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.DTO.RatingDTO;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.IRatingService;

@Service
public class RatingServiceImpl implements IRatingService {
	
	private RatingRepository ratingRepository;
	
	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

	@Override
	public Rating addRating(RatingDTO ratingDTO) {
		Rating rating = Rating.builder()
				.moodysRating(ratingDTO.getMoodysRating())
				.sandPRating(ratingDTO.getSandPRating())
				.fitchRating(ratingDTO.getFitchRating())
				.orderNumber(ratingDTO.getOrderNumber())
				.build();
		return ratingRepository.save(rating);
	}

	@Override
	public List<RatingDTO> getRatings() {
		List<Rating> ratingList = ratingRepository.findAllByOrderByIdDesc();
		return ratingList.stream()
				.map(r -> ratingToDTOMapper(r))
				.collect(Collectors.toList());
	}

	@Override
	public RatingDTO getRatingById(Integer id) {
		if (ratingRepository.existsById(id)) {
			return ratingToDTOMapper(ratingRepository.getById(id));
		}
		return new RatingDTO();
	}

	@Override
	public Rating updateRating(Integer id, RatingDTO ratingDTO) {
		if (ratingRepository.existsById(id)) {
			Rating rating = ratingRepository.getById(id);
			rating.setMoodysRating(ratingDTO.getMoodysRating());
			rating.setSandPRating(ratingDTO.getSandPRating());
			rating.setFitchRating(ratingDTO.getFitchRating());
			rating.setOrderNumber(ratingDTO.getOrderNumber());
			return ratingRepository.save(rating);
		}
		return new Rating();
	}

	@Override
	public void deleteRating(Integer id) {
		if (ratingRepository.existsById(id)) {
			ratingRepository.deleteById(id);
		}
	}
	
	public RatingDTO ratingToDTOMapper(Rating rating) {
		return RatingDTO.builder()
				.id(rating.getId())
				.moodysRating(rating.getMoodysRating())
				.sandPRating(rating.getSandPRating())
				.fitchRating(rating.getFitchRating())
				.orderNumber(rating.getOrderNumber())
				.build();
	}

}
