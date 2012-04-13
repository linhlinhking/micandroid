package com.salesmanager.customer.profile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.common.AjaxResponse;
import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.entity.customer.CustomerInfo;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.reference.Country;
import com.salesmanager.core.entity.reference.SystemUrlEntryType;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.customer.CustomerService;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.MessagesListUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class AddressController extends SalesManagerController {
	
	private static Logger log = Logger.getLogger(AddressController.class);
	
	@RequestMapping(value={"/profile/address.html"}, method=RequestMethod.GET)
	public ModelAndView displayAddress(HttpServletRequest request) throws Exception {
		
		// get customer from HttpSession (login putted Customer in
		// HttpSession)
		Customer customer = SessionUtil.getCustomer(request);
		

		// get CustomerInfo
		CustomerService cservice = (CustomerService) ServiceFactory
				.getService(ServiceFactory.CustomerService);
		CustomerInfo customerInfo = cservice.findCustomerInfoById(customer
				.getCustomerId());
		
		ModelAndView view = new ModelAndView("displayAddress");
		view.addObject("customer", customer);
		view.addObject("customerInfo", customerInfo);
		
		return view;
		
	}
	
	@RequestMapping(value={"/profile/editaddress.html"}, method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> editAddress(Customer customer, HttpServletRequest request) {
		
		Locale locale = LocaleUtil.getLocale(request);
		
		AjaxResponse response = new AjaxResponse();
		MessagesListUtil messages = new MessagesListUtil(locale);
		
		Map<String,Object> modelMap = new HashMap<String,Object>();
		
		try {
			
			
			MerchantStore store = SessionUtil.getMerchantStore(request);

			// check fields

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
			
			if(response.isHasErrors()) {
				modelMap.put("message", messages.getErrorMessage());
				modelMap.put("success", false);
				return modelMap;
			}

			CustomerService cservice = (CustomerService) ServiceFactory
					.getService(ServiceFactory.CustomerService);
			
			
			Customer tmpCustomer = SessionUtil.getCustomer(request);
			
			Customer currentCustomer = cservice.getCustomer(tmpCustomer.getCustomerId());
			
			if(currentCustomer==null) {
				log.error("Customer is null for customerId " + customer.getCustomerId());
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
			
			currentCustomer.setCustomerFirstname(customer.getCustomerFirstname());
			currentCustomer.setCustomerLastname(customer.getCustomerLastname());
			currentCustomer.setCustomerEmailAddress(customer.getCustomerEmailAddress());
			currentCustomer.setCustomerStreetAddress(customer.getCustomerStreetAddress());
			currentCustomer.setCustomerCity(customer.getCustomerCity());
			currentCustomer.setCustomerPostalCode(customer.getCustomerPostalCode());
			currentCustomer.setCustomerTelephone(customer.getCustomerTelephone());
			currentCustomer.setCustomerBillingCountryId(customer.getCustomerBillingCountryId());
			currentCustomer.setCustomerBillingState(customer.getCustomerBillingState());
			if(zoneId!=-1) {
				currentCustomer.setCustomerBillingZoneId(zoneId);
				currentCustomer.setCustomerBillingState(null);
			}
			if(StringUtils.isBlank(currentCustomer.getCustomerLang())) {
				currentCustomer.setCustomerLang(locale.getLanguage());
			}
			
			cservice.saveOrUpdateCustomer(currentCustomer, SystemUrlEntryType.WEB,
					locale);
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
		
		
	}
	
	
	@ModelAttribute("countryList")   
	public Collection<Country> populateCountryList(HttpServletRequest request) { 
		
		Collection countries = null;
		
		try {
			
			ReferenceService rservice = (ReferenceService)ServiceFactory.getService(ServiceFactory.ReferenceService);
			
			Locale locale = LocaleUtil.getLocale(request);
			
			int languageId = LanguageUtil.getLanguageNumberCode(locale.getLanguage());

			countries = rservice.getCountriesByLanguageId(languageId);
			
			return countries;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return countries;
		

	}

}
