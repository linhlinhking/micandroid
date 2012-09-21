package com.cisco.pmonitor.web.topology;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
@RequestMapping("topology")
public class TopologyController {


	@RequestMapping(value = "/topology_view", method = RequestMethod.GET)
	public String toView(HttpServletRequest request) {
		
		return SessionHandler.verifySession(request, "topology/topology_view");
	}
}
