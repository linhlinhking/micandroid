package ningbo.media.system.controller;

import javax.annotation.Resource;

import ningbo.media.entity.SystemUser;
import ningbo.media.system.service.SystemUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class SystemUserController {

	protected final static String REDIRCT_URL = "redirect:/rest/";

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String printUser(@PathVariable("name")
	String name, ModelMap model) {
		model.put("message", "Devon.Ning,Welcome");
		model.addAttribute("interest", name);
		return "index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("systemUser")
	SystemUser u) {
		systemUserService.save(u);
		return "redirect:/success.jsp";
	}

}
