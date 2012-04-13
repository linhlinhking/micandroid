package com.salesmanager.customer.profile;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.common.AjaxResponse;
import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.reference.SystemUrlEntryType;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.customer.CustomerService;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.MessagesListUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class PasswordController extends SalesManagerController {

	private static Logger log = Logger.getLogger(PasswordController.class);
	
	@RequestMapping(value = { "/profile/password.html" }, method = RequestMethod.GET)
	public ModelAndView displayChangePassword(HttpServletRequest request) {

		ModelAndView view = new ModelAndView("changePasswordForm");

		return view;

	}

	@RequestMapping(value = { "/profile/editpassword.html" }, method = RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> changePassword(String currentPassword,
			String newPassword, String newPasswordRepeat,
			HttpServletRequest request) {

		Locale locale = LocaleUtil.getLocale(request);

		AjaxResponse response = new AjaxResponse();
		MessagesListUtil messages = new MessagesListUtil(locale);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		try {

			MerchantStore store = SessionUtil.getMerchantStore(request);

			// check fields

			// new paswords match
			if (StringUtils.isBlank(currentPassword)) {

				modelMap.put("message", "messages.required.currentpassword");
				modelMap.put("success", false);
				return modelMap;
			}

			if (StringUtils.isBlank(newPassword)) {

				modelMap.put("message", "messages.required.newpassword");
				modelMap.put("success", false);
				return modelMap;
			}

			if (StringUtils.isBlank(newPasswordRepeat)) {

				modelMap.put("message", "messages.required.repeatnewpassword");
				modelMap.put("success", false);
				return modelMap;
			}

			if (!newPassword.equals(newPasswordRepeat)) {

				modelMap.put("message", "messages.password.match");
				modelMap.put("success", false);
				return modelMap;

			}

			// 6 to 8 characters
			if (newPassword.length() < 6 || newPassword.length() > 8) {

				modelMap.put("message", "messages.password.length");
				modelMap.put("success", false);
				return modelMap;
			}

			Customer customer = new Customer();
			
			
			if (customer == null) {
				log.error("Customer is null");
				messages.addErrorMessage("errors.technical");
				response.setErrorMessage(messages.getErrorMessage());

				modelMap.put("message", response.getErrorMessage());
				modelMap.put("success", false);
			}
			if (StringUtils.isBlank(customer.getCustomerFirstname())) {
				messages.addErrorMessage("messages.required.firstname");
				response.setHasErrors(true);
			}
			if (StringUtils.isBlank(customer.getCustomerLastname())) {
				messages.addErrorMessage("messages.required.lastname");
				response.setHasErrors(true);
			}

			if (StringUtils.isBlank(customer.getCustomerEmailAddress())) {
				messages.addErrorMessage("messages.invalid.email");
				response.setHasErrors(true);
			}

			if (StringUtils.isBlank(customer.getCustomerBillingState())) {
				messages.addErrorMessage("messages.required.state");
				response.setHasErrors(true);
			}

			if (StringUtils.isBlank(customer.getCustomerStreetAddress())) {
				messages.addErrorMessage("messages.required.setreetaddress");
				response.setHasErrors(true);
			}

			if (StringUtils.isBlank(customer.getCustomerCity())) {

				messages.addErrorMessage("messages.required.city");
				response.setHasErrors(true);
			}

			if (StringUtils.isBlank(customer.getCustomerPostalCode())) {

				messages.addErrorMessage("messages.required.postalcode");
				response.setHasErrors(true);
			}

			if (StringUtils.isBlank(customer.getCustomerTelephone())) {

				messages.addErrorMessage("messages.required.telephone");
				response.setHasErrors(true);

			}

			if (response.isHasErrors()) {
				modelMap.put("message", messages.getErrorMessage());
				modelMap.put("success", false);
				return modelMap;
			}

			CustomerService cservice = (CustomerService) ServiceFactory
					.getService(ServiceFactory.CustomerService);

			Customer tmpCustomer = SessionUtil.getCustomer(request);

			Customer currentCustomer = cservice.getCustomer(tmpCustomer
					.getCustomerId());

			if (currentCustomer == null) {
				log.error("Customer is null for customerId "
						+ customer.getCustomerId());
				modelMap.put("message", messages.getErrorMessage());
				modelMap.put("success", false);
				return modelMap;
			}

			int zoneId = -1;
			try {
				zoneId = Integer.parseInt(customer.getCustomerBillingState());
			} catch (Exception e) {
				// TODO: handle exception
			}

			currentCustomer.setCustomerFirstname(customer
					.getCustomerFirstname());
			currentCustomer.setCustomerLastname(customer.getCustomerLastname());
			currentCustomer.setCustomerEmailAddress(customer
					.getCustomerEmailAddress());
			currentCustomer.setCustomerStreetAddress(customer
					.getCustomerStreetAddress());
			currentCustomer.setCustomerCity(customer.getCustomerCity());
			currentCustomer.setCustomerPostalCode(customer
					.getCustomerPostalCode());
			currentCustomer.setCustomerTelephone(customer
					.getCustomerTelephone());
			currentCustomer.setCustomerBillingCountryId(customer
					.getCustomerBillingCountryId());
			currentCustomer.setCustomerBillingState(customer
					.getCustomerBillingState());
			if (zoneId != -1) {
				currentCustomer.setCustomerBillingZoneId(zoneId);
				currentCustomer.setCustomerBillingState(null);
			}
			if (StringUtils.isBlank(currentCustomer.getCustomerLang())) {
				currentCustomer.setCustomerLang(locale.getLanguage());
			}

			cservice.saveOrUpdateCustomer(currentCustomer,
					SystemUrlEntryType.WEB, locale);
			SessionUtil.setCustomer(currentCustomer, request);

		} catch (Exception e) {
			log.error(e);
			messages.addErrorMessage("errors.technical");
			modelMap.put("message", response.getErrorMessage());
			modelMap.put("success", false);
			return modelMap;

		}

		modelMap.put("success", true);
		return modelMap;

		/*
		 * CustomerLogonModule logon = (CustomerLogonModule)
		 * com.salesmanager.core.util.SpringUtil .getBean("customerLogon");
		 * HttpSession session = getServletRequest().getSession();
		 * 
		 * Customer customer = SessionUtil.getCustomer(super
		 * .getServletRequest());
		 * 
		 * if (customer == null) { super.setTechnicalMessage();
		 * log.error("Customer does not exist in http session"); return INPUT; }
		 * 
		 * // new paswords match if
		 * (StringUtils.isBlank(this.getCurrentPassword())) {
		 * super.addFieldMessage("currentPassword",
		 * "messages.required.currentpassword"); return INPUT; }
		 * 
		 * if (StringUtils.isBlank(this.getNewPassword())) {
		 * super.addFieldMessage("newPassword",
		 * "messages.required.newpassword"); return INPUT; }
		 * 
		 * if (StringUtils.isBlank(this.getRepeatNewPassword())) {
		 * super.addFieldMessage("repeatNewPassword",
		 * "messages.required.repeatnewpassword"); return INPUT; }
		 * 
		 * if (!this.getNewPassword().equals(this.getRepeatNewPassword())) {
		 * super.addFieldMessage("repeatNewPassword",
		 * "messages.password.match"); return INPUT; }
		 * 
		 * // 6 to 8 characters if (this.getNewPassword().length() < 6 ||
		 * this.getNewPassword().length() > 8) {
		 * super.addErrorMessage("messages.password.length"); return INPUT; }
		 * 
		 * logon.resetPassword(customer, getCurrentPassword(),
		 * getNewPassword());
		 * 
		 * SessionUtil.setCustomer(customer, super.getServletRequest());
		 * 
		 * super.setMessage("customer.changepassword.success.message");
		 * 
		 * } catch (ServiceException e) {
		 * 
		 * if (e.getReason() == ErrorConstants.INVALID_CREDENTIALS) {
		 * addActionError
		 * (getText("customer.changepassword.validation.invalid")); } else {
		 * log.error(e); addActionError(getText("errors.technical")); } return
		 * INPUT; } catch (Exception ex) { log.error(ex);
		 * super.setTechnicalMessage(); return INPUT; }
		 * 
		 * return SUCCESS;
		 */

		//return null;
	}

}
