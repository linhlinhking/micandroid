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
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.controller.BaseController;
import com.xero.core.web.WebConstants;
import com.xero.website.bean.Project;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.bean.type.ProjectType;
import com.xero.website.service.ProjectService;
import com.xero.website.service.ProjectSupplierService;

@Controller
public class ProjectController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ProjectService projectService;

	@Resource
	private ProjectSupplierService projectSupplierService;

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
			Integer pId = sysUser.getPlanId() ;
			if (ep.before(new Date()) && pId == 1) {
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
			@RequestParam("poNumber")String poNumber,
			@RequestParam("customerId") String customerId,
			@RequestParam("customerName") String customerName,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(required = false, value = "supplierId") String supplierId,
			@RequestParam("supplierName") String supplierName,
			@RequestParam("supplierLang") String supplierLanguage,
			@RequestParam("userId") Integer userId) {
		ResponseEntity<Project> res = new ResponseEntity<Project>(false);
		try {

			Project p = new Project();
			p.setProjectName(proName);
			p.setCustomerId(customerId);
			p.setCustomerName(customerName);
			p.setCreateDateTime(new Date());
			Date sDate = (startDate != null) ? DateUtil.strToEnDate(startDate)
					: null;
			Date eDate = (endDate != null) ? DateUtil.strToEnDate(endDate)
					: null;
			p.setStartDate(sDate);
			p.setEndDate(eDate);
			p.setStatus(ProjectType.ACTIVE.toString());
			p.setUserId(userId) ;
			p.setPoNumber(poNumber) ;

			p = projectService.saveOrUpdate(p);

			Integer projectId = p.getId();
			ProjectSupplier ps = new ProjectSupplier();
			ps.setProjectId(projectId);
			ps.setSupplierId(supplierId);
			ps.setSupplierName(supplierName);
			ps.setSupplierLanguage(supplierLanguage);
			

			projectSupplierService.saveOrUpdate(ps);

			res.setResult(true);
			res.setData(p);
		} catch (Exception ex) {
			logger.error("Save Project Error.", ex);
			res.setData(null);
		}
		return res;
	}

	@RequestMapping(value = "/project-list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseCollection<Project> getAllProject(
			HttpServletRequest request, @RequestParam("userId") Integer userId) {
		return projectService.getProjectsById(userId);
	}

	@RequestMapping(value = "/project-detail", method = RequestMethod.GET)
	public ModelAndView toProject(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/project_detail");
		return model;
	}

}
