package com.salesmanager.catalog.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.reference.Country;
import com.salesmanager.core.entity.reference.Language;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.cache.RefCache;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;

@Controller
@RequestMapping(value="/reference") 
public class CountryController extends SalesManagerController {
	
	@RequestMapping(value="/countries.html")    
	public @ResponseBody Map<String,? extends Object> getCountries(HttpServletRequest request) throws Exception {
		
		Map<String,Object> modelMap = new HashMap<String,Object>(); 
		ReferenceService rservice = (ReferenceService)ServiceFactory.getService(ServiceFactory.ReferenceService);
		
		Locale locale = LocaleUtil.getLocale(request);
		
		int languageId = LanguageUtil.getLanguageNumberCode(locale.getLanguage());
		

		
		Collection countries = rservice.getCountriesByLanguageId(languageId);
		

		
		
		modelMap.put("data", countries);
		
		return modelMap;
		
	}
	
	/**
	 * Get a list of StateProvince by countryId
	 * @param countryId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/zones.html")
	public @ResponseBody Map<String,? extends Object> getZonesByCountry(@RequestParam int countryId, HttpServletRequest request) throws Exception {

		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		
		Locale locale = LocaleUtil.getLocale(request);
		ReferenceService rservice = (ReferenceService)ServiceFactory.getService(ServiceFactory.ReferenceService);
		
		Language l = LanguageUtil.getLanguageByCode(locale.getLanguage());
		
		Collection zones = rservice.getStateProvinceByCountryIdAndByLanguageId(countryId, l.getLanguageId());

		try{

			modelMap.put("data", zones);


		} catch (Exception e) {

			e.printStackTrace();

			modelMap.put("success", false);

			
		}
		
		return modelMap;
	}


}
