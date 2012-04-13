package com.salesmanager.customer.profile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import com.salesmanager.core.entity.catalog.Review;
import com.salesmanager.core.entity.catalog.ReviewVO;
import com.salesmanager.core.entity.catalog.SearchReviewCriteria;
import com.salesmanager.core.entity.catalog.SearchReviewResponse;
import com.salesmanager.core.entity.customer.Customer;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.orders.SearchOrderResponse;
import com.salesmanager.core.entity.orders.SearchOrdersCriteria;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.catalog.CatalogService;
import com.salesmanager.core.service.order.OrderService;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.PropertiesUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class ReviewsController extends SalesManagerController {

	private Logger log = Logger.getLogger(ReviewsController.class);
	private static Configuration config = PropertiesUtil.getConfiguration();


	
	
	private static int size = 5;

	static {

		size = config.getInt("catalog.reviewslist.maxsize", 5);

	}
	
	@RequestMapping(value={"/profile/reviews.html"}, method=RequestMethod.GET)
	public ModelAndView displayReviews(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView("displayReviews");
		return model;

	}
	
	@RequestMapping(value={"/profile/pageReviews.html"}, method=RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> pageReviews(int start, int limit, HttpServletRequest request) {
		
		
		Map<String,Object> modelMap = new HashMap<String,Object>();
		
		try {
			
			PageCount pageCount = new PageCount();
			pageCount.setSize(size);
			pageCount.setPageStartCount(start);
			pageCount.processStartNumber();
			pageCount.setPageElements();


			SearchReviewCriteria criteria = getCriteria(request,pageCount.getPageCriteriaIndex());
			
			Locale locale = LocaleUtil.getLocale(request);
			OrderService oservice = (OrderService) ServiceFactory
			.getService(ServiceFactory.OrderService);

			
			
			CatalogService cservice = (CatalogService) ServiceFactory
			.getService(ServiceFactory.CatalogService);

			SearchReviewResponse resp = cservice
					.searchProductReviewsByCustomer(criteria);
			if (resp != null) {
				Collection reviews = resp.getReviews();
				
				Collection rvos = new ArrayList();
				//convert to VO
				for(Object o : reviews) {
					
					Review r = (Review)o;
					ReviewVO rvo = new ReviewVO();
					rvo.setReviewId(r.getReviewId());
					rvo.setCustomerName(r.getCustomerName());
					rvo.setDateAdded(r.getDateAdded());
					rvo.setDescription(r.getDescription());
					rvo.setProductName(r.getProductName());
					rvo.setReviewRating(r.getReviewRating());
					rvos.add(rvo);
					
				}

				modelMap.put("data", rvos);
				modelMap.put("totalCount", resp.getCount());
		
				LocaleUtil.setLocaleToEntityCollection(reviews, locale);
		
				if (reviews != null && reviews.size() > 0) {
					
					pageCount.setListingCount(resp.getCount());
					pageCount.setRealCount(reviews.size());
					pageCount.setPageElements();
				}

			}
					

		} catch (Exception e) {
			log.error(e);
			modelMap.put("success", false);

		}
		
		
		return modelMap;
		
	}
	
	
	
	private SearchReviewCriteria getCriteria(HttpServletRequest request, int startIndex) {

		MerchantStore store = SessionUtil.getMerchantStore(request);

		SearchReviewCriteria criteria = new SearchReviewCriteria();

		Customer customer = SessionUtil.getCustomer(request);
		
		Locale locale = LocaleUtil.getLocale(request);
		
		

		criteria.setLanguageId(LanguageUtil.getLanguageNumberCode(locale.getLanguage()));
		criteria.setMerchantId(store.getMerchantId());
		criteria.setQuantity(size);
		criteria.setStartcount(startIndex);
		criteria.setCustomerId(customer.getCustomerId());

		return criteria;
		

	}
	
	
}
