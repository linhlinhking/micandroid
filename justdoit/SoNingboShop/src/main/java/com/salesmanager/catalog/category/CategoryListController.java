package com.salesmanager.catalog.category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.catalog.HomeController;
import com.salesmanager.catalog.common.PageCount;
import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.constants.CatalogConstants;
import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.entity.catalog.Category;
import com.salesmanager.core.entity.catalog.CategoryDescription;
import com.salesmanager.core.entity.catalog.SearchProductCriteria;
import com.salesmanager.core.entity.catalog.SearchProductResponse;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.module.model.application.CacheModule;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.catalog.CatalogService;
import com.salesmanager.core.util.CategoryUtil;
import com.salesmanager.core.util.LanguageUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.PropertiesUtil;
import com.salesmanager.core.util.SpringUtil;
import com.salesmanager.core.util.www.SessionUtil;

@Controller
public class CategoryListController extends SalesManagerController {
	
	private static Logger log = Logger.getLogger(CategoryListController.class);
	private static Configuration config = PropertiesUtil.getConfiguration();
	
	private static int DEFAULT_SIZE = 20;// default
	
	static {

		DEFAULT_SIZE = config.getInt("catalog.categorylist.maxsize", 10);

	}
	
	
	@RequestMapping(value="/category/{categoryName}.html",method=RequestMethod.GET)
	public ModelAndView execute(@PathVariable("categoryName") String categoryName, HttpServletRequest request) throws Exception {
		
		
		

		ModelAndView model = new ModelAndView("category");		
		
		String url = categoryName;
		
		PageCount pageCount = new PageCount();
		
		int size = getProductCount(DEFAULT_SIZE,request);
		
		pageCount.setSize(size);
		
		model.addObject("size",size);
		
		pageCount.processStartNumber();
		
		//super.setSize(getProductCount());// defined in configuration
		// according to template
		//super.setPageStartNumber();

		// 1) Get Category

		//String url = super.getRequestedEntityId();
		model.addObject("currentEntity", url);
		//this.setCurrentEntity(url);
		
		MerchantStore store = SessionUtil.getMerchantStore(request);
		CatalogService cservice = (CatalogService) ServiceFactory
				.getService(ServiceFactory.CatalogService);
		
		Locale locale = LocaleUtil.getLocale(request);

		// make a query to retrieve a category by id or seurl
		Category c = cservice.getCategoryByMerchantIdAndSeoURLAndByLang(
				store.getMerchantId(), url, locale.getLanguage());
		
		
		if(c==null) {
			
			//get category by url only
			Category categ = cservice.getCategoryByMerchantIdAndSeoURL(store.getMerchantId(),url);
			if(categ!=null) {
				c = cservice.getCategoryByLang(categ.getCategoryId(), locale.getLanguage());
			}
			
		}

		if (c != null) {//if null try to get without the language (@todo)

			CategoryDescription description = c.getCategoryDescription();

			model.addObject("category", c);
			//this.setCategory(c);

			//this.setMetaDescription(description.getMetatagDescription());
			//this.setMetaKeywords(description.getMetatagKeywords());
			
			
			model.addObject("metaDescription", description.getMetatagDescription());
			model.addObject("metaKeywords", description.getMetatagKeywords());
			
			if(!StringUtils.isBlank(description.getCategoryTitle())) {
				//this.setPageTitle(description.getCategoryTitle());
				model.addObject("pageTitle", description.getCategoryTitle());
			} else {
				model.addObject("pageTitle", description.getCategoryName());
				//this.setPageTitle(description.getCategoryName());
			}
			model.addObject("pageText", description.getCategoryDescription());
			//this.setPageText(description.getCategoryDescription());

			// SET CURRENT MAIN CATEGORY AND SUB CATEGORY IN HTTP SESSION
			if (c.getParentId() == 0) {
				// will be used for top category display
				request.getSession().setAttribute(
						"mainUrl", c);
			} else {
				// will be used for side bar navigation categories
				request.getSession().setAttribute(
						"subCategory", c);
			}

			request.getSession().setAttribute(
					"currentCategory", c);

		}
		
		if(c==null) {
			throw new Exception("Category is null !");
		}


		model.addObject("pageCount", pageCount);
		this.setCategories(c, pageCount, model, request);

		
		return model;
	}
	
	
	private void setCategories(Category c, PageCount pageCount, ModelAndView model, HttpServletRequest request) throws Exception {

		MerchantStore store = SessionUtil.getMerchantStore(request);
		CacheModule cache = (CacheModule) SpringUtil.getBean("cache");
		CatalogService cservice = (CatalogService) ServiceFactory
				.getService(ServiceFactory.CatalogService);

		// get store template maximum item quantity per page
		List idList = new ArrayList();
		List categories = new ArrayList();

		// Get category list for left menu
		String lineageQuery = new StringBuffer().append(c.getLineage()).append(
				c.getCategoryId()).append(CatalogConstants.LINEAGE_DELIMITER)
				.toString();
		
		
		model.addObject("categoryLineage",lineageQuery);
		
		//this.setCategoryLineage(lineageQuery);

		// Category List from cache
		CategoryList categoryList = null;
		try {

			categoryList = (CategoryList) cache.getFromCache(
					Constants.CACHE_CATEGORIES + lineageQuery + "_"
							+ LocaleUtil.getLocale(request).getLanguage(), store);
		} catch (Exception ignore) {

		}

		if (categoryList == null) {

			// get from missed
			boolean missed = false;
			try {
				missed = (Boolean) cache.getFromCache(
						Constants.CACHE_CATEGORIES + lineageQuery + "_MISSED_"
								+ LocaleUtil.getLocale(request), store);
			} catch (Exception ignore) {

			}

			if (!missed) {

				Collection subcategs = cservice
						.findCategoriesByMerchantIdAndLineageAndLanguageId(c
								.getMerchantId(), lineageQuery, LocaleUtil.getLocale(request).getLanguage());

				Collection ids = new ArrayList();
				if (subcategs != null && subcategs.size() > 0) {
					categoryList = new CategoryList();
					Iterator cIterator = subcategs.iterator();
					while (cIterator.hasNext()) {
						Category sc = (Category) cIterator.next();
						categories.add(sc);
						idList.add(sc.getCategoryId());
					}

					Collection categs = new ArrayList();
					categs.addAll(categories);
					categoryList.setCategories(categs);

				}

				// add master category
				idList.add(c.getCategoryId());

				if (subcategs != null && subcategs.size() > 0) {
					ids.addAll(idList);
					categoryList.setCategoryIds(ids);
				}

				if (categoryList != null) {

					try {
						cache
								.putInCache(Constants.CACHE_CATEGORIES
										+ lineageQuery + "_"
										+ LocaleUtil.getLocale(request).getLanguage(),
										categoryList,
										Constants.CACHE_CATEGORIES, store);
					} catch (Exception e) {
						log.error(e);
					}

				} else {

					try {
						cache
								.putInCache(Constants.CACHE_CATEGORIES
										+ lineageQuery + "_MISSED_"
										+ LocaleUtil.getLocale(request).getLanguage(),
										categoryList,
										Constants.CACHE_CATEGORIES, store);
					} catch (Exception e) {
						log.error(e);
					}

				}

			}

		} else {
			
			
			idList.add(c.getCategoryId());
			idList.addAll(categoryList.getCategoryIds());
			
		}
		
		model.addObject("categories", categories);

		int productCount = getProductCount(pageCount.getSize(), request);

		// get product list
		SearchProductCriteria criteria = new SearchProductCriteria();
		criteria.setMerchantId(store.getMerchantId());
		criteria.setCategoryList(idList);
		criteria.setLanguageId(LanguageUtil.getLanguageNumberCode(LocaleUtil.getLocale(request).getLanguage()));
		criteria.setQuantity(productCount);// qty based on template config
		criteria.setStartindex(pageCount.getPageStartIndex());

		SearchProductResponse response = cservice
				.findProductsByCategoryList(criteria);

		pageCount.setListingCount(response.getCount());
		//this.setListingCount(response.getCount());

		
		
		Collection prds = response.getProducts();

		LocaleUtil.setLocaleToEntityCollection(prds, LocaleUtil.getLocale(request), store
				.getCurrency());

		// get category path
		Collection categoryPath = null;
		try {
			categoryPath = (Collection) cache.getFromCache(
					Constants.CACHE_CATEGORIES_PATH + "_" + c.getCategoryId()
							+ "_" + LocaleUtil.getLocale(request), store);
		} catch (Exception ignore) {

		}

		if (categoryPath == null || categoryPath.size() == 0) {

			// get from missed
			boolean missed = false;
			try {
				missed = (Boolean) cache.getFromCache(
						Constants.CACHE_CATEGORIES_PATH + "_MISSED_"
								+ c.getCategoryId() + "_" + LocaleUtil.getLocale(request),
						store);
			} catch (Exception ignore) {

			}

			if (!missed) {

				categoryPath = CategoryUtil.getCategoryPath(LocaleUtil.getLocale(request)
						.getLanguage(), store.getMerchantId(), c
						.getCategoryId());

				if (categoryPath != null && categoryPath.size() > 0) {

					try {
						cache
								.putInCache(Constants.CACHE_CATEGORIES_PATH
										+ "_" + c.getCategoryId() + "_"
										+LocaleUtil.getLocale(request), categoryPath,
										Constants.CACHE_CATEGORIES, store);
					} catch (Exception e) {
						log.error(e);
					}

				} else {

					try {
						cache.putInCache(Constants.CACHE_CATEGORIES_PATH
								+ "_MISSED_" + c.getCategoryId() + "_"
								+ LocaleUtil.getLocale(request), true,
								Constants.CACHE_CATEGORIES, store);
					} catch (Exception e) {
						log.error(e);
					}
				}

			}

		}

		categoryPath = CategoryUtil.getCategoryPath(LocaleUtil.getLocale(request)
				.getLanguage(), store.getMerchantId(), c.getCategoryId());
		
		model.addObject("categoryPath",categoryPath);
		model.addObject("products", prds);

		//products = prds;
		
		pageCount.setListingCount(response.getCount());

		//super.setListingCount(response.getCount());
		
		pageCount.setRealCount(prds.size());
		
		//super.setRealCount(products.size());
		
		pageCount.setPageElements();
		//super.setPageElements();

		/*
		 * if(products==null || products.size()==0) { this.setFirstItem(0);
		 * this.setLastItem(response.getCount()); } else {
		 * 
		 * this.setFirstItem(startIndex+1); if(productCount<response.getCount())
		 * { this.setLastItem(startIndex + products.size()); } else {
		 * this.setLastItem(response.getCount()); } }
		 */

	}

	
	
	
	private int getProductCount(int defaultSize, HttpServletRequest request) {

		int maxQuantity = defaultSize;
		MerchantStore store = (MerchantStore) request
				.getSession().getAttribute("STORE");
		Map storeConfiguration = (Map)request.getSession()
				.getAttribute("STORECONFIGURATION");
		if (storeConfiguration != null) {
			String sMaxQuantity = null;
			try {
				sMaxQuantity = (String) storeConfiguration
						.get("listingitemsquantity");
				if (sMaxQuantity != null) {
					maxQuantity = Integer.parseInt(sMaxQuantity);

				}
			} catch (Exception e) {
				log
						.warn("Invalid value for listing quantity (table module_configuration.configurationKey listingitemsquantity has value "
								+ sMaxQuantity
								+ " for module_configuration.configuration_module "
								+ store.getTemplateModule());
			}
		}

		return maxQuantity;

	}
	

}
