package com.xero.website.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xero.core.Response.ResponseCollection;
import com.xero.website.bean.OSLanguage;
import com.xero.website.service.OSLanguageService;

@Controller
public class OSLanguageController {

	@Resource
	private OSLanguageService oSLanguageService;

	@RequestMapping(value = "/language", method = RequestMethod.GET)
	@ResponseBody
	public ResponseCollection<OSLanguage> getAllLanguage() {
		return oSLanguageService.getAllLanguage();
	}
}
