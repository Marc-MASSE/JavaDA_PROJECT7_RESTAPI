package com.nnk.springboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.DTO.RuleNameDTO;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.IRuleNameService;

@Service
public class RuleNameServiceImpl implements IRuleNameService {
	
	private RuleNameRepository ruleNameRepository;
	
	@Autowired
	public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
		this.ruleNameRepository = ruleNameRepository;
	}

	@Override
	public RuleName addRuleName(RuleNameDTO ruleNameDTO) {
		RuleName ruleName = RuleName.builder()
				.name(ruleNameDTO.getName())
				.description(ruleNameDTO.getDescription())
				.json(ruleNameDTO.getJson())
				.template(ruleNameDTO.getTemplate())
				.sqlStr(ruleNameDTO.getSqlStr())
				.sqlPart(ruleNameDTO.getSqlPart())
				.build();
		return ruleNameRepository.save(ruleName);
	}

	@Override
	public List<RuleNameDTO> getRuleNames() {
		List<RuleName> ruleNameList = ruleNameRepository.findAllByOrderByIdDesc();
		return ruleNameList.stream()
				.map(r -> ruleNameToDTOMapper(r))
				.collect(Collectors.toList());
	}

	@Override
	public RuleNameDTO getRuleNameById(Integer id) {
		if (ruleNameRepository.existsById(id)) {
			return ruleNameToDTOMapper(ruleNameRepository.getById(id));
		}
		return new RuleNameDTO();
	}

	@Override
	public RuleName updateRuleName(Integer id, RuleNameDTO ruleNameDTO) {
		if (ruleNameRepository.existsById(id)) {
			RuleName ruleName = ruleNameRepository.getById(id);
			ruleName.setName(ruleNameDTO.getName());
			ruleName.setDescription(ruleNameDTO.getDescription());
			ruleName.setJson(ruleNameDTO.getJson());
			ruleName.setTemplate(ruleNameDTO.getTemplate());
			ruleName.setSqlStr(ruleNameDTO.getSqlStr());
			ruleName.setSqlPart(ruleNameDTO.getSqlPart());
			return ruleNameRepository.save(ruleName);
		}
		return new RuleName();	}

	@Override
	public void deleteRuleName(Integer id) {
		if (ruleNameRepository.existsById(id)) {
			ruleNameRepository.deleteById(id);
		}
	}
	
	@Override
	public RuleNameDTO ruleNameToDTOMapper(RuleName ruleName) {
		return RuleNameDTO.builder()
				.id(ruleName.getId())
				.name(ruleName.getName())
				.description(ruleName.getDescription())
				.json(ruleName.getJson())
				.template(ruleName.getTemplate())
				.sqlStr(ruleName.getSqlStr())
				.sqlPart(ruleName.getSqlPart())
				.build();
	}

}
