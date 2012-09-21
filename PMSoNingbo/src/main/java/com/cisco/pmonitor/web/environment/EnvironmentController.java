package com.cisco.pmonitor.web.environment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
@RequestMapping("/environment")
public class EnvironmentController {

	@RequestMapping(value = "/environment_view", method = RequestMethod.GET)
	public String toView(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "environment/environment_view");
	}
}
