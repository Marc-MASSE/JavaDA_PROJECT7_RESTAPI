package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
	
	public List<Rating> findAllByOrderByIdDesc();

}
