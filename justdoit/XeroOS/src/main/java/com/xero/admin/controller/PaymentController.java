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
public class PaymentController {

	@Resource
	private CompanyService companyService;
	
	@RequestMapping(value=("/payment"), method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession(false);
		if(null == session){
			model.setViewName("redirect:/");
			return model ;
		}
		SystemUser sysUser = (SystemUser) session
				.getAttribute(WebConstants.XERO_USER_SESSION);
		
		
		if (null != session && null != sysUser) {
			Company cmp = companyService.getCompanyByUserId(sysUser.getId());
			Integer companyId = 0,planId = 0;
			if (null != cmp) {
				companyId = cmp.getId();
				planId = cmp.getPlanId() ;
				model.addObject("currentCompany", companyId);
				model.addObject("planId", planId);
			}
			
			Date ep = sysUser.getExpiredDateTime();
			
			if (ep.before(new Date()) && planId == 1) {
				model.setViewName("redirect:/");
			} else {
				int leftDays = DateUtil.daysOfTwoDate(new Date(), ep);
				model.addObject("leftDays", leftDays);
				model.setViewName("/myAccount");
			}
		} else {
			model.setViewName("redirect:/");
		}
		
		return model;
	}
}
