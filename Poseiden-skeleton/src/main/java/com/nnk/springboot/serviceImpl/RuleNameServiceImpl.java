package com.nnk.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.IRuleNameService;

public class RuleNameServiceImpl implements IRuleNameService {
	
	private RuleNameRepository ruleNameRepository;
	
	@Autowired
	public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
		this.ruleNameRepository = ruleNameRepository;
	}

}
