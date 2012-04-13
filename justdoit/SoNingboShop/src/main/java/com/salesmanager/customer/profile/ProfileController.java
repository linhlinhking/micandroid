package com.salesmanager.customer.profile;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.entity.customer.CustomerInfo;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.reference.Country;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.customer.CustomerService;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class ProfileController extends SalesManagerController {
	
	@RequestMapping(value={"/profile/profile.html"}, method=RequestMethod.GET)
	public ModelAndView execute(HttpServletRequest request) throws Exception {
		
		// get customer from HttpSession (login putted Customer in
		// HttpSession)
		Customer customer = SessionUtil.getCustomer(request);
		
		
		ModelAndView view = new ModelAndView("profile");
		view.addObject("customer", customer);

		
		return view;
		
	}
	
	

	


}
