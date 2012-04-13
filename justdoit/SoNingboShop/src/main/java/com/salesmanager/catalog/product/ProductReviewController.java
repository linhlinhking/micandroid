package com.salesmanager.catalog.product;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.catalog.common.PageCount;
import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.catalog.SearchReviewCriteria;
import com.salesmanager.core.entity.catalog.SearchReviewResponse;
import com.salesmanager.core.entity.common.Counter;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.catalog.CatalogService;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.PropertiesUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class ProductReviewController extends SalesManagerController {
	
	private static Configuration config = PropertiesUtil.getConfiguration();
	
	private static int DEFAULT_SIZE = 3;// default
	
	static {

		DEFAULT_SIZE = config.getInt("catalog.reviewslist.maxsize", 3);

	}
	
	@RequestMapping(value="/product/reviews.html",method=RequestMethod.GET)
	public ModelAndView execute (@RequestParam long productId, @RequestParam int pageStartIndex, HttpServletRequest request) throws Exception {
		

			Locale locale = LocaleUtil.getLocale(request);
			
			MerchantStore store = SessionUtil.getMerchantStore(request);
			

			ModelAndView model = new ModelAndView("productReviews");		

			PageCount pageCount = new PageCount();
			
			int size = DEFAULT_SIZE;
			
			pageCount.setSize(size);
			pageCount.setPageStartIndex(pageStartIndex);

			SearchReviewCriteria criteria = new SearchReviewCriteria();
			criteria.setProductId(productId);
			criteria.setLanguageId(LanguageUtil.getLanguageNumberCode(locale
					.getLanguage()));
			criteria.setQuantity(size);
			criteria.setStartindex(pageStartIndex);

			CatalogService cservice = (CatalogService) ServiceFactory
					.getService(ServiceFactory.CatalogService);
			
			
			com.salesmanager.core.entity.catalog.Product product = cservice.getProduct(productId);
			
			model.addObject("product", product);
			
			if(store.getMerchantId()!=product.getMerchantId()) {
				return model;//will see a button to create a review
			}
			
			model.addObject("size",size);
			model.addObject("pageCount", pageCount);

			SearchReviewResponse response = cservice
					.searchProductReviewsByProduct(criteria);
			Collection reviews = response.getReviews();
			
			model.addObject("reviews", reviews);
			
			
			pageCount.setRealCount(reviews.size());
			pageCount.setListingCount(response.getCount());
			pageCount.processStartNumber();
			pageCount.setPageElements();
			

			LocaleUtil.setLocaleToEntityCollection(reviews, locale);

			// calculate average
			Counter counter = cservice.countAverageRatingPerProduct(productId);


			model.addObject("counter", counter);
			
			return model;
		
		
	}

}
