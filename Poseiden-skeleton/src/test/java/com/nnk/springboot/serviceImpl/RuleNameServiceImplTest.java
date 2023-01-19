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

import com.nnk.springboot.DTO.RuleNameDTO;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.IRuleNameService;

@ExtendWith(MockitoExtension.class)
public class RuleNameServiceImplTest {
	
	private IRuleNameService ruleNameService;
	
	@Mock
	private RuleNameRepository ruleNameRepository;
	
	private RuleName ruleName1;
	private RuleName ruleName2;
	private RuleNameDTO ruleNameDTO1;
	private RuleNameDTO ruleNameDTO2;
	
	@Captor
	ArgumentCaptor<RuleName> ruleNameCaptor;
	@Captor
	ArgumentCaptor<Integer> idCaptor;
	
	@BeforeEach
	public void init() {
		ruleNameService = new RuleNameServiceImpl(ruleNameRepository);
		ruleName1 = RuleName.builder()
				.id(1)
				.name("Nom1")
				.description("des1")
				.json("json1")
				.template("temp1")
				.sqlStr("str1")
				.sqlPart("part1")
				.build();
		ruleName2 = RuleName.builder()
				.id(2)
				.name("Nom2")
				.description("des2")
				.json("json2")
				.template("temp2")
				.sqlStr("str2")
				.sqlPart("part2")
				.build();
		ruleNameDTO1 = RuleNameDTO.builder()
				.id(1)
				.name("Nom1")
				.description("des1")
				.json("json1")
				.template("temp1")
				.sqlStr("str1")
				.sqlPart("part1")
				.build();
		ruleNameDTO2 = RuleNameDTO.builder()
				.id(2)
				.name("Nom2")
				.description("des2")
				.json("json2")
				.template("temp2")
				.sqlStr("str2")
				.sqlPart("part2")
				.build();
	}
	
	// Test addRuleName
	@Test
	public void addRuleName_success() {
		RuleNameDTO ruleNameDTO = RuleNameDTO.builder()
				.name("Nom")
				.description("des")
				.json("json")
				.template("temp")
				.sqlStr("str")
				.sqlPart("part")
				.build();
		RuleName ruleName = RuleName.builder()
				.name("Nom")
				.description("des")
				.json("json")
				.template("temp")
				.sqlStr("str")
				.sqlPart("part")
				.build();
		when(ruleNameRepository.save(any(RuleName.class)))
			.thenReturn(ruleName);
		ruleNameService.addRuleName(ruleNameDTO);
		verify(ruleNameRepository).save(ruleNameCaptor.capture());
		assertThat(ruleNameCaptor.getValue()).isEqualTo(ruleName);
		verify(ruleNameRepository).save(ruleName);
	}
	
	// Test getRuleNames
	@Test
	public void getRuleNames_success() {
		when(ruleNameRepository.findAllByOrderByIdDesc())
			.thenReturn(Arrays.asList(ruleName2,ruleName1));
		assertThat(ruleNameService.getRuleNames())
			.contains(ruleNameDTO1)
			.contains(ruleNameDTO2);
		verify(ruleNameRepository).findAllByOrderByIdDesc();
	}
	
	// Test getRuleNameById
	@Nested
	class GetRuleNameById {
		@Test
		public void success() {
			when(ruleNameRepository.existsById(1))
				.thenReturn(true);
			when(ruleNameRepository.getById(1))
				.thenReturn(ruleName1);
			assertThat(ruleNameService.getRuleNameById(1)
				.equals(ruleNameDTO1));
			verify(ruleNameRepository).existsById(1);
			verify(ruleNameRepository).getById(1);
		}
		@Test
		public void no_ruleName() {
			when(ruleNameRepository.existsById(15))
				.thenReturn(false);
			assertThat(ruleNameService.getRuleNameById(15))
				.isEqualTo(new RuleNameDTO());
			verify(ruleNameRepository).existsById(15);
		}
	}
	
	// Test updateRuleName
	@Nested
	class UpdateRuleNameById {
		@Test
		public void success() {
			RuleNameDTO ruleNameDTOToUpdate = RuleNameDTO.builder()
					.name("Nom4")
					.description("des4")
					.json("json4")
					.template("temp4")
					.sqlStr("str4")
					.sqlPart("part4")
					.build();
			RuleName ruleName1Updated = RuleName.builder()
					.id(1)
					.name("Nom4")
					.description("des4")
					.json("json4")
					.template("temp4")
					.sqlStr("str4")
					.sqlPart("part4")
					.build();
			when(ruleNameRepository.existsById(1))
				.thenReturn(true);
			when(ruleNameRepository.getById(1))
				.thenReturn(ruleName1);
			when(ruleNameRepository.save(ruleName1Updated))
				.thenReturn(ruleName1Updated);
			assertThat(ruleNameService.updateRuleName(1,ruleNameDTOToUpdate)
				.equals(ruleName1Updated));
			verify(ruleNameRepository).existsById(1);
			verify(ruleNameRepository).getById(1);
			verify(ruleNameRepository).save(ruleName1Updated);
		}
		@Test
		public void no_ruleName() {
			RuleNameDTO ruleNameDTOToUpdate = RuleNameDTO.builder()
					.name("Nom4")
					.description("des4")
					.json("json4")
					.template("temp4")
					.sqlStr("str4")
					.sqlPart("part4")
					.build();
			when(ruleNameRepository.existsById(15))
				.thenReturn(false);
			assertThat(ruleNameService.updateRuleName(15,ruleNameDTOToUpdate))
				.isEqualTo(new RuleName());
			verify(ruleNameRepository).existsById(15);
		}
	}
	
	// Test deleteRuleName
	@Nested
	class DeleteRuleNameById {
		@Test
		public void success() {
			when(ruleNameRepository.existsById(1))
				.thenReturn(true);
			ruleNameService.deleteRuleName(1);
			verify(ruleNameRepository).existsById(1);
			verify(ruleNameRepository).deleteById(idCaptor.capture());
			assertThat(idCaptor.getValue()).isEqualTo(1);
			verify(ruleNameRepository).deleteById(1);
		}
		@Test
		public void no_ruleName() {
			when(ruleNameRepository.existsById(15))
				.thenReturn(false);
			ruleNameService.deleteRuleName(15);
			verify(ruleNameRepository).existsById(15);
		}
	}
	
	// Test ruleNameToDTOMapper
	@Test
	public void mapper_success() {
		assertThat(ruleNameService.ruleNameToDTOMapper(ruleName1))
			.isEqualTo(ruleNameDTO1);
	}

}
