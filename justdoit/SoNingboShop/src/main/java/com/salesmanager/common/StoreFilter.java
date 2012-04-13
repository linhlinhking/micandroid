package com.salesmanager.common;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;

import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.reference.Language;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.cache.RefCache;
import com.salesmanager.core.service.merchant.MerchantService;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.www.SalesManagerPrincipalProxy;





/**
 * Servlet Filter implementation class StoreFilter
 */
public class StoreFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StoreFilter() {
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
    /**
     * Set attributes
     * STORE
     * templateId
     * principal
     */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println(" ***** In the filter *****");
		

		try {
						HttpServletRequest request = (HttpServletRequest)req;
						HttpServletResponse response = (HttpServletResponse)resp;
						request.setCharacterEncoding("UTF-8");

						// get cookies
						Map cookiesMap = new HashMap();
						Cookie[] cookies = request.getCookies();
						
/*						if (cookies != null) {
							for (int i = 0; i < cookies.length; i++) {
								Cookie cookie = cookies[i];
								cookiesMap.put(cookie.getName(), cookie);
							}
						}*/


						// look at merchantId in url parameter
						String merchantId = (String) request.getParameter("merchantId");

						Cookie storeCookie = (Cookie) cookiesMap.get("STORE");

						int iMerchantId = Constants.DEFAULT_MERCHANT_ID;
						MerchantStore store = null;

						if (StringUtils.isBlank(merchantId)) {// no merchantId in the
																// request

							// check for store
							store = (MerchantStore) request.getSession().getAttribute("STORE");

							if (merchantId == null) {

								if (store != null) {

									iMerchantId = store.getMerchantId();
								} else {
									// check for cookie
									Cookie c = (Cookie) cookiesMap.get("STORE");
									if (c != null && !StringUtils.isBlank(c.getValue())) {

										String v = c.getValue();
										iMerchantId = Integer.valueOf(v);
									} else {
										// assign defaultMerchantId
										iMerchantId = Constants.DEFAULT_MERCHANT_ID;
									}
									// set store
									store = this.setMerchantStore(request, response, merchantId);
									if (store == null) {
										response.sendRedirect(request.getContextPath() + "/components/nostore.jsp");
										return;
									}
								}
							}

						} else {// merchantId in the request

							// check for store in the session
							store = (MerchantStore) request.getSession().getAttribute("STORE");

							if (store != null) {
								// check if both match
								if (!merchantId.equals(String
										.valueOf(store.getMerchantId()))) {// if they differ
									store = this.setMerchantStore(request, response, merchantId);
								} else {
									iMerchantId = store.getMerchantId();
								}

							} else {
								// set store
								store = this.setMerchantStore(request, response, merchantId);
								if (store == null) {
									response.sendRedirect(request.getContextPath() + "/components/nostore.jsp");
									return;
								}
							}
						}

						request.setAttribute("STORE", store);

						if (StringUtils.isBlank(store.getTemplateModule())) {
							response.sendRedirect(request.getContextPath() + "/components/nostore.jsp");
							return;
						}

						request.setAttribute("templateId", store.getTemplateModule());

						//ActionContext ctx = ActionContext.getContext();
						//LocaleUtil.setLocaleForRequest(request, response, store);

						HttpSession session = request.getSession();
						Principal p = (Principal) session.getAttribute("PRINCIPAL");

						if (p != null) {
							try {
								SalesManagerPrincipalProxy proxy = new SalesManagerPrincipalProxy(p);
								request.setAttribute("principal", p);
								//BaseActionAware action = ((BaseActionAware) invoke.getAction());
								//action.setPrincipalProxy(proxy);
							} catch (Exception e) {
								//log
										//.error("The current action does not implement PrincipalAware "
										//		+ invoke.getAction().getClass());
							}
						}

						//String r = baseIntercept(invoke, req, resp);

						//if (r != null) {
						//	return r;
						//}

						//return invoke.invoke();
						


					} catch (Exception e) {
						
						Log.error(e);
						
						//log.error(e);
						//ActionSupport action = (ActionSupport) invoke.getAction();
						//action.addActionError(action.getText("errors.technical") + " "
						//		+ e.getMessage());
						//if (e instanceof ActionException) {
						//	return Action.ERROR;
						//} else {
						//	return "GENERICERROR";
						//}

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
	
	private MerchantStore setMerchantStore(HttpServletRequest req,
			HttpServletResponse resp, String merchantId) throws Exception {

		// different merchantId
		int iMerchantId = 1;

		try {
			iMerchantId = Integer.parseInt(merchantId);
		} catch (Exception e) {
			//log.error("Cannot parse merchantId to Integer " + merchantId);
		}

		// get MerchantStore
		MerchantService mservice = (MerchantService) ServiceFactory
				.getService(ServiceFactory.MerchantService);
		MerchantStore mStore = mservice.getMerchantStore(iMerchantId);

		if (mStore == null) {
			// forward to error page
			//log.error("MerchantStore does not exist for merchantId "
			//		+ merchantId);
			return null;
		}
		
		if (mStore.getLanguages() == null || mStore.getLanguages().size() == 0) {

			// languages
			if (!StringUtils.isBlank(mStore.getSupportedlanguages())) {
				List languages = new ArrayList();
				List langs = LanguageUtil.parseLanguages(mStore
						.getSupportedlanguages());
				for (Object o : langs) {
					String lang = (String) o;
					Locale locale = new Locale(lang);
					Language l = LanguageUtil.getLanguageByCode(lang);
					if (l != null) {
						l.setLocale(locale, mStore.getCurrency());
						languages.add(l);
					}
				}
				mStore.setLanguages(languages);
			}
		}

		req.getSession().setAttribute("STORE", mStore);
		req.setAttribute("STORE", mStore);
		
		//get store configuration for template
		ReferenceService rservice = (ReferenceService) ServiceFactory
					.getService(ServiceFactory.ReferenceService);
		Map storeConfiguration = rservice.getModuleConfigurationsKeyValue(
					mStore.getTemplateModule(), mStore.getCountry());
	
		if (storeConfiguration != null) {
				req.getSession().setAttribute("STORECONFIGURATION",
						storeConfiguration);
		}

		Cookie c = new Cookie("STORE", merchantId);
		c.setMaxAge(365 * 24 * 60 * 60);
		resp.addCookie(c);
		
		if(!RefCache.isLoaded()) {
			RefCache.createCache();
		}

		return mStore;

	}

}
