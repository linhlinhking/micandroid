package com.salesmanager.catalog.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.catalog.category.CategoryListController;
import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.constants.CatalogConstants;
import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.entity.catalog.Product;
import com.salesmanager.core.entity.catalog.ProductOption;
import com.salesmanager.core.entity.catalog.ProductOptionDescriptor;
import com.salesmanager.core.entity.catalog.ProductOptionValue;
import com.salesmanager.core.entity.common.I18NEntity;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.orders.ShoppingCart;
import com.salesmanager.core.module.model.application.CacheModule;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.catalog.CatalogService;
import com.salesmanager.core.util.CategoryUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.ProductUtil;
import com.salesmanager.core.util.SpringUtil;

@Controller
public class ProductDetailsController extends SalesManagerController {
	
	
	private static Logger log = Logger.getLogger(ProductDetailsController.class);
	
	
	@RequestMapping(value="/product/{productName}.html",method=RequestMethod.GET)
	public ModelAndView execute(@PathVariable("productName") String productName, HttpServletRequest request) throws Exception {
		
		
		ModelAndView model = new ModelAndView("product");
		

		CacheModule cache = (CacheModule) SpringUtil.getBean("cache");

		String url = productName;
		CatalogService cservice = (CatalogService) ServiceFactory
				.getService(ServiceFactory.CatalogService);
		MerchantStore store = (MerchantStore) request
				.getSession().getAttribute("STORE");
		Locale locale = LocaleUtil.getLocale(request);
		
		Product product = cservice.getProductByMerchantIdAndSeoURLAndByLang(store
				.getMerchantId(), url, locale.getLanguage());

		if (product == null) {
			Product p = cservice.getProductByMerchantIdAndSeoURL(store.getMerchantId(), url);
			if(p!=null) {
				product = cservice.getProductByLanguage(p.getProductId(), locale.getLanguage());
			}
			
			if (product == null) {
				log.warn("Product having seUrl " + url
						+ " does not exist");
				throw new Exception("Product " + productName + " does not exist");
			}
		}

		
		model.addObject("product", product);
		
		if(!StringUtils.isBlank(product.getProductDescription().getProductTitle())) {
			//this.setPageTitle(product.getProductDescription().getProductTitle());
			model.addObject("pageTitle",product.getProductDescription().getProductTitle());
		} else {
			//this.setPageTitle(product.getName());
			model.addObject("pageTitle",product.getName());
		}

		model.addObject("metaDescription",product.getDescription());
		//this.setMetaDescription(product.getDescription());

		((I18NEntity) product).setLocale(LocaleUtil.getLocale(request), store
				.getCurrency());

		Set prices = product.getPrices();

		LocaleUtil.setLocaleToEntityCollection(prices, locale, store
				.getCurrency());

		// for category trail
		Collection categoryPath = CategoryUtil.getCategoryPath(LocaleUtil.getLocale(request)
				.getLanguage(), store.getMerchantId(), product
				.getMasterCategoryId());
		
		model.addObject("categoryPath", categoryPath);

		// options - attributes
		Collection<com.salesmanager.core.entity.catalog.ProductAttribute> attributes = cservice.getProductAttributes(product
				.getProductId(), locale.getLanguage());

		Collection defaultOptions = new ArrayList();
		
		Collection<ProductOptionDescriptor> specifications = new ArrayList();
		Collection<ProductOptionDescriptor> options = new ArrayList();

		if (attributes != null && attributes.size() > 0) {

			// extract read only
			Iterator i = attributes.iterator();

			long lastOptionId = -1;
			long lastSpecificationOptionId = -1;
			ProductOptionDescriptor pod = null;

			while (i.hasNext()) {

				com.salesmanager.core.entity.catalog.ProductAttribute pa = (com.salesmanager.core.entity.catalog.ProductAttribute) i.next();

				ProductOption po = pa.getProductOption();
				//setValues ???
				ProductOptionValue pov = pa.getProductOptionValue();
				if (po != null) {

					if (pa.isAttributeDisplayOnly()) {

						if (lastSpecificationOptionId == -1) {
							lastSpecificationOptionId = po
									.getProductOptionId();
							pod = new ProductOptionDescriptor();
							pod.setOptionType(po.getProductOptionType());
							pod.setName(po.getName());
							specifications.add(pod);
						} else {
							if (pa.getOptionId() != lastOptionId) {
								lastSpecificationOptionId = po
										.getProductOptionId();
								pod = new ProductOptionDescriptor();
								pod
										.setOptionType(po
												.getProductOptionType());
								pod.setName(po.getName());
								specifications.add(pod);
							}
						}

					} else {// option

						if (lastOptionId == -1) {
							lastOptionId = po.getProductOptionId();
							pod = new ProductOptionDescriptor();
							pod.setOptionType(po.getProductOptionType());
							pod.setName(po.getName());
							options.add(pod);
							if (pa.isAttributeDefault()) {
								defaultOptions.add(pa);
							}

						} else {
							if (pa.getOptionId() != lastOptionId) {
								lastOptionId = po.getProductOptionId();
								pod = new ProductOptionDescriptor();
								pod
										.setOptionType(po
												.getProductOptionType());
								pod.setName(po.getName());
								options.add(pod);
								if (pa.isAttributeDefault()) {
									defaultOptions.add(pa);
								}
							}
						}

					}

					pod.addValue(pa);
					pod.setOptionId(pa.getOptionId());
					if (pa.isAttributeDefault()) {
						pod.setDefaultOption(pa.getProductAttributeId());
					}
				}
			}

		}

		
		model.addObject("specifications", specifications);
		model.addObject("options", options);
		
		if (defaultOptions != null && defaultOptions.size() > 0) {
			model.addObject("productPrice", ProductUtil
					.formatHTMLProductPriceWithAttributes(
							LocaleUtil.getLocale(request), store.getCurrency(), product, defaultOptions, true));
		} else {
			model.addObject("productPrice", ProductUtil.formatHTMLProductPrice(LocaleUtil.getLocale(request), store.getCurrency(), product,
					true, false));
		}

		// related items
		Collection<Product> relatedItems = null;
		try {
			relatedItems = (Collection) cache.getFromCache(
					Constants.CACHE_RELATED_ITEMS + product.getProductId()
							+ "_" + locale.getLanguage(), store);
		} catch (Exception ignore) {

		}

		if (relatedItems == null) {

			// get it from missed cache
			boolean missed = false;
			try {
				missed = (Boolean) cache.getFromCache(
						Constants.CACHE_RELATED_ITEMS
								+ product.getProductId() + "_MISSED_"
								+ locale.getLanguage(), store);
			} catch (Exception ignore) {

			}

			if (!missed) {

				Collection r = cservice
						.getProductRelationShip(
								product.getProductId(),
								store.getMerchantId(),
								CatalogConstants.PRODUCT_RELATIONSHIP_RELATED_ITEMS,
								LocaleUtil.getLocale(request).getLanguage(), true);

				if (r != null && r.size() > 0) {

					LocaleUtil.setLocaleToEntityCollection(r, LocaleUtil.getLocale(request), store.getCurrency());

					relatedItems = r;

					try {
						cache.putInCache(Constants.CACHE_RELATED_ITEMS
								+ product.getProductId() + "_"
								+ locale.getLanguage(), relatedItems,
								Constants.CACHE_PRODUCTS, store);
					} catch (Exception ignore) {

					}

				} else {

					try {
						cache.putInCache(Constants.CACHE_RELATED_ITEMS
								+ product.getProductId() + "_MISSED_"
								+ locale.getLanguage(), true,
								Constants.CACHE_PRODUCTS, store);
					} catch (Exception ignore) {

					}
				}
			}
		}

		model.addObject("relatedItems", relatedItems);
		return model;
		
	}
	
	@RequestMapping(value={"/product/setProductPrice.html"}, method=RequestMethod.POST)
	public @ResponseBody com.salesmanager.catalog.product.Product setProductPrice(@RequestBody String prod, HttpServletRequest request) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		com.salesmanager.catalog.product.Product product = mapper.readValue(prod, com.salesmanager.catalog.product.Product.class); // 'src' can be File, InputStream, Reader, String
		
		if(product==null) {
			return null;
		}
		
		if (product.getProductId()==0) {
			return null;
		}
		
		long productId = product.getProductId();
		
		com.salesmanager.catalog.product.ProductAttribute[] attributes = product.getAttributes();

		HttpSession session = request.getSession();

		MerchantStore store = (MerchantStore) session.getAttribute("STORE");

		Locale locale = LocaleUtil.getLocale(request);

		String price = "";

		// get original product price
		try {

			CatalogService cservice = (CatalogService) ServiceFactory
					.getService(ServiceFactory.CatalogService);
			Product p = cservice.getProduct(Long.valueOf(productId));
			if (p == null) {
				return null;
			}

			if (attributes != null && attributes.length > 0) {
				List ids = new ArrayList();
				for (int i = 0; i < attributes.length; i++) {
					if (!attributes[i].isStringValue()) {
						ids.add(new Long(attributes[i].getValue()));
					}
				}
				Collection attrs = cservice.getProductAttributes(ids, locale
						.getLanguage());

				if (attrs != null && attrs.size() > 0) {
					price = ProductUtil.formatHTMLProductPriceWithAttributes(
							locale, store.getCurrency(), p, attrs, false);
				}
			}

		} catch (Exception e) {
			log.error(e);
		}

		com.salesmanager.catalog.product.Product returnProduct = new com.salesmanager.catalog.product.Product();
		returnProduct.setPrice(price);
		
		return returnProduct;

	}

}
