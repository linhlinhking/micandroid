package com.xero.website.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.xero.admin.bean.type.MailType;
import com.xero.admin.util.DateUtil;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.email.SendManagerService;
import com.xero.core.util.ApplicationContextUtil;
import com.xero.core.util.encode.EncodeUtil;
import com.xero.website.bean.Contact;
import com.xero.website.bean.EmailRecord;
import com.xero.website.bean.Project;
import com.xero.website.bean.ProjectNote;
import com.xero.website.bean.ProjectSupplier;
import com.xero.website.service.ContactService;
import com.xero.website.service.EmailRecordService;
import com.xero.website.service.ProjectNoteService;
import com.xero.website.service.ProjectService;
import com.xero.website.service.ProjectSupplierService;

@Controller
public class ProjectNoteController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ProjectNoteService projectNoteService;

	@Resource
	private ProjectService projectService;

	@Resource
	private EmailRecordService emailRecordService;

	@Resource
	private ContactService contactService;

	@Resource
	private ProjectSupplierService projectSupplierService;

	private SendManagerService sendMgrService = (SendManagerService) ApplicationContextUtil
			.getContext().getBean("sendMail");

	@RequestMapping(value = "/update-customer", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProjectNote> updateShowCustomer(
			HttpServletRequest request, @RequestParam("noteId") Integer noteId,
			@RequestParam("showCustomer") Integer showCustomer) {
		ResponseEntity<ProjectNote> res = new ResponseEntity<ProjectNote>(false);
		try {
			ProjectNote pNote = projectNoteService.get(noteId);
			if (null != pNote) {
				boolean isShowCustomer = false;
				if (null != showCustomer && showCustomer == 1) {
					isShowCustomer = true;
				}
				pNote.setShowCustomer(isShowCustomer);
				pNote = projectNoteService.saveOrUpdate(pNote);

				res.setData(pNote);
				res.setResult(true);
			} else {
				res.setResult(false);
				res.setData(null);

			}
		} catch (Exception ex) {
			logger.error("Update Project Note Error On Controller", ex);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}

	@RequestMapping(value = "/note-add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProjectNote> doAdd(
			HttpServletRequest request,
			@RequestParam("projectId") Integer projectId,
			@RequestParam("userId") Integer userId,
			@RequestParam("creator") String creator,
			@RequestParam("content") String noteContent,
			@RequestParam("landmarkDate") String landmarkDate,
			@RequestParam("showCustomer") Integer showCustomer,
			@RequestParam(required = false, value = "supplierId") Integer supplierId,
			@RequestParam(required = false, value = "emailId") Integer emailId) {
		ResponseEntity<ProjectNote> res = new ResponseEntity<ProjectNote>(false);
		try {
			ProjectNote pNote = new ProjectNote();

			Date tempDate = (null == landmarkDate) ? new Date() : DateUtil
					.strToEnDate(landmarkDate);
			boolean isShowCustomer = false;
			if (null != showCustomer && showCustomer == 1) {
				isShowCustomer = true;
			}
			pNote.setContent(noteContent);
			pNote.setProjectId(projectId);
			pNote.setUserId(userId);
			pNote.setCreator(creator);
			pNote.setLandmarkDate(tempDate);
			pNote.setShowCustomer(isShowCustomer);
			pNote.setCreateDateTime(new Date());
			pNote.setSupplierId(supplierId);

			/** if the supplier add note .modify the emailrecord */
			pNote = projectNoteService.saveOrUpdate(pNote);
			if (null != emailId && emailId != 0) {
				EmailRecord record = emailRecordService.get(emailId);
				record.setReply(true);
				emailRecordService.saveOrUpdate(record);
			}

			res.setData(pNote);
			res.setResult(true);

		} catch (Exception ex) {
			logger.error("Save Project Note Error On Controller", ex);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}

	@RequestMapping(value = "/note-list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseCollection<ProjectNote> toList(HttpServletRequest request,
			@RequestParam("projectId") Integer projectId) {
		ResponseCollection<ProjectNote> res = new ResponseCollection<ProjectNote>();
		try {
			res = projectNoteService.getNotesByProjectId(projectId, false);
		} catch (Exception ex) {
			logger.error("Save Project Note Error On Controller", ex);
			res.setResult(false);
			res.setData(null);
		}
		return res;
	}

	@RequestMapping(value = "/supplier/{dataEncode}", method = RequestMethod.GET)
	public ModelAndView toSupplier(HttpServletRequest request,
			@PathVariable("dataEncode") String dataEncode) {
		ModelAndView model = new ModelAndView();
		try {
			if (null != dataEncode && dataEncode.length() > 0) {
				byte[] data = EncodeUtil.base64Decode(dataEncode);

				String newData = new String(data);
				String[] dataArry = newData.split(":");
				if (null != dataArry && dataArry.length > 0) {

					String tempSupplierId = dataArry[0];
					String tempProId = dataArry[1];
					String tmpEmailId = dataArry[2];
					String tmpLinkID = dataArry[3] ;

					Integer projectId = (tempProId == null || !StringUtils
							.isNumeric(tempProId)) ? 0 : Integer
							.valueOf(tempProId);

					Integer emailId = (tmpEmailId == null || !StringUtils
							.isNumeric(tmpEmailId)) ? 0 : Integer
							.valueOf(tmpEmailId);

					Integer supplierId = (tempSupplierId == null || !StringUtils
							.isNumeric(tempSupplierId)) ? 0 : Integer
							.valueOf(tempSupplierId);
					
					Integer linkID = (tmpLinkID == null || !StringUtils
							.isNumeric(tmpLinkID)) ? 0 : Integer
							.valueOf(tmpLinkID);

					Contact c = contactService.get(supplierId);
					String supplierName = "";
					if (null != c) {
						supplierName = c.getUemail();
					}else{
						ProjectSupplier ps = projectSupplierService.get(linkID) ;
						supplierName = ps.getSupplierName() ;
					}

					model.addObject("supplierId", supplierId);
					model.addObject("supplierName", supplierName);
					Project p = projectService.get(projectId);
					if (null != p) {
						Date tempSDate = p.getStartDate();
						Date tempEDate = p.getEndDate();

						tempSDate = (tempSDate == null) ? new Date()
								: tempSDate;
						tempEDate = (tempEDate == null) ? new Date()
								: tempEDate;

						String sDate = DateUtil.dateToString(tempSDate);
						String eDate = DateUtil.dateToString(tempEDate);

						model.addObject("sDate", sDate);
						model.addObject("eDate", eDate);
						model.addObject("emailId", emailId);
						model.addObject("project", p);
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Format Error", ex);
		}
		model.setViewName("/project_detail_supply");
		return model;
	}

	@RequestMapping(value = "/report/{projectId}/supplier", method = RequestMethod.GET)
	@ResponseBody
	public boolean mailToSupplier(HttpServletRequest request,
			@PathVariable("projectId") Integer projectId) {
		boolean flag = false;
		try {
			// Get Current Project.
			Project p = projectService.get(projectId);
			String poNumber = "";
			String supplierEmail = "";
			String companyName = "";
			String customerName = "";

			boolean linkXero = false;

			if (null != p) {
				projectId = p.getId();
				linkXero = p.isLinkXero();
				poNumber = p.getPoNumber();
				customerName = p.getProjectName();
			}

			ResponseCollection<ProjectSupplier> resSuppliers = projectSupplierService
					.getSuppliersByProjectId(projectId);
			if (resSuppliers.getResult()) {
				List<ProjectSupplier> listSupplier = resSuppliers.getData();
				if (null != listSupplier && listSupplier.size() > 0) {
					for (int n = 0, m = listSupplier.size(); n < m; n++) {
						ProjectSupplier itemSupplier = listSupplier.get(n);
						String supplierId = itemSupplier.getSupplierId();

						Integer tmpId = (supplierId == null || !StringUtils
								.isNumeric(supplierId)) ? 0 : Integer
								.valueOf(supplierId);

						Integer linkID = 0;

						String language = itemSupplier.getSupplierLanguage();
						// Send Email report to.
						EmailRecord er = new EmailRecord();
						if (linkXero) {
							er.setReply(false);
							er = emailRecordService.saveOrUpdate(er);
							supplierEmail = itemSupplier.getSupplierEmail();
							companyName = itemSupplier.getSupplierName();
							linkID = itemSupplier.getId();
						} else {
							Contact c = contactService.get(tmpId);
							if (null != c) {
								companyName = c.getCompanyName();
								supplierEmail = c.getUemail();
								er.setReply(false);
								er = emailRecordService.saveOrUpdate(er);
							} else {
								// TODO:Contact No Exists.
								// return false;
							}
						}
						// link xero supplierId = 0
						Integer emailId = er.getId();
						StringBuffer buffer = new StringBuffer();
						buffer.append(supplierId).append(":").append(projectId)
								.append(":").append(emailId).append(":")
								.append(linkID);
						
						String dataEncode = EncodeUtil
								.base64UrlSafeEncode(buffer.toString()
										.getBytes());

						String linkUrl = "https://globaldesign.co.nz/supplier/"
								+ dataEncode;
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("customerCompanyName", customerName);
						params.put("supplierCompanyName", companyName);
						params.put("poNumber", poNumber);
						params.put("linkUrl", linkUrl);
						flag = sendMgrService.sendHtmlMail(
								MailType.MAILSUPPLIERS, supplierEmail,
								language, params);

						er.setProjectId(projectId);
						er.setSendDate(new Date());
						er.setSupplierId(supplierId);

						if (flag) {
							er.setStatus(true);
						} else {
							er.setStatus(false);
						}
						emailRecordService.saveOrUpdate(er);
					}
				} else {
					// TODO:No Supplier Data.
					logger.error("No Supplier Data.");
					flag = false;
				}
			} else {
				// TODO:Get Supplier Error.
				logger.error("Get Supplier Error");
				flag = false;
			}

		} catch (Exception ex) {
			logger.error("Project'ID is not number Or No Exists.", ex);
			flag = false;
		}

		return flag;
	}

	@RequestMapping(value = "/report/{projectId}/customer", method = RequestMethod.GET)
	@ResponseBody
	public boolean mailToCustomer(HttpServletRequest request,
			@PathVariable("projectId") Integer projectId) {
		boolean flag = false;
		ResponseCollection<ProjectNote> res = new ResponseCollection<ProjectNote>();
		try {
			// Get Current Project.
			Project p = projectService.get(projectId);
			String customerEmail = "";
			String projectName = "";
			if (null != p) {
				customerEmail = p.getCustomerEmail();
				projectId = p.getId();
				projectName = p.getProjectName();
			}

			res = projectNoteService.getNotesByProjectId(projectId, true);
			if (res.getResult()) {
				List<ProjectNote> notes = res.getData();
				if (null != notes && notes.size() > 0) {
					Map<String, Object> params = new HashMap<String, Object>();
					if ("" != customerEmail) {
						params.put("projectName", projectName);
						params.put("projectNotes", notes);
						sendMgrService.sendHtmlMail(MailType.MAILCUSTOMER,
								customerEmail, null, params);
						// sendMgrService.sendHtmlCollectionMail(MailType.MAILCUSTOMER,
						// customerEmail, params);
						flag = true;
					} else {
						logger.error("Send Email to Customer Error.The Email is Error.Email is "
								+ customerEmail);
					}

				}
			}
		} catch (Exception ex) {
			logger.error("Project'ID is not number Or No Exists.", ex);
			flag = false;
		}

		return flag;
	}
}
