package com.grupo3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@GetMapping ("/")
	public ModelAndView home() {		
		ModelAndView mv = new ModelAndView();	
		mv.setViewName("/home");
		return mv ;		
	}
	
}
