package com.xero.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.bean.type.MailType;
import com.xero.admin.service.SystemUserService;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.controller.BaseController;
import com.xero.core.email.SendManagerService;
import com.xero.core.security.MD5Util;
import com.xero.core.util.ApplicationContextUtil;
import com.xero.core.util.RandomUtil;

@Controller
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SystemUserService systemUserService;
	
	private SendManagerService sendMgrService = (SendManagerService) ApplicationContextUtil
			.getContext().getBean("sendMail");

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String doLogin(@RequestParam String uemail,
			@RequestParam String password, @RequestParam String remember_me,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (StringUtils.isEmpty(uemail) || StringUtils.isEmpty(password)) {
			return "false";
		}
		uemail = uemail.trim();
		password = password.trim();
		ResponseEntity<SystemUser> rs = systemUserService.login(uemail,
				password);

		if (rs.getResult()) {
			SystemUser sysUser = rs.getData();
			boolean flag = false;
			if (null == sysUser) {
				return "false";
			}

			if (null != remember_me && remember_me.equals("1")) {
				flag = true;
			}
			setSession(request, sysUser, flag);
			// setCookie(response, request, sysUser, flag);
			return "true";

		}
		// model.addAttribute("login_status", "0") ;
		return "false";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {
		invalidateSession(request);
		return "redirect:/";
	}

	@RequestMapping(value = "/forgot/password", method = RequestMethod.POST)
	@ResponseBody
	public boolean forgotPassword(@RequestParam("currency_email") String email) {
		boolean flag = false;
		try {
			SystemUser u = systemUserService.get(SystemUser.UEMAIL, email);
			Map<String,Object> params = new HashMap<String,Object>() ;
			if (null != u) {
				String newPassword = RandomUtil.generatePassword(0);
				String md5Password = MD5Util.calcMD5(newPassword);
				u.setPassword(md5Password);
				systemUserService.saveOrUpdate(u);
				params.put("newpassword", newPassword) ;
				
				sendMgrService.sendHtmlMail(MailType.MAILFORGOTPASSWORD, email, null, params) ;
				flag = true;
			}
		} catch (Exception ex) {
			logger.error("Reset User's Password Error.User'Email is " + email,
					ex);
		}

		return flag;
	}
	
	@RequestMapping(value = "/forgot/password", method = RequestMethod.GET)
	public ModelAndView toForgotPassword(HttpServletRequest request) {
		ModelAndView model = new ModelAndView() ;
		model.setViewName("/forgot_password") ;
		return model ;
	}

}
