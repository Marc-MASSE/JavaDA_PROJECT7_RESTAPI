package com.nnk.springboot.controllers;

import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.DTO.CurvePointDTO;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.ICurvePointService;

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
public class CurveController {
    // TODO: Inject Curve Point service
	
	static Logger log = LogManager.getLogger(CurveController.class.getName());
	
	@Autowired
	private ICurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model){
        // TODO: find all Curve Point, add to model
    	List<CurvePointDTO> curvePointList = curvePointService.getCurvePoints();
        model.addAttribute("curvePoint", curvePointList);
        log.info("GET request - endpoint /curvePoint/list - return BidList/list page");
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
    	CurvePointDTO curvePoint = new CurvePointDTO();
    	model.addAttribute("curvePoint",curvePoint);
        log.info("GET request - endpoint /curvePoint/add - return CurvePoint/add page");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePointDTO curvePointDTO, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        log.info("POST request - endpoint /curvePoint/validate - add CurvePoint = {}",curvePointDTO);
        if (result.hasErrors()) {
            log.info("POST request - endpoint /curvePoint/validate - BindingResult = {}",result);
			return "curvePoint/add";
		}
    	curvePointService.addCurvePoint(curvePointDTO);
        model.addAttribute("curvePoint", curvePointDTO);
        log.info("POST request - endpoint /curvePoint/validate - create curvePoint = {}",curvePointDTO);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        model.addAttribute("curvePoint", curvePointService.getCurvePointById(id));
        log.info("GET request - endpoint /curvepoint/update - curvePoint to update = {}",curvePointService.getCurvePointById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("curvePoint") CurvePointDTO curvePointDTO,BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            log.info("POST request - endpoint /curvePoint/update - BindingResult = {}",result);
			return "curvePoint/update";
		}
    	curvePointService.updateCurvePoint(id,curvePointDTO);
        log.info("POST request - endpoint /curvePoint/update - update curvePoint = {}",curvePointDTO);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
       	curvePointService.deleteCurvePoint(id);
        log.info("POST request - endpoint /curvePoint/delete - delete curvePoint with id = {}",id);
        return "redirect:/curvePoint/list";
    }
}
