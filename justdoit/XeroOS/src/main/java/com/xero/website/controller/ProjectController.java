package com.xero.website.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.bean.SystemUser;
import com.xero.admin.util.DateUtil;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.controller.BaseController;
import com.xero.core.web.WebConstants;
import com.xero.website.bean.Project;
import com.xero.website.service.ProjectService;

@Controller
public class ProjectController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ProjectService projectService;

	@RequestMapping(value = "/project", method = RequestMethod.GET)
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
				model.setViewName("/project");
			}
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}

	
	@RequestMapping(value = "/project-add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Project> doProject(
			HttpServletRequest request,
			@RequestParam("proName") String proName,
			@RequestParam("customerId") String customerId,
			@RequestParam("startDate") String startDate,
			@RequestParam("startDate") String endDate,
			@RequestParam(required = false, value = "suppliers") String supplierIds) {
		ResponseEntity<Project> res = new ResponseEntity<Project>(false);
		try {
			
		
			Project p = new Project();
			p.setProjectName(proName);
			p.setCustomerId(customerId);
			p.setCreateDateTime(new Date());
			Date sDate = (startDate != null) ? DateUtil.strToEnDate(startDate)
					: null;
			Date eDate = (endDate != null) ? DateUtil.strToEnDate(endDate)
					: null;
			p.setStartDate(sDate);
			p.setEndDate(eDate);

			p = projectService.saveOrUpdate(p);

			res.setResult(true);
			res.setData(p);
		} catch (Exception ex) {
			logger.error("Save Project Error.", ex);
			res.setData(null);
		}
		return res;
	}
}
