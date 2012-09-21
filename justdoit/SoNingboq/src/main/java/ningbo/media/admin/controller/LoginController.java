package ningbo.media.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ningbo.media.admin.util.Result;
import ningbo.media.admin.util.WebConstants;
import ningbo.media.bean.SystemUser;
import ningbo.media.service.SystemUserService;
import ningbo.media.web.util.SessionHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/admin")
@Controller
public class LoginController {

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(@RequestParam String username,
			@RequestParam String password, Model model,
			HttpServletRequest request) throws Exception {
		
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return "login";
		}
		
		username = username.trim();
		password = password.trim();
		Result<SystemUser> rs = systemUserService.findUserByUsername(username,
				password);

		if (rs.isSuccess()) {
			SystemUser sysUser = rs.getDefaultModel();
			if (null == sysUser) {
				return "login";
			}
			setSession(request, sysUser);
			//
			return "main";

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
		if (null != session
				&& null != session
						.getAttribute(WebConstants.SONINGBO_USER_SESSION)) {
			session.invalidate();
		}
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String backHome(HttpServletRequest request) throws Exception {
		return SessionHandler.verifySession(request, "main");
	}

	private void setSession(HttpServletRequest request, SystemUser sysUser) {
		HttpSession session = request.getSession(false);
		session.setAttribute(WebConstants.SONINGBO_USER_SESSION, sysUser);
		session.setMaxInactiveInterval(WebConstants.SESSION_OUT_TIME);
	}

}
