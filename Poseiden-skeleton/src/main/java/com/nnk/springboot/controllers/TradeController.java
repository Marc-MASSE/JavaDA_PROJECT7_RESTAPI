package com.nnk.springboot.controllers;

import com.nnk.springboot.DTO.TradeDTO;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.ITradeService;
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
public class TradeController {
	
	static Logger log = LogManager.getLogger(TradeController.class.getName());
	
	@Autowired
	private ITradeService tradeService;
	
	@Autowired
	private IUserService userService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
       	List<TradeDTO> tradeList = tradeService.getTrades();
    	model.addAttribute("trade",tradeList);
        log.info("GET request - endpoint /trade/list - return Trade/list page");
        model.addAttribute("user",userService.getConnectedUser());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
       	Trade trade = new Trade();
    	model.addAttribute("trade",trade);
        log.info("GET request - endpoint /trade/add - return Trade/add page");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") TradeDTO tradeDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /trade/validate - BindingResult = {}",result);
			return "trade/add";
		}
    	tradeService.addTrade(tradeDTO);
        model.addAttribute("trade", tradeDTO);
        log.info("POST request - endpoint /trade/validate - create trade = {}",tradeDTO);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
       	model.addAttribute("trade",tradeService.getTradeById(id));
        log.info("GET request - endpoint /trade/update - trade to update = {}",tradeService.getTradeById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") TradeDTO tradeDTO,BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /trade/update - BindingResult = {}",result);
            tradeDTO.setTradeId(id);
			return "trade/update";
		}
    	tradeService.updateTrade(id,tradeDTO);
        log.info("POST request - endpoint /trade/update - update trade = {}",tradeDTO);
    	return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
      	tradeService.deleteTrade(id);
        log.info("POST request - endpoint /trade/delete - delete trade with id = {}",id);
        return "redirect:/trade/list";
    }
}
