package com.xero.admin.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.service.SystemUserService;
import com.xero.admin.util.DateUtil;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.web.WebConstants;

@Controller
public class SystemUserController {

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (null == session) {
			model.setViewName("redirect:/");
			return model;
		}
		SystemUser sysUser = (SystemUser) session
				.getAttribute(WebConstants.XERO_USER_SESSION);
		if (null != sysUser) {
			Date ep = sysUser.getExpiredDateTime();
			if (ep.before(new Date())) {
				model.setViewName("redirect:/");
			} else {
				int leftDays = DateUtil.daysOfTwoDate(new Date(), ep);
				model.addObject("leftDays", leftDays);
				model.setViewName("/manage-user");
			}
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}

	@RequestMapping(value = "/user-list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseCollection<SystemUser> getAllUser(HttpServletRequest request) {
		ResponseCollection<SystemUser> res = new ResponseCollection<SystemUser>() ;
		//HttpSession session = request.getSession(false);
		//if(null == session){
		//	res.setResult(false);
		//	res.setData(null); 
		//	res.setMessage("No Authorization.") ;
		//}else{
			res = systemUserService.getAllUser();
		//}
		return res ;
	}

}
