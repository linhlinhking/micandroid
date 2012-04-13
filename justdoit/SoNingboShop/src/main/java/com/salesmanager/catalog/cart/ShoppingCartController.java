package com.salesmanager.catalog.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.catalog.product.ProductAttribute;
import com.salesmanager.checkout.util.MiniShoppingCartUtil;
import com.salesmanager.common.SalesManagerController;
import com.salesmanager.core.entity.catalog.Product;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.entity.orders.ShoppingCart;
import com.salesmanager.core.entity.orders.ShoppingCartProduct;
import com.salesmanager.core.entity.orders.ShoppingCartProductAttribute;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.catalog.CatalogService;
import com.salesmanager.core.util.CurrencyUtil;
import com.salesmanager.core.util.LabelUtil;
import com.salesmanager.core.util.LocaleUtil;
import com.salesmanager.core.util.MiniShoppingCartSerializationUtil;
import com.salesmanager.core.util.ProductUtil;
import com.salesmanager.core.util.www.SessionUtil;
import com.salesmanager.core.entity.common.I18NEntity;

@Controller
public class ShoppingCartController extends SalesManagerController {

	private static Logger logger = Logger
			.getLogger(ShoppingCartController.class);

	@RequestMapping(value = { "/addToCart.html" }, method = RequestMethod.POST)
	public @ResponseBody
	ShoppingCart addProductToCart(
			@RequestBody com.salesmanager.catalog.product.Product product,
			HttpServletRequest req) throws Exception {

		HttpSession session = req.getSession();

		if (product == null) {
			return null;
		}

		ProductAttribute[] attributes = product.getAttributes();

		ShoppingCart cart = SessionUtil.getMiniShoppingCart(req);

		MerchantStore store = (MerchantStore) session.getAttribute("STORE");

		Locale locale = LocaleUtil.getLocale(req);

		if (store == null) {
			cart = new ShoppingCart();
			LabelUtil label = LabelUtil.getInstance();
			label.setLocale(locale);
			String msg = label.getText("error.sessionexpired");
			cart.setErrorMessage(msg);
			return cart;
		}

		if (cart == null) {
			cart = new ShoppingCart();
		}
		cart.setErrorMessage(null);

		try {

			// get products
			Collection productsCollection = cart.getProducts();

			CatalogService cservice = (CatalogService) ServiceFactory
					.getService(ServiceFactory.CatalogService);
			Product p = cservice.getProduct(product.getProductId());

			if (p == null || store == null) {
				String message = LabelUtil.getInstance().getText(locale,
						"errors.addtocart");
				cart.setErrorMessage(message);
				return cart;
			}

			if (product.getQuantity() > p.getProductQuantityOrderMax()) {
				String message = LabelUtil.getInstance().getText(locale,
						"messages.invalid.quantity");
				cart.setErrorMessage(message);
				return cart;
			}

			((I18NEntity) p).setLocale(locale, store.getCurrency());
			
			String attributeLine = null;

			if (p.getMerchantId() != store.getMerchantId())
				return cart;

			boolean productFound = false;
			if (productsCollection != null
					&& (attributes == null || attributes.length == 0)) {
				Iterator i = productsCollection.iterator();
				while (i.hasNext()) {
					ShoppingCartProduct scp = (ShoppingCartProduct) i.next();
					if (scp.getAttributes() != null
							&& scp.getAttributes().size() > 0) {
						continue;
					}
					if (scp.getProductId() == product.getProductId()) {
						int qty = scp.getQuantity();

						if (qty + product.getQuantity() > p
								.getProductQuantityOrderMax()) {
							String message = LabelUtil.getInstance().getText(
									locale, "messages.invalid.quantity");
							cart.setErrorMessage(message);
							return cart;
						}

						scp.setQuantity(qty + product.getQuantity());
						productFound = true;
						break;
					}
				}
			}

			// check if product with similar attributes exist
			if (productsCollection != null
					&& (attributes != null && attributes.length > 0)) {
				
				
				//flat current product attributes lines
				List idsvalues = new ArrayList();
				
				for(int i = 0; i<attributes.length; i++) {
					
					
					ProductAttribute productAttribute = attributes[i];
					idsvalues.add(productAttribute.getName());
					idsvalues.add(productAttribute.getValue());
				}
				
				Collections.sort(idsvalues);

				StringBuilder flatAttributes = new StringBuilder(); 
				for(Object oo : idsvalues) {
				
					flatAttributes.append(oo);
				
				}
				attributeLine = flatAttributes.toString();

				Iterator i = productsCollection.iterator();
				while (i.hasNext()) {
					ShoppingCartProduct scp = (ShoppingCartProduct) i.next();
					if (scp.getAttributes() == null) {
						continue;
					}
					if (scp.getProductId() == product.getProductId()) {
						int qty = scp.getQuantity();

						// check if they have the same ids
/*						List currentAttributes = new ArrayList();
						currentAttributes.addAll(scp.getAttributes());
						List newAttributes = new ArrayList();
						newAttributes.addAll(Arrays.asList(attributes));

						currentAttributes.removeAll(newAttributes);*/
						
						if(!StringUtils.isBlank(scp.getAttributeLine()) && !StringUtils.isBlank(attributeLine)) {
							
							if(scp.getAttributeLine().equals(attributeLine)) {
								
								if (qty + product.getQuantity() > p
										.getProductQuantityOrderMax()) {
									String message = LabelUtil.getInstance()
											.getText(locale,
													"messages.invalid.quantity");
									cart.setErrorMessage(message);
									return cart;
								}

								scp.setQuantity(qty + product.getQuantity());
								productFound = true;
								break;
								
							}
						}
					}
				}
			}

			if (!productFound) {

				ShoppingCartProduct scp = new ShoppingCartProduct();
				scp.setProductId(p.getProductId());
				scp.setQuantity(product.getQuantity());
				
				if(attributeLine!=null) {
					scp.setAttributeLine(attributeLine);
				}
				
				if (!StringUtils.isBlank(p.getSmallImagePath())) {
					scp.setImage(p.getSmallImagePath());
				} else if (!StringUtils.isBlank(p.getLargeImagePath())) {
					scp.setImage(p.getLargeImagePath());
				} else {
					// nothing for now
				}

				if (attributes != null && attributes.length > 0) {
					Map ids = new HashMap();
					for (int i = 0; i < attributes.length; i++) {
						ids.put(new Long(attributes[i].getName()),
								attributes[i]);
					}
					Collection attrs = cservice.getProductAttributes(
							new ArrayList(ids.keySet()), locale.getLanguage());

					if (attrs != null && attrs.size() > 0) {
						BigDecimal priceWithAttributes = ProductUtil
								.determinePriceWithAttributes(p, attrs, locale,
										store.getCurrency());
						scp.setPrice(priceWithAttributes);
						scp.setPriceText(CurrencyUtil
								.displayFormatedAmountWithCurrency(
										priceWithAttributes,
										store.getCurrency()));

						Iterator attrIt = attrs.iterator();
						List attrList = new ArrayList();
						//List idsvalues = null;
						while (attrIt.hasNext()) {

							//attributeLine (flat)
							com.salesmanager.core.entity.catalog.ProductAttribute productAttribute = (com.salesmanager.core.entity.catalog.ProductAttribute) attrIt
									.next();
							ShoppingCartProductAttribute scpa = new ShoppingCartProductAttribute();
							scpa.setAttributeId(productAttribute
									.getProductAttributeId());
							ProductAttribute pa = (ProductAttribute) ids
									.get(new Long(productAttribute
											.getProductAttributeId()));
							if (pa != null) {
								
								
								scpa.setAttributeValue(pa.getValue());
								if (pa.isStringValue()) {
									scpa.setTextValue(pa.getTextValue());
								}
								attrList.add(scpa);
								
								//if(idsvalues==null) {
								//	idsvalues = new ArrayList();
								//}
								
								//idsvalues.add(productAttribute.getOptionId());
								//idsvalues.add(productAttribute.getOptionValueId());
								
							}
						}
						scp.setAttributes(attrList);
						
/*						if(idsvalues!=null) {
							Collections.sort(idsvalues);
							StringBuilder flatAttributes = new StringBuilder(); 
							for(Object oo : idsvalues) {
							
								flatAttributes.append(oo);
							
							}
							scp.setAttributeLine(flatAttributes.toString());
						}*/

					} else {
						scp.setPrice(ProductUtil.determinePrice(p, locale,
								store.getCurrency()));
						BigDecimal price = ProductUtil.determinePrice(p,
								locale, store.getCurrency());
						scp.setPriceText(CurrencyUtil
								.displayFormatedAmountWithCurrency(price,
										store.getCurrency()));
					}
				} else {
					scp.setPrice(ProductUtil.determinePrice(p, locale,
							store.getCurrency()));
					BigDecimal price = ProductUtil.determinePrice(p, locale,
							store.getCurrency());
					scp.setPriceText(CurrencyUtil
							.displayFormatedAmountWithCurrency(price,
									store.getCurrency()));
				}
				
				

				
				scp.setProductName(p.getName());

				Collection products = cart.getProducts();
				if (products == null) {
					products = new ArrayList();
					cart.setProducts(products);
				}
				products.add(scp);
			}
			
			
			//get products, flat attributes

			MiniShoppingCartUtil.calculateTotal(cart, store);

			SessionUtil.setMiniShoppingCart(cart, req);

			// save the cart in the cookie
			setMiniCartCookie(req, cart);

			return cart;

		} catch (Exception e) {
			logger.error(e);
			cart.setErrorMessage(LabelUtil.getInstance().getText(locale,
					"errors.technical"));
			return cart;
		}

	}

	private void setMiniCartCookie(HttpServletRequest req, ShoppingCart cart) {

		try {

			String serializedCart = MiniShoppingCartSerializationUtil
					.serializeToJSON(cart);
			if (cart != null) {
				cart.setJsonShoppingCart(serializedCart);
			}
			/*
			 * String cookieValue = serializedCart; int maxAge =
			 * SECONDS_PER_YEAR; String cookieName =
			 * CatalogConstants.CART_COOKIE_NAME; if(cookieValue==null) {
			 * cookieValue = ""; maxAge = 0; }
			 * 
			 * Cookie c = null; Cookie[] cookies = req.getCookies(); if (cookies
			 * != null) { for (int i = 0; i < cookies.length; i++) { Cookie
			 * cookie = cookies[i];
			 * if(cookie.getName().equals(CatalogConstants.SKU_COOKIE)) { c =
			 * cookie; c.setValue(cookieValue); } } }
			 * 
			 * if(c==null) { c = new Cookie(cookieName,cookieValue); }
			 * c.setMaxAge(maxAge); resp.addCookie(c);
			 */

		} catch (Exception e) {
			logger.error("Cannot serialize cart in the cookie", e);
		}

	}

	/**
	 * Removes an item from the shopping cart
	 * 
	 * @param productId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/removeFromCart.html" }, method = RequestMethod.POST)
	public @ResponseBody
	ShoppingCart removeProductFromCart(
			@RequestBody ShoppingCartProduct product,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		ShoppingCart cart = SessionUtil.getMiniShoppingCart(request);

		MerchantStore store = (MerchantStore) session.getAttribute("STORE");

		Locale locale = LocaleUtil.getLocale(request);
		
		String attributeLine = product.getAttributeLine();

		if (cart != null) {

			cart.setErrorMessage(null);

			Collection productsCollection = cart.getProducts();

			Collection newProductsCollection = new ArrayList();

			if (productsCollection != null) {
				Iterator i = productsCollection.iterator();
				while (i.hasNext()) {
					ShoppingCartProduct scp = (ShoppingCartProduct) i.next();
					if (scp.getProductId() == product.getProductId()) {
						
						if(!StringUtils.isBlank(attributeLine) && !StringUtils.isBlank(scp.getAttributeLine())) {
							if(attributeLine.equals(scp.getAttributeLine())) {
								continue;
							}
						} else if(StringUtils.isBlank(attributeLine) && !StringUtils.isBlank(scp.getAttributeLine())){
							
							
						} else {
							continue;
						}

					}
					newProductsCollection.add(scp);
				}
			}
			cart.setProducts(newProductsCollection);
		}

		MiniShoppingCartUtil.calculateTotal(cart, store);

		// save the cart in the cookie
		setMiniCartCookie(request, cart);

		return cart;

	}

}
