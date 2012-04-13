package com.salesmanager.catalog.store;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.core.constants.ConfigurationConstants;
import com.salesmanager.core.constants.LabelConstants;
import com.salesmanager.core.entity.merchant.MerchantConfiguration;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.reference.DynamicLabelDescription;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.merchant.ConfigurationRequest;
import com.salesmanager.core.service.merchant.ConfigurationResponse;
import com.salesmanager.core.service.merchant.MerchantService;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class ContactController {
	
	@RequestMapping(value={"/contact.html"}, method=RequestMethod.GET)
	public ModelAndView execute(HttpServletRequest request) throws Exception {
		
		ModelAndView model = new ModelAndView("contact");
		
		
		MerchantStore store = SessionUtil.getMerchantStore(request);



			request.setAttribute("pageId", "contact");
			// get contact us properties & map
			MerchantService mservice = (MerchantService) ServiceFactory
					.getService(ServiceFactory.MerchantService);
			ConfigurationRequest crequest = new ConfigurationRequest(store
					.getMerchantId(), ConfigurationConstants.CONTACTUS);
			ConfigurationResponse response = mservice.getConfiguration(crequest);

			MerchantConfiguration conf = response
					.getMerchantConfiguration(ConfigurationConstants.CONTACTUS);

			if (conf != null) {

				// display google map
				String mapConf = conf.getConfigurationValue1();
				if (mapConf != null && mapConf.equalsIgnoreCase("true")) {
					model.addObject("displayMap",new Boolean(true));
				}

				// display custom address
				String basicConf = conf.getConfigurationValue();
				if (basicConf != null && basicConf.equalsIgnoreCase("true")) {
					model.addObject("displayAddress", new Boolean(true));
				}

			}

			ReferenceService rservice = (ReferenceService) ServiceFactory
					.getService(ServiceFactory.ReferenceService);
			DynamicLabelDescription label = rservice
					.getDynamicLabelDescription(store.getMerchantId(),
							LabelConstants.STORE_FRONT_CONTACT_US, LocaleUtil.getLocale(request));
			if (label != null) {
				model.addObject("description", label.getDynamicLabelDescription());
			}


		return model;
		
	}

}
