package com.xero.website.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.xero.website.service.CompanyService;

@Controller
public class ReportController {
	
	@Resource
	private CompanyService companyService ;


	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView() ;
		model.setViewName("/report");
		return model;
	}
}
