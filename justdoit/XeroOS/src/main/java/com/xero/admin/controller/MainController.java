package com.xero.admin.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.service.SystemUserService;
import com.xero.admin.util.DateUtil;
import com.xero.core.util.CookieUtil;
import com.xero.core.util.encode.EncodeUtil;
import com.xero.core.web.WebConstants;

@Controller
public class MainController {

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping(value = ("/contact"), method = RequestMethod.GET)
	public ModelAndView contact(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
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
				model.setViewName("/contact");
			}
		} else {
			model.setViewName("redirect:/");
		}
		
		return model;
	}

	
	public void justForCookie(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		Cookie cookie = CookieUtil.getCookie(request,
				WebConstants.COOKIE_DOMAIN_NAME);
		if (null != cookie) {
			String cookieValue = cookie.getValue();
			byte[] byteArry = EncodeUtil.base64Decode(cookieValue);
			String tempValue = new String(byteArry);
			String[] valArry = tempValue.split(":");
			SystemUser sysUser = systemUserService.get(Integer
					.valueOf(valArry[0]));
			if (null != sysUser) {
				Date ep = sysUser.getExpiredDateTime();
				if (ep.before(new Date())) {
					model.setViewName("redirect:/");
				} else {
					int leftDays = DateUtil.daysOfTwoDate(new Date(), ep);
					model.addObject("leftDays", leftDays);
					model.setViewName("/contact");
				}
			}else{
				model.setViewName("redirect:/");
			}

		} else {
			model.setViewName("redirect:/");
		}
	}
}
