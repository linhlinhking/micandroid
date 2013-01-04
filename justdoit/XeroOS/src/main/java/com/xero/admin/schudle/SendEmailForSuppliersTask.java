package com.xero.admin.schudle;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
								Contact c = contactService.get(tmpId);
								if (null != c) {
									String supplierEmail = c.getUemail();
									String companyName = c.getCompanyName();

									StringBuffer buffer = new StringBuffer();
									buffer.append(supplierId).append(":")
											.append(pId);
									String dataEncode = EncodeUtil
											.base64UrlSafeEncode(buffer
													.toString().getBytes());

									String linkUrl = "http://dev.globaldesign.co.nz/supplier/"
											+ dataEncode;

									// String linkUrl =
									// "http://localhost:9000/supplier/"
									// + dataEncode;

									System.out.println(supplierEmail);
									boolean flag = sendMgrService.sendHtmlMail(
											supplierEmail, companyName,
											pNumber, linkUrl);

									/** Send and Save Email Records */
									EmailRecord er = new EmailRecord();
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
