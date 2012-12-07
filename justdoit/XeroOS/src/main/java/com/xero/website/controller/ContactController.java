package com.xero.website.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.xero.admin.bean.type.ContactType;
import com.xero.admin.util.XeroApiURLContants;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.api.server.OAuthServiceProvider;
import com.xero.core.controller.BaseController;
import com.xero.core.web.SessionHandler;
import com.xero.website.bean.Contact;
import com.xero.website.service.ContactService;

@Controller
public class ContactController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ContactService contactService;

	@Autowired
	@Qualifier("xeroServiceProvider")
	private OAuthServiceProvider xeroServiceProvider;

	@RequestMapping(value = "/contact-add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Contact> doAdd(
			@RequestParam(value = "companyName", required = false) String companyName,
			@RequestParam("uemail") String uemail,
			@RequestParam("telephone") String telephone,
			@RequestParam("userId") Integer userId,
			@RequestParam("groupId") Integer groupId) {
		ResponseEntity<Contact> res = new ResponseEntity<Contact>(false);
		try {
			Contact contact = new Contact();
			contact.setCompanyName(companyName);
			contact.setTelephone(telephone);
			contact.setUemail(uemail);
			contact.setGroupId(groupId);
			contact.setUserId(userId);
			contact.setCreateDateTime(new Date());
			contact = contactService.saveOrUpdate(contact);
			res.setResult(true);
			res.setData(contact);

		} catch (Exception ex) {
			logger.error("Save Contact Error.", ex);
			res.setData(null);
		}

		return res;
	}

	@RequestMapping(value = "/contact-xero", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public String getContactsByXero(WebRequest request,
			NativeWebRequest nativeRequest) {
		OAuthService service = xeroServiceProvider.getService();
		String jsonString = signXeroApi(request, XeroApiURLContants.CONTACTS,
				service, Verb.GET);
		return jsonString;
	}

	@RequestMapping(value = "/contact-list", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public ResponseCollection<Contact> getContactsById(
			@RequestParam("id") Integer id,
			@RequestParam("userId") Integer userId,
			@RequestParam(value = "type", required = false) String type,
			HttpServletRequest request) {
		ContactType currentType = null;
		if (null != type && type.equalsIgnoreCase("customer")) {
			currentType = ContactType.CUSTOMER;
		} else if (type.equalsIgnoreCase("supplier")) {
			currentType = ContactType.SUPPLIER;
		} else {
			currentType = ContactType.ALL;
		}

		ResponseCollection<Contact> res = contactService.queryContactById(id,
				userId, currentType);
		if (SessionHandler.verifySession(request)) {
			return res;
		}

		res.setData(null);
		res.setResult(false);
		res.setMessage("Authorization Error.");

		return res;
	}
}
