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

//@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingRepositoryTest {

	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void ratingTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		rating = ratingRepository.save(rating);
		//Assert.assertNotNull(rating.getId());
		assertThat(rating.getId()).isNotNull();
		//Assert.assertTrue(rating.getOrderNumber() == 10);
		assertThat(rating.getOrderNumber()).isEqualTo(10);

		// Update
		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		//Assert.assertTrue(rating.getOrderNumber() == 20);
		assertThat(rating.getOrderNumber()).isEqualTo(20);

		// Find
		//List<Rating> listResult = ratingRepository.findAll();
		//Assert.assertTrue(listResult.size() > 0);
		assertThat(ratingRepository.findAll()).isNotEmpty();

		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		//Assert.assertFalse(ratingList.isPresent());
		assertThat(ratingList).isEmpty();
	}
}
