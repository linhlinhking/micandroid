package com.xero.website.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xero.core.Response.ResponseCollection;
import com.xero.website.bean.Project;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.bean.Reports;
import com.xero.website.service.CompanyService;
import com.xero.website.service.ProjectService;
import com.xero.website.service.ProjectSupplierService;

@Controller
public class ReportController {

	@Resource
	private CompanyService companyService;

	private ProjectService projectService;

	@Resource
	private ProjectSupplierService projectSupplierService;

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/report");
		return model;
	}

	@RequestMapping(value = "/report-list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseCollection<Reports> getAllReports() {
		ResponseCollection<Reports> res = new ResponseCollection<Reports>();
		
		
		return res;
	}

	private List<Reports> getReportsByUserId(Integer userId) {
		ResponseCollection<Project> resProject = projectService
				.getProjectsById(userId);
		if (resProject.getResult()) {
			List<Project> listProject = resProject.getData();
			if (null != listProject && listProject.size() > 0) {
				for (int i = 0, j = listProject.size(); i < j; i++) {
					Project p = listProject.get(i);
					Integer pId = p.getId();

					ResponseCollection<ProjectSupplier> resSuppliers = projectSupplierService
							.getSuppliersByProjectId(pId);

					if (resSuppliers.getResult()) {
						List<ProjectSupplier> listSuppliers = resSuppliers
								.getData();

					}
				}
			}

		}
		return null;
	}
}
