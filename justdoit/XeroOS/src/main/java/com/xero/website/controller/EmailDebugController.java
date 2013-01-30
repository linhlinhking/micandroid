package com.xero.website.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xero.admin.bean.type.MailType;
import com.xero.admin.service.SystemUserService;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseMessage;
import com.xero.core.email.SendManagerService;
import com.xero.core.util.ApplicationContextUtil;
import com.xero.core.util.encode.EncodeUtil;
import com.xero.website.bean.Contact;
import com.xero.website.bean.EmailRecord;
import com.xero.website.bean.Project;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.service.ContactService;
import com.xero.website.service.EmailRecordService;
import com.xero.website.service.ProjectService;
import com.xero.website.service.ProjectSupplierService;

@Controller
public class EmailDebugController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private ProjectService projectService;

	@Resource
	private ProjectSupplierService projectSupplierService;

	@Resource
	private ContactService contactService;

	@Resource
	private EmailRecordService emailRecordService;

	private SendManagerService sendMgrService = (SendManagerService) ApplicationContextUtil
			.getContext().getBean("sendMail");

	@RequestMapping(value = "/email-debug", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage sendDebugEmail(HttpServletRequest request) {
		ResponseMessage msg = new ResponseMessage();
		try {
			ResponseCollection<Project> resProjects = projectService
					.getActiveProjects();
			System.out.println("resProjects:"
					+ String.valueOf(resProjects.getResult()));
			if (resProjects.getResult()) {
				List<Project> listProject = resProjects.getData();

				if (null != listProject && listProject.size() > 0) {
					for (int i = 0, j = listProject.size(); i < j; i++) {
						Project itemProject = listProject.get(i);
						Integer pId = itemProject.getId();
						String customerName = itemProject.getCustomerName();
						String pNumber = itemProject.getPoNumber();

						ResponseCollection<ProjectSupplier> resSuppliers = projectSupplierService
								.getSuppliersByProjectId(pId);

						System.out.println("resSuppliers:"
								+ String.valueOf(resSuppliers.getResult()));
						if (resSuppliers.getResult()) {
							List<ProjectSupplier> listSupplier = resSuppliers
									.getData();
							if (null != listSupplier && listSupplier.size() > 0) {
								for (int n = 0, m = listSupplier.size(); n < m; n++) {
									ProjectSupplier itemSupplier = listSupplier
											.get(n);
									String supplierId = itemSupplier
											.getSupplierId();

									System.out.println("supplierId:"
											+ supplierId);
									Integer tmpId = (supplierId == null) ? 0
											: Integer.valueOf(supplierId);
									String language = itemSupplier
											.getSupplierLanguage();
									Contact c = contactService.get(tmpId);
									if (null != c) {
										String supplierEmail = c.getUemail();
										String companyName = c.getCompanyName();

										/** Send and Save Email Records */
										EmailRecord er = new EmailRecord();
										er.setReply(false);
										er = emailRecordService
												.saveOrUpdate(er);

										/**
										 * email'id Trace the supplier write
										 * note or not
										 */
										Integer emailId = er.getId();

										StringBuffer buffer = new StringBuffer();
										buffer.append(supplierId).append(":")
												.append(pId).append(":")
												.append(emailId);
										String dataEncode = EncodeUtil
												.base64UrlSafeEncode(buffer
														.toString().getBytes());

										String linkUrl = "http://gdp.nalike.com/supplier/"
												+ dataEncode;

										System.out.println(supplierEmail);

										Map<String, Object> params = new HashMap<String, Object>();
										params.put("customerCompanyName",
												customerName);
										params.put("supplierCompanyName",
												companyName);
										params.put("poNumber", pNumber);
										params.put("linkUrl", linkUrl);
										boolean flag = sendMgrService
												.sendHtmlMail(
														MailType.MAILSUPPLIERS,
														supplierEmail,
														language, params);

										er.setProjectId(pId);
										er.setSendDate(new Date());
										er.setSupplierId(supplierId);
										if (flag) {
											er.setStatus(true);
										} else {
											er.setStatus(false);
										}
										emailRecordService.saveOrUpdate(er);
										msg.setResult(true);

										System.out.println("Send finish.");

									} else {
										System.out.println("No Contact");
										msg.setResult(false);
										msg.setMessage("No Contant.");
									}

								}
							} else {
								msg.setResult(false);
								msg.setMessage("No Suppliers' data.");
							}

						} else {
							msg.setResult(false);
							msg.setMessage("No ProjectSuppliers");
						}

					}
				}
			} else {
				msg.setResult(false);
				msg.setMessage("No Projects' Data.");
			}

			System.out.println("Send OK.");
		} catch (Exception ex) {
			logger.error(
					"Google Translate Time out or Database Connection timeout.",
					ex);
			msg.setResult(false);
			msg.setMessage("Connection Timeout.");
		}

		return msg;
	}
}
