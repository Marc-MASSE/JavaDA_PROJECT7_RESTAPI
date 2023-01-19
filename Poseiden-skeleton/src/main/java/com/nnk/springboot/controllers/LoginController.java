package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("app")
public class LoginController {
	
	static Logger log = LogManager.getLogger(LoginController.class.getName());

    @Autowired
    private UserRepository userRepository;

	/*
	 * Page "Login"
	*/
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	/*
	 * "Logout" redirect to "Login"
	*/
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
	
	/*
	 * After login, ADMIN role is redirected to User/list page
	 * other roles are redirected to BidList/list page
	*/
	@GetMapping("/login/redirection")
	public String successUrlRedirection() {
		String connectedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findUserByUsername(connectedUser);
		log.info("GET request - endpoint /login/redirection - user = {}",user);
		if (user.getRole().equals("ADMIN")) {
			return "redirect:/user/list";
		}
		return "redirect:/bidList/list";
	}
	

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
    
    /*
    @RequestMapping("/*")
    public String getGithub(){
      return "bidList/list";
    }
    */
}
