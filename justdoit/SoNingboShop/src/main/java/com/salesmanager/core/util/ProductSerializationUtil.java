package com.salesmanager.core.util;




import org.apache.commons.lang.StringUtils;

import com.salesmanager.core.entity.catalog.Product;
import com.salesmanager.core.entity.catalog.ProductDescription;
import com.salesmanager.core.entity.catalog.SearchProductCriteria;

public class ProductSerializationUtil {
	
	/**
	 * This will serialize a product to json only for search items to be kept
	 * This method will be used only for indexing
	 * @param product
	 * @param langId
	 * @return
	 * @throws Exception
	 */
	public static String serializeToJson(Product product, int langId) throws Exception {
		
		
		//product id
		//product name
		//product description
		
		/**
		 * ->category
		 * ->manufacturer
		 * ->review
		 * ->price range
		 * ->merchantid
		 */
		
		String lang = LanguageUtil.getLanguageStringCode(langId);
		
		ProductDescription description = product.getProductDescription();
		
		if(description==null) {
			throw new Exception("Product description cannot be null");
		}
		
		StringBuilder json = new StringBuilder();
		json.append("{");
		
		json.append("\"id\":\"").append(product.getProductId()).append("\",");
		json.append("\"merchantid\":\"").append(product.getMerchantId()).append("\",");
		json.append("\"name\":\"").append(description.getProductName()).append("\",");
		json.append("\"description\":\"").append(description.getProductDescription()).append("\",");
		json.append("\"lang\":\"").append(lang).append("\",");
		json.append("\"review\":\"").append(product.getProductReview()).append("\",");
		json.append("\"category\":\"").append(product.getMasterCategoryId()).append("\",");
		json.append("\"manufacturer\":\"").append(product.getProductManufacturersId()).append("\",");

		//tags
		if(!StringUtils.isBlank(description.getMetatagKeywords())) {
			String[] tags = description.getMetatagKeywords().split(",");
			
			if(tags!=null && tags.length>0) {
				json.append("\"tags\": [");
				for(int i=0;i<tags.length;i++) {
					if(!StringUtils.isBlank(tags[i])) {
						json.append("\"").append(tags[i]).append("\"");
						if(i<tags.length-1) {
							json.append(",");
						}
					}
				}
				json.append("]");
			}
		} else {
			json.append("{\"tags\": []");
		}
		json.append("}");
		return json.toString();	
	}
	
	public static String getSearchProductQuery(SearchProductCriteria criteria, String collection) throws Exception {
		
		/**
		 * 			'\"highlight\":{\"fields\":{\"description\":{\"pre_tags\" : [\"<strong>\"], \"post_tags\" : [\"</strong>\"]},\"name\":{\"pre_tags\" : [\"<strong>\"], \"post_tags\" : [\"</strong>\"]}}}',

					'\"facets\" : { \"category\" : { \"terms\" : {\"field\" : \"category\"}}}';
					
								var queryStart = '{';
		
			//var query = '{\"text\" : {\"_all\" : \"' + search + '\" }}';
			var query = '\"query\":{\"text\" : {\"_all\" : \"' + search + '\" }}';
			if(filter!=null && filter!='') {
				query = '\"query\":{\"filtered\":{\"query\":{\"text\":{\"_all\":\"' + search + '\"}},' + filter + '}}';
			}
			if(highlights!=null && highlights!='') {
				query = query + ',' + highlights;
			}
			if(facets!=null && facets!='') {
				query = query + ',' + facets;
			}

			//query = query + ',' + '\"facets\" : { \"tags\" : { \"terms\" : {\"field\" : \"tags\"}}}'; 
			
			var queryEnd = '}';
			
			query = queryStart + query + queryEnd;

		 */
		
		
		return null;
		
	}

}
