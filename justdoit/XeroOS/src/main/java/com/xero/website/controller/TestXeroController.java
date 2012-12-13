package com.xero.website.controller;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import org.scribe.model.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xero.core.api.SessionAttributes;

@Controller
public class TestXeroController {

	@RequestMapping(value = ("/testXeroPost"), method = RequestMethod.GET)
	public ModelAndView index(WebRequest request) {

		ModelAndView model = new ModelAndView();

		Token accessToken = (Token) request.getAttribute(
				SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		model.addObject("AccessToken", accessToken);
		
		model.setViewName("addSimple");
		return model;
	}
}
