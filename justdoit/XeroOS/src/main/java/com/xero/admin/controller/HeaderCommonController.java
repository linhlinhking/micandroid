package com.xero.admin.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.util.DateUtil;
import com.xero.core.web.WebConstants;
import com.xero.website.bean.Company;
import com.xero.website.service.CompanyService;

@Controller
public class HeaderCommonController {

	@Resource
	private CompanyService companyService ;
	
	@RequestMapping(value = "/header-common", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView() ;
		HttpSession session = request.getSession(false);
		if (null == session) {
			model.setViewName("redirect:/");
			return model;
		}
		SystemUser sysUser = (SystemUser) session
				.getAttribute(WebConstants.XERO_USER_SESSION);
		if (null != session && null != sysUser) {
			Date ep = sysUser.getExpiredDateTime();
			Integer userId = sysUser.getId() ;
			Company cmp = companyService.getCompanyByUserId(userId) ;
			Integer planId = 0,companyId = 0;
			if(null != cmp){
				planId = cmp.getPlanId() ;
				companyId = cmp.getId() ;
			}
			
			if (ep.before(new Date()) && planId == 1) {
				model.setViewName("redirect:/");
			} else {
				int leftDays = DateUtil.daysBetweenDate(new Date(), ep);
				model.addObject("leftDays", leftDays);
				model.addObject("planId", planId);
				model.addObject("isLinkXero", sysUser.getLinkXero());
				model.addObject("companyId", companyId);
				model.addObject("currency_user_id", userId) ;
				model.setViewName("/header");
			}
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}
}
