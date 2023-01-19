package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
	
	public List<RuleName> findAllByOrderByIdDesc();
	
}
