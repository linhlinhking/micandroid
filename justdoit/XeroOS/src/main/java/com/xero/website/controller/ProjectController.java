package com.xero.website.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xero.admin.util.DateUtil;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.controller.BaseController;
import com.xero.website.bean.Project;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.bean.type.ProjectType;
import com.xero.website.service.CompanyService;
import com.xero.website.service.ProjectService;
import com.xero.website.service.ProjectSupplierService;

@Controller
public class ProjectController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ProjectService projectService;

	@Resource
	private ProjectSupplierService projectSupplierService;

	@Resource
	private CompanyService companyService;

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/project");
		return model;
	}

	@RequestMapping(value = "/slide-add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Project> updateSlide(HttpServletRequest request,
			@RequestParam("projectId") Integer projectId,
			@RequestParam("rateValue") String rateValue) {
		ResponseEntity<Project> res = new ResponseEntity<Project>(false);
		try {
			Project p = projectService.get(projectId);
			if (null == rateValue || rateValue.length() < 0) {
				rateValue = "0.0";
			}
			p.setRate(Double.valueOf(rateValue));
			p.setUpdateDateTime(new Date());
			p = projectService.saveOrUpdate(p);

			res.setResult(true);
			res.setData(p);
		} catch (Exception ex) {
			logger.error("Save Slide Error,current projectId is " + projectId,
					ex);
			res.setData(null);
			res.setResult(false);
		}

		return res;
	}

	@RequestMapping(value = "/project-add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Project> doProject(
			HttpServletRequest request,
			@RequestParam("proName") String proName,
			@RequestParam("poNumber") String poNumber,
			@RequestParam("customerId") String customerId,
			@RequestParam("customerName") String customerName,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(required = false, value = "supplierId") String supplierId,
			@RequestParam("supplierName") String supplierName,
			@RequestParam("supplierLang") String supplierLanguage,
			@RequestParam("userId") Integer userId,
			@RequestParam("companyId") Integer companyId) {
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
			p.setUserId(userId);
			p.setPoNumber(poNumber);
			companyId = (null != companyId && StringUtils.isNumeric(String
					.valueOf(companyId))) ? companyId : Integer.valueOf(0);
			
			p.setCompanyId(companyId);

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

	@RequestMapping(value = "/project-detail/{poNumber}", method = RequestMethod.GET)
	public ModelAndView toProject(HttpServletRequest request,
			@PathVariable("poNumber") String poNumber) {
		ModelAndView model = new ModelAndView();
		Project pro = projectService.get(Project.PO_NUMBER, poNumber);
		if (null != pro) {
			Date tempSDate = pro.getStartDate();
			Date tempEDate = pro.getEndDate();
			tempSDate = (tempSDate == null) ? new Date() : tempSDate;
			tempEDate = (tempEDate == null) ? new Date() : tempEDate;

			String sDate = DateUtil.dateToString(tempSDate);
			String eDate = DateUtil.dateToString(tempEDate);

			model.addObject("sDate", sDate);
			model.addObject("eDate", eDate);
			model.addObject("project", pro);

		}
		model.setViewName("/project_detail");
		return model;
	}

}
