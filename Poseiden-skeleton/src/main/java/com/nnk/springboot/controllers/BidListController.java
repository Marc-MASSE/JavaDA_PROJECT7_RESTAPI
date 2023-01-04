package com.nnk.springboot.controllers;

import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.IBidListService;

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
public class BidListController {
	
	static Logger log = LogManager.getLogger(BidListController.class.getName());
	
	@Autowired
	private IBidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model){
    	List<BidListDTO> bidList = bidListService.getBidLists();
        model.addAttribute("bidList", bidList);
        log.info("GET request - endpoint /bidList/list - return BidList/list page");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
    	BidListDTO bidListDTO = new BidListDTO();
        model.addAttribute("bidList", bidListDTO);
        log.info("GET request - endpoint /bidList/add - return BidList/add page");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidListDTO bidListDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /bidList/validate - BindingResult = {}",result);
			return "bidList/add";
		}
    	bidListService.addBidList(bidListDTO);
        model.addAttribute("bidList", bidListDTO);
        log.info("POST request - endpoint /bidList/validate - create bidList = {}",bidListDTO);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidList", bidListService.getBidListById(id));
        log.info("GET request - endpoint /bidList/update - bidList to update = {}",bidListService.getBidListById(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bidList") BidListDTO bidListDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /bidList/update - BindingResult = {}",result);
			return "bidList/update";
		}
    	bidListService.updateBidList(id,bidListDTO);
        log.info("POST request - endpoint /bidList/update - update bidList = {}",bidListDTO);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
    	bidListService.deleteBidList(id);
        log.info("POST request - endpoint /bidList/delete - delete bidList with id = {}",id);
        return "redirect:/bidList/list";
    }
}
