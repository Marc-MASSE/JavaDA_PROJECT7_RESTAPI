package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.DTO.RuleNameDTO;
import com.nnk.springboot.domain.RuleName;

public interface IRuleNameService {
	
    RuleName addRuleName(RuleNameDTO ruleNameDTO);

    List<RuleNameDTO> getRuleNames();

    RuleNameDTO getRuleNameById(Integer id);

    RuleName updateRuleName(Integer id, RuleNameDTO ruleNameDTO);

    void deleteRuleName(Integer id);

}
