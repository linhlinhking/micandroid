package com.salesmanager.customer.profile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.reference.Country;
import com.salesmanager.core.entity.reference.CountryDescription;
import com.salesmanager.core.entity.reference.SystemUrlEntryType;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.customer.CustomerService;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.CountryUtil;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.MessagesListUtil;
import com.salesmanager.core.util.PropertiesUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class RegistrationController extends SalesManagerController {
	
	
	private static Logger log = Logger.getLogger(RegistrationController.class);
	
	/**
	 * Displays registration form
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/registration.html"}, method=RequestMethod.GET)
	public ModelAndView displayRegistration(HttpServletRequest request) throws Exception {
		
		
		ModelAndView model = new ModelAndView("register");
		

		MerchantStore store = SessionUtil.getMerchantStore(request);
		Integer merchantid = store.getMerchantId();
		
		int shippingCountryId = PropertiesUtil.getConfiguration().getInt(
				"core.system.defaultcountryid", Constants.US_COUNTRY_ID);
		
		Locale locale = LocaleUtil.getLocale(request);
		String countryCode = locale.getCountry();
		
		if (!StringUtils.isBlank(countryCode)) {
			CountryDescription country = CountryUtil.getCountryByIsoCode(
					countryCode, locale);
			shippingCountryId = country.getId().getCountryId();
		}

		
		Customer c = new Customer();
		c.setCustomerCountryId(shippingCountryId);
		c.setCustomerBillingCountryId(shippingCountryId);
		model.addObject("customer", c);
		

		
		return model;
		
		
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
	
	@RequestMapping(value={"/register.html"}, method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> register(Customer customer, HttpServletRequest request) {

		
		Locale locale = LocaleUtil.getLocale(request);
		
		AjaxResponse response = new AjaxResponse();
		MessagesListUtil messages = new MessagesListUtil(locale);
		
		Map<String,Object> modelMap = new HashMap<String,Object>();

		try {

			
			MerchantStore store = SessionUtil.getMerchantStore(request);
			
			
			// check fields

			if (customer == null) {
				log.error("Customer is null");
				//response.addErrorMessage("errors.technical");
				
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

			if (StringUtils.isBlank(customer.getCustomerEmailAddressRepeat())) {
				messages.addErrorMessage("messages.invalid.email");
				response.setHasErrors(true);
			} else {

				if (!customer.getCustomerEmailAddressRepeat().equals(
						customer.getCustomerEmailAddress())) {
					messages.addErrorMessage("messages.invalid.email");
					response.setHasErrors(true);
				}
			
			}


			if (StringUtils.isBlank(customer.getCustomerBillingState())) {
					messages.addErrorMessage("messages.required.state");
					response.setHasErrors(true);
			}

			if(response.isHasErrors()) {
				response.setErrorMessage(messages.getErrorMessage());
				modelMap.put("message", response.getErrorMessage());
				modelMap.put("success", false);
				return modelMap;
			}

			CustomerService cservice = (CustomerService) ServiceFactory
					.getService(ServiceFactory.CustomerService);

			// check if email address already exist
			Customer tmpCustomer = cservice.findCustomerByUserName(customer
					.getCustomerEmailAddress(), store.getMerchantId());
			if (tmpCustomer != null) {
				// user already exist, display reset password message
				messages.addErrorMessage("messages.customer.alreadyexist");
				response.setErrorMessage(messages.getErrorMessage());
				modelMap.put("message", response.getErrorMessage());
				
				modelMap.put("success", false);
				return modelMap;
			}
			
			
			int zoneId = -1;
			try {
				zoneId = Integer.parseInt(customer.getCustomerBillingState());
			} catch (Exception e) {
				// TODO: handle exception
			}

			customer.setMerchantId(store.getMerchantId());
			customer.setCustomerBillingCountryId(customer.getCustomerBillingCountryId());
			customer.setCustomerBillingState(customer.getCustomerBillingState());
			if(zoneId!=-1) {
				customer.setCustomerBillingZoneId(zoneId);
				customer.setCustomerBillingState(null);
			}
			customer.setCustomerAnonymous(false);
			customer.setCustomerLang(locale.getLanguage());

			// telephone, address, city and postal code are req in the db but
			// not during registration
			// so here is a dummy string
			customer.setCustomerTelephone("---");
			customer.setCustomerPostalCode("---");
			customer.setCustomerStreetAddress("---");
			customer.setCustomerCity("---");

			//cservice.saveOrUpdateCustomer(customer,
					//SystemUrlEntryType.WEB, locale);

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

}
