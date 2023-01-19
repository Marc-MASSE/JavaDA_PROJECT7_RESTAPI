package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RatingRepositoryTest {

	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void ratingTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		rating = ratingRepository.save(rating);
		assertThat(rating.getId()).isNotNull();
		assertThat(rating.getOrderNumber()).isEqualTo(10);

		// Update
		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		assertThat(rating.getOrderNumber()).isEqualTo(20);

		// Find
		assertThat(ratingRepository.findAll()).isNotEmpty();

		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		assertThat(ratingList).isEmpty();
	}
}
