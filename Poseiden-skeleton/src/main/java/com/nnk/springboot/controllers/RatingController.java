package com.nnk.springboot.controllers;

import com.nnk.springboot.DTO.RatingDTO;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.IRatingService;

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
public class RatingController {
	
	static Logger log = LogManager.getLogger(RatingController.class.getName());
	
	@Autowired
	private IRatingService ratingService;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * Page "Rating/list"
	 * Display the entire list of ratings
	 */
    @RequestMapping("/rating/list")
    public String home(Model model) {
    	List<RatingDTO> ratingList = ratingService.getRatings();
    	model.addAttribute("rating",ratingList);
        log.info("GET request - endpoint /rating/list - return Rating/list page");
		String connectedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findUserByUsername(connectedUser);
		model.addAttribute("user",user);
        return "rating/list";
    }

	/*
	 * Page "Rating/add"
	 * Display a form to add a RatingDTO
	 */
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
    	Rating rating = new Rating();
    	model.addAttribute("rating",rating);
        log.info("GET request - endpoint /rating/add - return Rating/add page");
        return "rating/add";
    }

	/*
	 * From "Rating/add" page
	 * To check if the data conforms to the specifications :
	 * If this is not the case, redirect to Rating/add page with error messages
	 * If this is the case, add the ratingDTO and redirect to Rating/list page 
	 */
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") RatingDTO ratingDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /rating/validate - BindingResult = {}",result);
			return "rating/add";
		}
    	ratingService.addRating(ratingDTO);
        model.addAttribute("rating", ratingDTO);
        log.info("POST request - endpoint /rating/validate - create rating = {}",ratingDTO);
        return "redirect:/rating/list";
    }

	/*
	 * Page "Rating/update"
	 * @param : id is the rating id to update
	 * Display a form to update a this rating
	 */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	model.addAttribute("rating",ratingService.getRatingById(id));
        log.info("GET request - endpoint /rating/update - rating to update = {}",ratingService.getRatingById(id));
        return "rating/update";
    }

	/*
	 * From "Rating/update" page
	 * @param : id is the rating id to update
	 * To check if the data conforms to the specifications :
	 * If this is not the case, redirect to Rating/update page with error messages
	 * If this is the case, update the rating and redirect to Rating/list page 
	 */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") RatingDTO ratingDTO,BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST request - endpoint /rating/update - BindingResult = {}",result);
			return "rating/update";
		}
    	ratingService.updateRating(id,ratingDTO);
        log.info("POST request - endpoint /rating/update - update rating = {}",ratingDTO);
        return "redirect:/rating/list";
    }

	/*
	 * From "Rating/list" page
	 * @param : id is the rating id to delete
	 */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {
       	ratingService.deleteRating(id);
        log.info("POST request - endpoint /rating/delete - delete rating with id = {}",id);
        return "redirect:/rating/list";
    }
}
