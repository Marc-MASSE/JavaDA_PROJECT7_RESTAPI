package com.nnk.springboot.controllers;

import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.service.IBidListService;
import com.nnk.springboot.service.IUserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private IUserService userService;

	/*
	 * Page "BidList/list"
	 * Display the entire list of bidLists
	 */
    @RequestMapping("/bidList/list")
    public String home(Model model){
    	List<BidListDTO> bidList = bidListService.getBidLists();
        model.addAttribute("bidList", bidList);
        log.info("GET request - endpoint /bidList/list - return BidList/list page");
		model.addAttribute("user",userService.getConnectedUser());
		log.info("SecurityContextHolder = {}",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "bidList/list";
    }

	/*
	 * Page "BidList/add"
	 * Display a form to add a BidListDTO
	 */
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
    	BidListDTO bidListDTO = new BidListDTO();
        model.addAttribute("bidList", bidListDTO);
        log.info("GET request - endpoint /bidList/add - return BidList/add page");
        return "bidList/add";
    }

	/*
	 * From "BidList/add" page
	 * To check if the data conforms to the specifications :
	 * If this is not the case, redirect to BidList/add page with error messages
	 * If this is the case, add the bidListDTO and redirect to BidList/list page 
	 */
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

	/*
	 * Page "BidList/update"
	 * @param : id is the bidList id to update
	 * Display a form to update a this bidList
	 */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("bidList", bidListService.getBidListById(id));
        log.info("GET request - endpoint /bidList/update - bidList to update = {}",bidListService.getBidListById(id));
        return "bidList/update";
    }

	/*
	 * From "BidList/update" page
	 * @param : id is the bidList id to update
	 * To check if the data conforms to the specifications :
	 * If this is not the case, redirect to BidList/update page with error messages
	 * If this is the case, update the bidList and redirect to BidList/list page 
	 */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bidList") BidListDTO bidListDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /bidList/update - BindingResult = {}",result);
            bidListDTO.setBidListId(id);
			return "bidList/update";
		}
    	bidListService.updateBidList(id,bidListDTO);
        log.info("POST request - endpoint /bidList/update - update bidList = {}",bidListDTO);
        return "redirect:/bidList/list";
    }

	/*
	 * From "BidList/list" page
	 * @param : id is the bidList id to delete
	 */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
    	bidListService.deleteBidList(id);
        log.info("POST request - endpoint /bidList/delete - delete bidList with id = {}",id);
        return "redirect:/bidList/list";
    }
}
