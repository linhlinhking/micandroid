package com.salesmanager.customer.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.entity.customer.CustomerInfo;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.module.model.application.CustomerLogonModule;
import com.salesmanager.core.service.ServiceException;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.customer.CustomerService;
import com.salesmanager.core.util.LabelUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.MessageUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class LogonController extends SalesManagerController {
	
	private static Logger log = Logger.getLogger(LogonController.class);
	
	@RequestMapping(value={"/profile/logout.html"}, method=RequestMethod.GET)
	public String homeLogout(HttpServletRequest request) throws Exception {

		logout(request);
		return "redirect:/homr.html";
		
	}
	
	private void logout(HttpServletRequest request) throws Exception {
		
		CustomerLogonModule logon = (CustomerLogonModule) com.salesmanager.core.util.SpringUtil
		.getBean("customerLogon");
		
		logon.logout(request);
		
		request.getSession().setAttribute("CUSTOMER", null);
		
	}
	
	
	@RequestMapping(value={"/logon.html"}, method=RequestMethod.POST)
	public String homeLogin(HttpServletRequest request) throws Exception {
		return execute(request);
	}
		

	public String execute(HttpServletRequest request) throws Exception {

		Customer customer = null;
		
		if (!validateCustomerLogon(request)) {

			
			
			LabelUtil l = LabelUtil.getInstance();
			Locale locale = LocaleUtil.getLocale(request);
			l.setLocale(locale);
			MessageUtil.addErrorMessage(request, l.getText(
					locale, "login.invalid"));
			return "redirect:/home.html";
			
		}
		try {

			CustomerLogonModule logon = (CustomerLogonModule) com.salesmanager.core.util.SpringUtil
					.getBean("customerLogon");

			// get merchantId
			int merchantId = 1;
			HttpSession session = request.getSession();
			MerchantStore store = (MerchantStore) session.getAttribute("STORE");
			if (store != null) {
				merchantId = store.getMerchantId();
			}

			customer= logon.logon(request, merchantId);
			

			if (customer == null) {

				MessageUtil.addErrorMessage(request, super.getText(request, "login.invalid"));
				return "redirect:/home.html";
				
			}
			
			
			Locale locale = LocaleUtil.getLocale(request);
			customer.setLocale(locale);

			// get CustomerInfo
			CustomerService cservice = (CustomerService) ServiceFactory
					.getService(ServiceFactory.CustomerService);
			CustomerInfo customerInfo = cservice.findCustomerInfoById(customer
					.getCustomerId());

			if (customerInfo == null) {
				customerInfo = new CustomerInfo();
				customerInfo.setCustomerInfoId(customer.getCustomerId());
			}

			Integer login = customerInfo.getCustomerInfoNumberOfLogon();
			login = login + 1;
			customerInfo.setCustomerInfoNumberOfLogon(login);
			cservice.saveOrUpdateCustomerInfo(customerInfo);

			SessionUtil.setCustomer(customer, request);
			request.setAttribute("CUSTOMER", customer);
			

			
			return "redirect:/profile/profile.html";
			
		} catch (ServiceException e) {

			MessageUtil.addErrorMessage(request, super.getText(request, "login.invalid"));
			return "redirect:/home.html";
			
			
		} catch (Exception ex) {
			log.error(ex);
			throw ex;

		}
	}
	
	@RequestMapping(value={"/password/reset.html"}, method=RequestMethod.POST)
	public String resetPassword(HttpServletRequest request) throws Exception {
		
		
		String userName = request.getParameter(
				"resetpasswordusername");
		CustomerService cservice = (CustomerService) ServiceFactory
				.getService(ServiceFactory.CustomerService);

		MerchantStore store = SessionUtil
				.getMerchantStore(request);
		Customer customer = cservice.findCustomerByUserName(userName, store
				.getMerchantId());

		if (customer != null) {
			cservice.resetCustomerPassword(customer);
		}

		super.setMessage("label.customer.passwordreset", request);
		

		return "redirect:/home.html";
		
		
	}
	
	private boolean validateCustomerLogon(HttpServletRequest request) {
		boolean isValid = true;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtils.isBlank(username)) {
			isValid = false;
		}
		if (StringUtils.isBlank(password)) {
			isValid = false;
		}
		return isValid;
	}
	

}
