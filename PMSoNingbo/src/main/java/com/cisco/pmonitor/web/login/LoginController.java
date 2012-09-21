package com.cisco.pmonitor.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.service.ICiscoLoginService;
import com.cisco.pmonitor.service.IUserService;
import com.cisco.pmonitor.service.util.Result;
import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
public class LoginController {
	
	private IUserService userService;
	private ICiscoLoginService ciscoLoginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(@RequestParam String username, 
							@RequestParam String password,
							Model model, 
							HttpServletRequest request) throws Exception{
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return "login";
		}
		username = username.trim();
		password = password.trim();
		
		Result<UserDo> rs = userService.findUserByUsername(username);
		if(rs.isSuccess()) {
			UserDo userDo = rs.getDefaultModel();
			if(null == userDo) {
				return "login";
			}
			if(StringUtils.isEmpty(userDo.getPassword())) {
				Result<Boolean> _rs = 
						ciscoLoginService.login(username, password);
				if(_rs.isSuccess()) {
					setSession(request, userDo);
					return "main";
				}
			}
			else {
				if(password.equalsIgnoreCase(userDo.getPassword())) {
					setSession(request, userDo);
					return "main";
				}
			}
		}
		model.addAttribute("msg", "Account information isn't correct!");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request) throws Exception {
		return SessionHandler.verifySession(request, "main");
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if(null != session && 
				null != session.getAttribute(Constants.PM_USER_SESSION)) {
			session.invalidate();
		}
		return "login";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String backHome(HttpServletRequest request) throws Exception {
		return SessionHandler.verifySession(request, "main");
	}
	
	private void setSession(HttpServletRequest request, UserDo userDo) {
		HttpSession session = request.getSession(false);
		session.setAttribute(Constants.PM_USER_SESSION, userDo);
		session.setMaxInactiveInterval(Constants.SESSION_OUT_TIME);
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setCiscoLoginService(ICiscoLoginService ciscoLoginService) {
		this.ciscoLoginService = ciscoLoginService;
	}
}
