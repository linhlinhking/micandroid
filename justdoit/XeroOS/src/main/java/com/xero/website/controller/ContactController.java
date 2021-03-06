package com.xero.website.controller;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
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

import com.xero.admin.util.XeroApiURLContants;
import com.xero.core.Response.ResponseCollection;
import com.xero.core.Response.ResponseEntity;
import com.xero.core.Response.ResponseMessage;
import com.xero.core.api.SessionAttributes;
import com.xero.core.api.XeroXmlParam;
import com.xero.core.api.server.OAuthServiceProvider;
import com.xero.core.controller.BaseController;
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
			@RequestParam(value = "Name", required = false) String companyName,
			@RequestParam("EmailAddress") String uemail,
			@RequestParam("DefaultNumber") String telephone,
			@RequestParam("userId") Integer userId,
			@RequestParam("groupId") Integer groupId,
			@RequestParam(value = "isXero", required = false) Integer isXero,
			@RequestParam(value = "contactId", required = false) String cId,
			WebRequest request) {
		ResponseEntity<Contact> res = new ResponseEntity<Contact>(false);
		try {
			if (null != isXero && isXero == 1) {
				String jsonString = "";
				// GET the xero's accessToken.
				Token accessToken = (Token) request.getAttribute(
						SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN,
						SCOPE_SESSION);
				if (null != accessToken) {
					String xmlValue = XeroXmlParam.postContactXml(companyName,
							uemail, telephone);
					// If the AccessToken Exists.Get Authorization Service.
					OAuthService service = xeroServiceProvider.getService();
					// Send post request to xero.
					OAuthRequest oauthRequest = new OAuthRequest(Verb.POST,
							XeroApiURLContants.CONTACTS);

					// Accept Response Type by JSON
					oauthRequest.addHeader("Accept", "application/json");
					// Add POST's parameter to xero api.
					oauthRequest.addBodyParameter("xml", xmlValue);
					service.signRequest(accessToken, oauthRequest);
					Response oauthResponse = oauthRequest.send();
					// Get Xero's Response.
					jsonString = oauthResponse.getBody();
				}
				res.setResult(true);
				res.setJson(jsonString);
			} else {
				Contact contact = null;
				/** do Edit Contact by Local database. */
				if ("" != cId && null != cId && StringUtils.isNumeric(cId)) {
					contact = contactService.get(Integer.valueOf(cId));
					if (null != contact) {
						contact.setUpdateDateTime(new Date());
						contact.setCompanyName(companyName);
						contact.setTelephone(telephone);
						contact.setUemail(uemail);
						contact = contactService.saveOrUpdate(contact);
					} else {
						logger.error("No Exists Contact,Contact's ID is " + cId);
						contact = new Contact();
					}

				} else {
					/** do Add Contact by Local database. */
					contact = new Contact();
					contact.setCompanyName(companyName);
					contact.setTelephone(telephone);
					contact.setUemail(uemail);
					contact.setGroupId(groupId);
					contact.setUserId(userId);
					contact.setCreateDateTime(new Date());
					contact = contactService.saveOrUpdate(contact);
				}
				res.setResult(true);
				res.setData(contact);
			}

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
			@RequestParam("groupId") Integer groupId,
			@RequestParam("userId") Integer userId, HttpServletRequest request) {
		ResponseCollection<Contact> res = contactService.queryContactById(
				groupId, userId);
		// if (SessionHandler.verifySession(request)) {
		// return res;
		// }
		// res.setData(null);
		// res.setResult(false);
		// res.setMessage("Authorization Error.");

		return res;
	}

	@RequestMapping(value = "/contact-delete", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public ResponseMessage removeContactsById(
			@RequestParam("contactId") Integer id) {
		ResponseMessage msg = new ResponseMessage();
		try {
			Contact c = contactService.get(id);
			if(null != c){
				c.setDeleted(true) ;
				c = contactService.saveOrUpdate(c) ;
				msg.setResult(true) ;
				msg.setStatusCode(200) ;
				
			}else{
				msg.setResult(false) ;
				msg.setStatusCode(601) ;
			}
			
		} catch (Exception ex) {
			logger.error("Remove Contact Error.Id is " + id, ex);
			msg.setResult(false) ;
			msg.setStatusCode(500) ;
		}
		msg.setUrl("/contact") ;
		
		return msg;
	}

}
