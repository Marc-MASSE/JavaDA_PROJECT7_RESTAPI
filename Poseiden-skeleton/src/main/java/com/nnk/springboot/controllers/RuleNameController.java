package com.nnk.springboot.controllers;

import com.nnk.springboot.DTO.RuleNameDTO;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.IRuleNameService;
import com.nnk.springboot.service.IUserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.validation.Valid;

@Controller
public class RuleNameController {
	
	static Logger log = LogManager.getLogger(RuleNameController.class.getName());
	
	@Autowired
	private IRuleNameService ruleNameService;
	
	@Autowired
	private IUserService userService;

	/*
	 * Page "RuleName/list"
	 * Display the entire list of ruleNames
	 */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
    	List<RuleNameDTO> ruleNameList = ruleNameService.getRuleNames();
    	model.addAttribute("ruleName",ruleNameList);
        log.info("GET request - endpoint /ruleName/list - return RuleName/list page");
        model.addAttribute("user",userService.getConnectedUser());
        return "ruleName/list";
    }

	/*
	 * Page "RuleName/add"
	 * Display a form to add a RuleNameDTO
	 */
    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
    	RuleName ruleName = new RuleName();
    	model.addAttribute("ruleName",ruleName);
        log.info("GET request - endpoint /ruleName/add - return RuleName/add page");
        return "ruleName/add";
    }

	/*
	 * From "RuleName/add" page
	 * To check if the data conforms to the specifications :
	 * If this is not the case, redirect to RuleName/add page with error messages
	 * If this is the case, add the ruleNameDTO and redirect to RuleName/list page 
	 */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleNameDTO ruleNameDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /ruleName/validate - BindingResult = {}",result);
			return "ruleName/add";
		}
    	ruleNameService.addRuleName(ruleNameDTO);
        model.addAttribute("ruleName", ruleNameDTO);
        log.info("POST request - endpoint /ruleName/validate - create ruleName = {}",ruleNameDTO);
        return "redirect:/ruleName/list";
    }

	/*
	 * Page "RuleName/update"
	 * @param : id is the ruleName id to update
	 * Display a form to update a this ruleName
	 */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
       	model.addAttribute("ruleName",ruleNameService.getRuleNameById(id));
        log.info("GET request - endpoint /ruleName/update - ruleName to update = {}",ruleNameService.getRuleNameById(id));
        return "ruleName/update";
    }

	/*
	 * From "RuleName/update" page
	 * @param : id is the ruleName id to update
	 * To check if the data conforms to the specifications :
	 * If this is not the case, redirect to RuleName/update page with error messages
	 * If this is the case, update the ruleName and redirect to RuleName/list page 
	 */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleNameDTO ruleNameDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /ruleName/update - BindingResult = {}",result);
			return "ruleName/update";
		}
    	ruleNameService.updateRuleName(id,ruleNameDTO);
        log.info("POST request - endpoint /ruleName/update - update ruleName = {}",ruleNameDTO);
    	return "redirect:/ruleName/list";
    }

	/*
	 * From "RuleName/list" page
	 * @param : id is the ruleName id to delete
	 */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {
      	ruleNameService.deleteRuleName(id);
        log.info("POST request - endpoint /ruleName/delete - delete ruleName with id = {}",id);
        return "redirect:/ruleName/list";
    }
}
