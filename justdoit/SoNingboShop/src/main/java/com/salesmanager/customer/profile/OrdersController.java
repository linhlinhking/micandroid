package com.salesmanager.customer.profile;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.catalog.common.PageCount;
import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.orders.SearchOrderResponse;
import com.salesmanager.core.entity.orders.SearchOrdersCriteria;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.order.OrderService;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.PropertiesUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class OrdersController extends SalesManagerController {
	
	
	
	private Logger log = Logger.getLogger(OrdersController.class);
	private static Configuration config = PropertiesUtil.getConfiguration();


	private static int size = 10;

	static {

		size = config.getInt("catalog.orderlist.maxsize", 10);

	}
	
	@RequestMapping(value={"/profile/orders.html"}, method=RequestMethod.GET)
	public ModelAndView displayOrders(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView("displayOrders");
		return model;

	}
	
	

	
	@RequestMapping(value={"/profile/pageOrders.html"}, method=RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> pageOrders(int start, int limit, HttpServletRequest request) {
		
		
		Map<String,Object> modelMap = new HashMap<String,Object>();
		
		try {
			
			PageCount pageCount = new PageCount();
			pageCount.setSize(size);
			pageCount.setPageStartCount(start);
			pageCount.processStartNumber();
			pageCount.setPageElements();


			SearchOrdersCriteria criteria = getCriteria(request,pageCount.getPageCriteriaIndex());
			
			Locale locale = LocaleUtil.getLocale(request);
			OrderService oservice = (OrderService) ServiceFactory
			.getService(ServiceFactory.OrderService);

			SearchOrderResponse resp = oservice.searchOrdersByCustomer(criteria);
			if (resp != null) {
				Collection orders = resp.getOrders();
				
				modelMap.put("data", orders);
				modelMap.put("totalCount", resp.getCount());

				LocaleUtil.setLocaleToEntityCollection(orders, locale);

				pageCount.setListingCount(resp.getCount());
				pageCount.setRealCount(orders.size());
				pageCount.setPageElements();
			}

			
			

		} catch (Exception e) {
			log.error(e);
			modelMap.put("success", false);

		}
		
		
		return modelMap;
		
	}
	
	
	
	private SearchOrdersCriteria getCriteria(HttpServletRequest request, int startIndex) {

		MerchantStore store = SessionUtil.getMerchantStore(request);

		SearchOrdersCriteria criteria = new SearchOrdersCriteria();

		Customer customer = SessionUtil.getCustomer(request);
		
		Locale locale = LocaleUtil.getLocale(request);

		// 12 months
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH, -12);

		criteria.setSdate(calendar.getTime());
		criteria.setEdate(new Date());

		criteria.setLanguageId(LanguageUtil.getLanguageNumberCode(locale.getLanguage()));
		criteria.setMerchantId(store.getMerchantId());
		criteria.setCustomerId(customer.getCustomerId());
		criteria.setQuantity(size);
		//criteria.setStartindex(startIndex);
		criteria.setStartcount(startIndex);

		return criteria;

	}
	

}
