package com.salesmanager.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import com.salesmanager.catalog.common.PortletConfiguration;
import com.salesmanager.checkout.util.MiniShoppingCartUtil;
import com.salesmanager.core.constants.CatalogConstants;
import com.salesmanager.core.constants.ConfigurationConstants;
import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.constants.LabelConstants;
import com.salesmanager.core.constants.ShippingConstants;
import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.entity.merchant.MerchantConfiguration;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.orders.ShoppingCart;
import com.salesmanager.core.entity.reference.CoreModuleService;
import com.salesmanager.core.entity.reference.CountryDescription;
import com.salesmanager.core.entity.reference.DynamicLabel;
import com.salesmanager.core.entity.shipping.ShippingEstimate;
import com.salesmanager.core.entity.shipping.ShippingType;
import com.salesmanager.core.module.model.application.CacheModule;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.merchant.ConfigurationRequest;
import com.salesmanager.core.service.merchant.ConfigurationResponse;
import com.salesmanager.core.service.merchant.MerchantService;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.MerchantConfigurationUtil;
import com.salesmanager.core.util.MiniShoppingCartSerializationUtil;
import com.salesmanager.core.util.ShippingUtil;
import com.salesmanager.core.util.SpringUtil;
import com.salesmanager.core.util.StringUtil;
import com.salesmanager.core.util.www.SessionUtil;

/**
 * Servlet Filter implementation class StoreTemplateFilter
 */
public class StoreTemplateFilter implements Filter {
	
	private Logger log = Logger.getLogger(StoreTemplateFilter.class);

    /**
     * Default constructor. 
     */
    public StoreTemplateFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		request.setCharacterEncoding("UTF-8");
		
		/** remove profile url **/
		//@todo
		//request.getSession().removeAttribute("profileUrl");
		
		
		try {
			

		
		

		/** synchronize mini shopping cart**/
		
		//get http session shopping cart
		ShoppingCart cart = SessionUtil.getMiniShoppingCart(request);
		MerchantStore mStore = SessionUtil.getMerchantStore(request);
		Locale locale = LocaleUtil.getLocale(request);
		
		if(cart==null) {//synch only when the cart is null or empty
		
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if(cookie.getName().equals(CatalogConstants.SKU_COOKIE + mStore.getMerchantId())) {
						
							
							String cookieValue = StringUtil.unescape(cookie.getValue());
							
							ShoppingCart sc = MiniShoppingCartSerializationUtil.deserializeJSON(cookieValue, mStore, locale);
							if(sc!=null) {
							
								MiniShoppingCartUtil.calculateTotal(sc,mStore);
								SessionUtil.setMiniShoppingCart(sc, request);
								
							} else {//expire cookie
								cookie.setValue(null);
								cookie.setMaxAge(0);
								response.addCookie(cookie);
							}
					}
				}
			}
		}
		
		//store activities related to template display


		Map<Integer, List> customPortletsMap = null;

		CacheModule cache = (CacheModule) SpringUtil.getBean("cache");
		ReferenceService rservice = (ReferenceService) ServiceFactory
				.getService(ServiceFactory.ReferenceService);

		ConfigurationResponse vo = null;

		try {
			vo = (ConfigurationResponse) cache.getFromCache(
					Constants.CACHE_CONFIGURATION, mStore);
		} catch (Exception ignore) {

		}

		if (vo == null) {

			/**
			 * MERCHANT CONFIGURATION
			 */
			ConfigurationRequest r = new ConfigurationRequest(mStore
					.getMerchantId());// get all configurations
			MerchantService mservice = (MerchantService) ServiceFactory
					.getService(ServiceFactory.MerchantService);
			vo = mservice.getConfiguration(r);

			if (vo != null) {

				try {
					cache.putInCache(Constants.CACHE_CONFIGURATION, vo,
							Constants.CACHE_CONFIGURATION, mStore);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}

		ShippingType shippingType = null;
		String shippingModule = null;// will determine shipping company name
		String shippingRegionLine = null;
		String shippingEstimateLine = null;

		if (vo != null && vo.getMerchantConfigurationList() != null
				&& vo.getMerchantConfigurationList().size() > 0) {
			List configurations = vo.getMerchantConfigurationList();

			Iterator i = configurations.iterator();
			Map confMap = new HashMap();
			Collection prtList = new ArrayList();
			while (i.hasNext()) {

				MerchantConfiguration conf = (MerchantConfiguration) i.next();

				if (conf.getConfigurationKey().equals(
						ConfigurationConstants.STORE_PORTLETS_)) {

					Collection portletsList = MerchantConfigurationUtil
							.getConfigurationList(conf.getConfigurationValue(),
									";");
					if (portletsList != null && portletsList.size() > 0) {

						Iterator ii = portletsList.iterator();
						while (ii.hasNext()) {
							String p = (String) ii.next();
							PortletConfiguration pc = (PortletConfiguration) confMap
									.get(p);
							if (pc == null) {
								pc = new PortletConfiguration();
								pc.setModuleName(p);
								confMap.put(p, p);
							}
							prtList.add(pc);
						}
					}
					request.setAttribute("STORE_FRONT_PORTLETS_MAP", confMap);//this one is used by tag lib for getting portlet configuration
					request.setAttribute("STORE_FRONT_PORTLETS", prtList);
					continue;
				}

				else if (conf.getConfigurationKey().equals(
						ConfigurationConstants.G_API)) {
					if (!StringUtils.isBlank(conf.getConfigurationValue())) {
						request.setAttribute("ANALYTICS", conf
								.getConfigurationValue());
					}
					if (!StringUtils.isBlank(conf.getConfigurationValue1())) {
						request
								.setAttribute("G_API", conf
										.getConfigurationValue1());
					}
					continue;
				}

				else if (conf.getConfigurationKey().equals(
						ShippingConstants.MODULE_SHIPPING_RT_MODULE_INDIC_NAME)) {
					if (!StringUtils.isBlank(conf.getConfigurationValue1())) {
						shippingModule = conf.getConfigurationValue1();
					}
					continue;
				}

				else if (conf
						.getConfigurationKey()
						.equals(
								ShippingConstants.MODULE_SHIPPING_INDIC_COUNTRIES_COSTS)) {
					if (!StringUtils.isBlank(conf.getConfigurationValue())
							&& conf.getConfigurationValue().equals("true")) {
						if (!StringUtils.isBlank(conf.getConfigurationValue1())) {
							shippingRegionLine = conf.getConfigurationValue1();
						}
					}
					continue;
				}

				else if (conf.getConfigurationKey().equals(
						ShippingConstants.MODULE_SHIPPING_ESTIMATE_BYCOUNTRY)) {
					if (!StringUtils.isBlank(conf.getConfigurationValue1())) {
						shippingEstimateLine = conf.getConfigurationValue1();
					}
					continue;
				}

				else if (conf.getConfigurationKey().equals(
						ShippingConstants.MODULE_SHIPPING_ZONES_SHIPPING)) {
					if (!StringUtils.isBlank(conf.getConfigurationValue())) {
						if (ShippingConstants.INTERNATIONAL_SHIPPING
								.equals(conf.getConfigurationValue())) {
							shippingType = ShippingType.INTERNATIONAL;
						} else if (ShippingConstants.DOMESTIC_SHIPPING
								.equals(conf.getConfigurationValue())) {
							shippingType = ShippingType.NATIONAL;
						}
					}
					continue;
				}
			}// end while

			ShippingEstimate estimate = new ShippingEstimate();
			estimate.setLocale(locale);
			estimate.setCurrency(mStore.getCurrency());
			estimate.setShippingType(shippingType);
			estimate.setShippingModule(shippingModule);

			if (!StringUtils.isBlank(shippingRegionLine)) {
				Map m = ShippingUtil.buildShippingPriceRegionMap("",
						shippingRegionLine, shippingEstimateLine);
				estimate.setRegions(m);
			}

			String currentCountryIsoCode = locale.getCountry();

			CountryDescription zoneDescription = rservice
					.getCountryDescriptionByIsoCode(currentCountryIsoCode,
							LanguageUtil.getLanguageNumberCode(locale
									.getLanguage()));
			CountryDescription storeZoneDescription = rservice
					.getCountryDescriptionByCountryId(mStore.getCountry(),
							LanguageUtil.getLanguageNumberCode(locale
									.getLanguage()));
			if (zoneDescription != null) {
				estimate.setCustomerCountry(zoneDescription.getCountryName());
			}
			estimate.setStoreCountry(storeZoneDescription.getCountryName());
			if (!StringUtils.isBlank(shippingModule)) {
				CoreModuleService cms = rservice.getCoreModuleService(
						currentCountryIsoCode, shippingModule);
				if (cms != null) {
					estimate.setShippingCompanyLogo(cms
							.getCoreModuleServiceLogoPath());
				}
			}

			// iterate and get the index
			if (!StringUtils.isBlank(shippingRegionLine)) {
				StringTokenizer cvtk = new StringTokenizer(shippingRegionLine,
						"|");

				int index = 1;
				boolean countryFound = false;
				while (cvtk.hasMoreTokens() && !countryFound) {

					String countryline = cvtk.nextToken();// maxpound:price,maxpound:price...|
					if (!countryline.equals("*")) {
						StringTokenizer countrystk = new StringTokenizer(
								countryline, ";");
						String country = null;
						while (countrystk.hasMoreTokens()) {
							country = countrystk.nextToken();
							if (currentCountryIsoCode != null
									&& country.equals(currentCountryIsoCode)) {
								estimate.setCustomerZoneIndex(index);
								countryFound = true;
								break;
							}
						}
					}
					index++;
				}
			}

			/**
			 * Labels required for the 
			 * Custom portlets SECTION 75
			 * Shipping fees 02
			 * Custom page links 70
			 * Since those are displayed on each page, need to get them on each request
			 */

			Collection<DynamicLabel> dynamicLabels = null;

			try {
				dynamicLabels = (Collection) cache.getFromCache(
						Constants.CACHE_LABELS + "_" + LabelConstants.STORE_FRONT_CUSTOM_PORTLETS + "_" + locale.getLanguage(),
						mStore);
			} catch (Exception ignore) {

			}

			if (dynamicLabels == null) {

				// get from missed
				boolean missed = false;
				try {
					missed = (Boolean) cache.getFromCache(
							Constants.CACHE_LABELS + "_" + LabelConstants.STORE_FRONT_CUSTOM_PORTLETS + "_MISSED_" + locale.getLanguage(), mStore);
				} catch (Exception ignore) {

				}

				if (!missed) {

					// get all dynamic labels
					// get shipping, custom portlets, custom pages [section_id in]
					
					List sections = new ArrayList();
					sections.add(LabelConstants.SHIPPING_FEES_SECTION);
					sections.add(LabelConstants.STORE_FRONT_CUSTOM_PAGES);
					sections.add(LabelConstants.STORE_FRONT_CUSTOM_PORTLETS);
					
					dynamicLabels = rservice.getDynamicLabels(mStore
							.getMerchantId(), sections, locale);

					if (dynamicLabels != null && dynamicLabels.size() > 0) {

						try {
							cache.putInCache(Constants.CACHE_LABELS + "_" + LabelConstants.STORE_FRONT_CUSTOM_PORTLETS + "_" + locale.getLanguage(), dynamicLabels,
									Constants.CACHE_LABELS, mStore);
						} catch (Exception e) {
							log.error(e);
						}

					} else {

						try {
							cache.putInCache(Constants.CACHE_LABELS + "_" + LabelConstants.STORE_FRONT_CUSTOM_PORTLETS + "_MISSED_" + locale.getLanguage(), true,
									Constants.CACHE_LABELS, mStore);
						} catch (Exception e) {
							log.error(e);
						}

					}

				}// end missed

			}

			if (dynamicLabels != null && dynamicLabels.size() > 0) {

				Iterator it = dynamicLabels.iterator();
				List topNavList = new ArrayList();
				List customPortlets = new ArrayList();
				while (it.hasNext()) {

					DynamicLabel dl = (DynamicLabel) it.next();
					if (dl.getSectionId() == LabelConstants.SHIPPING_FEES_SECTION) {
						estimate.setDefaultShippingEstimateText(dl
								.getDynamicLabelDescription()
								.getDynamicLabelDescription());
						continue;
					}

					// custom pages
					if (dl.getSectionId() == LabelConstants.STORE_FRONT_CUSTOM_PAGES) {
						topNavList.add(dl);
						continue;
					}

					// custom portlets
					// set in a map [title, dl]
					if (dl.getSectionId() == LabelConstants.STORE_FRONT_CUSTOM_PORTLETS) {
						if (dl.isVisible()) {
							if (customPortletsMap == null) {
								customPortletsMap = new HashMap();
							}
							PortletConfiguration pc = new PortletConfiguration();
							pc.setContent(dl.getDynamicLabelDescription()
									.getDynamicLabelDescription());
							pc.setCustom(true);
							pc.setPosition(dl.getPosition());
							pc.setModuleName(dl.getTitle());
							List portlets = customPortletsMap.get(dl
									.getPosition());
							if (portlets == null) {
								portlets = new ArrayList();
								customPortletsMap.put(dl.getPosition(),
										portlets);
							}
							portlets.add(pc);
						}
						continue;
					}
				}
				if(topNavList.size()>0) {
					request.setAttribute("TOPNAV", topNavList);
				}
				if (customPortletsMap.size()>0) {
					request.setAttribute("CUSTOMPORTLETS", customPortletsMap);
				}
			}
			request.setAttribute("SHIPPING", estimate);

		}// end if
		
		
		
		//set page title in httpservlet request
		request.setAttribute("pageTitle", mStore.getStorename());
		


		/** set objects in request **/
		Customer customer = SessionUtil.getCustomer(request);
		if (customer != null) {
			req.setAttribute("CUSTOMER", customer);
		}


		req.setAttribute("merchantId", mStore.getMerchantId());

		/**
		 * Messages
		 */
/*		String errMessage = MessageUtil.getErrorMessage(request);
		if (!StringUtils.isBlank(errMessage)) {
			SalesManagerBaseAction action = (SalesManagerBaseAction) invoke
					.getAction();
			action.addActionError(errMessage);
		}

		String message = MessageUtil.getMessage(req);
		if (!StringUtils.isBlank(message)) {
			SalesManagerBaseAction action = (SalesManagerBaseAction) invoke
					.getAction();
			action.addActionMessage(message);
		}
		MessageUtil.resetMessages(req);*/
		
		} catch (Exception e) {
			log.error(e);
		}


		// pass the request along the filter chain
		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
