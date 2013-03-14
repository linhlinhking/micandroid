package com.xero.website.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xero.admin.util.DateUtil;
import com.xero.core.Response.ResponseCollection;
import com.xero.website.bean.Contact;
import com.xero.website.bean.EmailRecord;
import com.xero.website.bean.Project;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.bean.Reports;
import com.xero.website.service.CompanyService;
import com.xero.website.service.ContactService;
import com.xero.website.service.EmailRecordService;
import com.xero.website.service.ProjectService;
import com.xero.website.service.ProjectSupplierService;

@Controller
public class ReportController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private CompanyService companyService;

	@Resource
	private ProjectService projectService;

	@Resource
	private ProjectSupplierService projectSupplierService;

	@Resource
	private EmailRecordService emailRecordService;

	@Resource
	private ContactService contactService;

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/report");
		return model;
	}

	@RequestMapping(value = "/report-list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseCollection<Reports> getAllReports(
			HttpServletRequest request,
			@RequestParam("companyId") Integer companyId) {
		ResponseCollection<Reports> res = new ResponseCollection<Reports>();
		try {
			res.setData(getReportsByCompany(companyId));
			res.setResult(true);
		} catch (Exception ex) {
			logger.error("Get Reports Error.", ex);
			res.setData(null);
			res.setResult(false);
		}
		return res;
	}

	private List<Reports> getReportsByCompany(Integer companyId) {
		List<Reports> reps = new ArrayList<Reports>();

		/** Get projects by current user */
		ResponseCollection<Project> resProject = projectService
				.getActiveProjectsByCompany(companyId);

		if (resProject.getResult()) {
			List<Project> listProject = resProject.getData();
			if (null != listProject && listProject.size() > 0) {
				for (int i = 0, j = listProject.size(); i < j; i++) {
					Project p = listProject.get(i);
					Integer pId = p.getId();
					String poNumber = p.getPoNumber();
					String proName = p.getProjectName();

					/** Get Supplier by current project */
					ResponseCollection<ProjectSupplier> resSuppliers = projectSupplierService
							.getSuppliersByProjectId(pId);

					if (resSuppliers.getResult()) {
						/** Get All Suppliers By this project */
						List<ProjectSupplier> listSuppliers = resSuppliers
								.getData();
						if (null != listSuppliers && listSuppliers.size() > 0) {
							for (int m = 0, n = listSuppliers.size(); m < n; m++) {
								ProjectSupplier ps = listSuppliers.get(m);
								String tmpSId = ps.getSupplierId();

								Integer sId = (tmpSId == null || !StringUtils
										.isNumeric(tmpSId)) ? 0 : Integer
										.valueOf(tmpSId);

								/** Get All No reply Records By current supplier */
								ResponseCollection<EmailRecord> listEmail = emailRecordService
										.getRecordsNoReply(tmpSId);

								if (listEmail.getResult()) {
									List<EmailRecord> records = listEmail
											.getData();
									if (null != records && records.size() > 0) {
										for (EmailRecord rec : records) {
											Reports report = new Reports();
											// Integer proId =
											// rec.getProjectId() ;
											Date sendDate = rec.getSendDate();
											Integer daysDue = DateUtil
													.daysBetweenDate(sendDate,
															new Date());
											Contact c = contactService.get(sId);
											if (null != c) {
												report.setTelephone(c
														.getTelephone());
												report.setSupplier(c
														.getCompanyName());
												report.setSupplierEmail(c
														.getUemail());
											}
											report.setDaysOverdue(daysDue);
											report.setPoNumber(poNumber);
											report.setProjectName(proName);

											reps.add(report);
										}
									}
								}

							}

						}

					}
				}
			}

		}
		return reps;
	}
}
