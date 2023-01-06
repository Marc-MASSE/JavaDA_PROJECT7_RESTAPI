package com.nnk.springboot.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.DTO.RatingDTO;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.IRatingService;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {
	
	private IRatingService ratingService;
	
	@Mock
	private RatingRepository ratingRepository;
	
	private Rating rating1;
	private Rating rating2;
	private RatingDTO ratingDTO1;
	private RatingDTO ratingDTO2;
	
	@Captor
	ArgumentCaptor<Rating> ratingCaptor;
	@Captor
	ArgumentCaptor<Integer> idCaptor;
	
	@BeforeEach
	public void init() {
		ratingService = new RatingServiceImpl(ratingRepository);
		rating1 = Rating.builder()
				.id(1)
				.moodysRating("A1")
				.sandPRating("Aa1")
				.fitchRating("A1")
				.orderNumber(1)
				.build();
		rating2 = Rating.builder()
				.id(2)
				.moodysRating("A2")
				.sandPRating("Aa2")
				.fitchRating("A2")
				.orderNumber(2)
				.build();
		ratingDTO1 = RatingDTO.builder()
				.id(1)
				.moodysRating("A1")
				.sandPRating("Aa1")
				.fitchRating("A1")
				.orderNumber(1)
				.build();
		ratingDTO2 = RatingDTO.builder()
				.id(2)
				.moodysRating("A2")
				.sandPRating("Aa2")
				.fitchRating("A2")
				.orderNumber(2)
				.build();
	}
	
	// Test addRating
	@Test
	public void addBidList_success() {
		RatingDTO ratingDTO = RatingDTO.builder()
				.moodysRating("A")
				.sandPRating("Aa")
				.fitchRating("A")
				.orderNumber(4)
				.build();
		Rating rating = Rating.builder()
				.moodysRating("A")
				.sandPRating("Aa")
				.fitchRating("A")
				.orderNumber(4)
				.build();
		when(ratingRepository.save(any(Rating.class)))
			.thenReturn(rating);
		ratingService.addRating(ratingDTO);
		verify(ratingRepository).save(ratingCaptor.capture());
		assertThat(ratingCaptor.getValue()).isEqualTo(rating);
		verify(ratingRepository).save(rating);
	}
	
	// Test getRatings
	@Test
	public void getRatings_success() {
		when(ratingRepository.findAllByOrderByIdDesc())
			.thenReturn(Arrays.asList(rating2,rating1));
		assertThat(ratingService.getRatings())
			.contains(ratingDTO1)
			.contains(ratingDTO2);
		verify(ratingRepository).findAllByOrderByIdDesc();
	}
	
	// Test getRatingById
	@Nested
	class GetRatingById {
		@Test
		public void success() {
			when(ratingRepository.existsById(1))
				.thenReturn(true);
			when(ratingRepository.getById(1))
				.thenReturn(rating1);
			assertThat(ratingService.getRatingById(1)
				.equals(ratingDTO1));
			verify(ratingRepository).existsById(1);
			verify(ratingRepository).getById(1);
		}
		@Test
		public void no_rating() {
			when(ratingRepository.existsById(15))
				.thenReturn(false);
			assertThat(ratingService.getRatingById(15))
				.isEqualTo(new RatingDTO());
			verify(ratingRepository).existsById(15);
		}
	}
	
	// Test updateRating
	@Nested
	class UpdateRatingById {
		@Test
		public void success() {
			RatingDTO ratingDTOToUpdate = RatingDTO.builder()
					.moodysRating("A")
					.sandPRating("Aa")
					.fitchRating("A")
					.orderNumber(4)
					.build();
			Rating rating1Updated = Rating.builder()
					.id(1)
					.moodysRating("A")
					.sandPRating("Aa")
					.fitchRating("A")
					.orderNumber(4)
					.build();
			when(ratingRepository.existsById(1))
				.thenReturn(true);
			when(ratingRepository.getById(1))
				.thenReturn(rating1);
			when(ratingRepository.save(rating1Updated))
				.thenReturn(rating1Updated);
			assertThat(ratingService.updateRating(1,ratingDTOToUpdate)
				.equals(rating1Updated));
			verify(ratingRepository).existsById(1);
			verify(ratingRepository).getById(1);
			verify(ratingRepository).save(rating1Updated);
		}
		@Test
		public void no_rating() {
			RatingDTO ratingDTOToUpdate = RatingDTO.builder()
					.moodysRating("A")
					.sandPRating("Aa")
					.fitchRating("A")
					.orderNumber(4)
					.build();
			when(ratingRepository.existsById(15))
				.thenReturn(false);
			assertThat(ratingService.updateRating(15,ratingDTOToUpdate))
				.isEqualTo(new Rating());
			verify(ratingRepository).existsById(15);
		}
	}
	
	// Test deleteRating
	@Nested
	class DeleteRatingById {
		@Test
		public void success() {
			when(ratingRepository.existsById(1))
				.thenReturn(true);
			ratingService.deleteRating(1);
			verify(ratingRepository).existsById(1);
			verify(ratingRepository).deleteById(idCaptor.capture());
			assertThat(idCaptor.getValue()).isEqualTo(1);
			verify(ratingRepository).deleteById(1);
		}
		@Test
		public void no_rating() {
			when(ratingRepository.existsById(15))
				.thenReturn(false);
			ratingService.deleteRating(15);
			verify(ratingRepository).existsById(15);
		}
	}
	
	// Test ratingToDTOMapper
	@Test
	public void mapper_success() {
		assertThat(ratingService.ratingToDTOMapper(rating1))
			.isEqualTo(ratingDTO1);
	}

}
