package com.salesmanager.catalog.store;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.reference.DynamicLabel;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.PropertiesUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class ContentController extends SalesManagerController {

	private static org.apache.commons.configuration.Configuration config = PropertiesUtil
	.getConfiguration();
	
	@RequestMapping(value="/content/{page}.html",method=RequestMethod.GET)
	public ModelAndView execute(@PathVariable("page") String pageName,HttpServletRequest request) throws Exception {
		
		ModelAndView model = new ModelAndView("contentpage");
		

		
		String url = pageName;
		
		Locale locale = LocaleUtil.getLocale(request);
		
		request.setAttribute("pageId", url);
		MerchantStore store = (MerchantStore) SessionUtil
				.getMerchantStore(request);
		ReferenceService rservice = (ReferenceService) ServiceFactory
				.getService(ServiceFactory.ReferenceService);
		DynamicLabel label = rservice.getDynamicLabelByMerchantIdAndSeUrlAndLanguageId(
				store.getMerchantId(), url, LocaleUtil.getLocale(request));

		if (label == null) {
			
			DynamicLabel l = rservice.getDynamicLabelByMerchantIdAndSeUrl(store.getMerchantId(), url);
			if(l!=null) {
				label = rservice.getDynamicLabel(store.getMerchantId(),l.getTitle(),locale); 
			}
			if(locale==null) {
				throw new Exception("Dynamic Label is null");
			}
		}
		
		
		
		model.addObject("pageTitle",label.getDynamicLabelDescription().getDynamicLabelTitle());
		model.addObject("label",label);


		
		return model;
		
	}
}
