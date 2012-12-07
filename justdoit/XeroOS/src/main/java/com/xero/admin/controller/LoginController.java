package com.xero.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.service.SystemUserService;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.controller.BaseController;
import com.xero.core.web.WebConstants;

@Controller
public class LoginController extends BaseController {

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String doLogin(@RequestParam String uemail,
			@RequestParam String password, @RequestParam String remember_me,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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
			//setCookie(response, request, sysUser, flag);
			return "true";

		}
		// model.addAttribute("login_status", "0") ;
		return "false";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if (null != session
				&& null != session.getAttribute(WebConstants.XERO_USER_SESSION)) {
			session.invalidate();
		}
		return "redirect:/";
	}

}
