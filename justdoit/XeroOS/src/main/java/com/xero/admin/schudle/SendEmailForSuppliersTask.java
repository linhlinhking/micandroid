package com.xero.admin.schudle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xero.admin.bean.type.MailType;
import com.xero.admin.service.SystemUserService;
import com.xero.core.Response.ResponseCollection;
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

@Component
public class SendEmailForSuppliersTask {

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

	/**
	 * 
	 * (cron="0 0 6 ? * Tue")
	 */
	// @Scheduled(cron = "0/20 * *  * * ? ")
	public void doSomethingWithDelay() {
		ResponseCollection<Project> resProjects = projectService
				.getActiveProjects();
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
					if (resSuppliers.getResult()) {
						List<ProjectSupplier> listSupplier = resSuppliers
								.getData();
						if (null != listSupplier && listSupplier.size() > 0) {
							for (int n = 0, m = listSupplier.size(); n < m; n++) {
								ProjectSupplier itemSupplier = listSupplier
										.get(n);
								String supplierId = itemSupplier
										.getSupplierId();
								Integer tmpId = (supplierId == null) ? 0
										: Integer.valueOf(supplierId);

								Integer linkID = itemSupplier.getId();

								String language = itemSupplier
										.getSupplierLanguage();
								Contact c = contactService.get(tmpId);
								if (null != c) {
									String supplierEmail = c.getUemail();
									String companyName = c.getCompanyName();

									/** Send and Save Email Records */
									EmailRecord er = new EmailRecord();
									er.setReply(false);
									er = emailRecordService.saveOrUpdate(er);

									/**
									 * email'id Trace the supplier write note or
									 * not
									 */
									Integer emailId = er.getId();

									StringBuffer buffer = new StringBuffer();
									buffer.append(supplierId).append(":")
											.append(pId).append(":")
											.append(emailId).append(":")
											.append(linkID);
									
									
									String dataEncode = EncodeUtil
											.base64UrlSafeEncode(buffer
													.toString().getBytes());

									// String linkUrl =
									// "http://dev.globaldesign.co.nz/supplier/"
									// + dataEncode;

									String linkUrl = "https://globaldesign.co.nz/supplier/"
											+ dataEncode;

									System.out.println(supplierEmail);
									Map<String, Object> params = new HashMap<String, Object>();
									params.put("customerCompanyName",
											customerName);
									params.put("supplierCompanyName",
											companyName);
									params.put("poNumber", pNumber);
									params.put("linkUrl", linkUrl);

									boolean flag = sendMgrService.sendHtmlMail(
											MailType.MAILSUPPLIERS,
											supplierEmail, language, params);

									er.setProjectId(pId);
									er.setSendDate(new Date());
									er.setSupplierId(supplierId);
									if (flag) {
										er.setStatus(true);
									} else {
										er.setStatus(false);
									}
									emailRecordService.saveOrUpdate(er);
									System.out.println("Send finish.");

								} else {
									System.out.println("No Contact");
								}

							}
						}

					} else {
						System.out.println("No ProjectSuppliers.");
					}

				}
			}
		}

		System.out.println("Send OK.");
	}
}
