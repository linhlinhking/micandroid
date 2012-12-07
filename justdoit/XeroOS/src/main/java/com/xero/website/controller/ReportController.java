package com.xero.website.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.util.DateUtil;
import com.xero.core.web.WebConstants;

@Controller
public class ReportController {

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView() ;
		HttpSession session = request.getSession(false);
		SystemUser sysUser = (SystemUser) session
				.getAttribute(WebConstants.XERO_USER_SESSION);
		if (null != session && null != sysUser) {
			Date ep = sysUser.getExpiredDateTime();
			if (ep.before(new Date())) {
				model.setViewName("redirect:/");
			} else {
				int leftDays = DateUtil.daysOfTwoDate(new Date(), ep);
				model.addObject("leftDays", leftDays);
				model.setViewName("/report");
			}
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}
}
