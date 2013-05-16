package com.xero.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

	@RequestMapping(value=("/"), method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model ;
	}
	
	@RequestMapping(value=("/contact-us"), method = RequestMethod.GET)
	public ModelAndView contactUs(){
		ModelAndView model = new ModelAndView();
		model.setViewName("contact-index");
		return model ;
	}
	

}
