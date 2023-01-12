package com.nnk.springboot.controllers;

import com.nnk.springboot.DTO.CurvePointDTO;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.ICurvePointService;

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
public class CurveController {
	
	static Logger log = LogManager.getLogger(CurveController.class.getName());
	
	@Autowired
	private ICurvePointService curvePointService;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * Page "CurvePoint/list"
	 * Display the entire list of curvePoints
	 */
    @RequestMapping("/curvePoint/list")
    public String home(Model model){
    	List<CurvePointDTO> curvePointList = curvePointService.getCurvePoints();
        model.addAttribute("curvePoint", curvePointList);
        log.info("GET request - endpoint /curvePoint/list - return BidList/list page");
		String connectedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findUserByUsername(connectedUser);
		model.addAttribute("user",user);
        return "curvePoint/list";
    }

	/*
	 * Page "CurvePoint/add"
	 * Display a form to add a CurvePointDTO
	 */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
    	CurvePointDTO curvePoint = new CurvePointDTO();
    	model.addAttribute("curvePoint",curvePoint);
        log.info("GET request - endpoint /curvePoint/add - return CurvePoint/add page");
        return "curvePoint/add";
    }

	/*
	 * From "CurvePoint/add" page
	 * To check if the data conforms to the specifications :
	 * If this is not the case, redirect to CurvePoint/add page with error messages
	 * If this is the case, add the curvePointDTO and redirect to CurvePoint/list page 
	 */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePointDTO curvePointDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /curvePoint/validate - BindingResult = {}",result);
			return "curvePoint/add";
		}
    	curvePointService.addCurvePoint(curvePointDTO);
        model.addAttribute("curvePoint", curvePointDTO);
        log.info("POST request - endpoint /curvePoint/validate - create curvePoint = {}",curvePointDTO);
        return "redirect:/curvePoint/list";
    }

	/*
	 * Page "CurvePoint/update"
	 * @param : id is the curvePoint id to update
	 * Display a form to update a this curvePoint
	 */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("curvePoint", curvePointService.getCurvePointById(id));
        log.info("GET request - endpoint /curvepoint/update - curvePoint to update = {}",curvePointService.getCurvePointById(id));
        return "curvePoint/update";
    }

	/*
	 * From "CurvePoint/update" page
	 * @param : id is the curvePoint id to update
	 * To check if the data conforms to the specifications :
	 * If this is not the case, redirect to CurvePoint/update page with error messages
	 * If this is the case, update the bidList and redirect to CurvePoint/list page 
	 */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("curvePoint") CurvePointDTO curvePointDTO,BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /curvePoint/update - BindingResult = {}",result);
			return "curvePoint/update";
		}
    	curvePointService.updateCurvePoint(id,curvePointDTO);
        log.info("POST request - endpoint /curvePoint/update - update curvePoint = {}",curvePointDTO);
        return "redirect:/curvePoint/list";
    }

	/*
	 * From "CurvePoint/list" page
	 * @param : id is the curvePoint id to delete
	 */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
       	curvePointService.deleteCurvePoint(id);
        log.info("POST request - endpoint /curvePoint/delete - delete curvePoint with id = {}",id);
        return "redirect:/curvePoint/list";
    }
}
