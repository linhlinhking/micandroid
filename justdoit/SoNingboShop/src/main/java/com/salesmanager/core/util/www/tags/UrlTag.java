/*
 * Licensed to csti consulting 
 * You may obtain a copy of the License at
 *
 * http://www.csticonsulting.com
 * Copyright (c) 2006-Aug 24, 2010 Consultation CS-TI inc. 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.salesmanager.core.util.www.tags;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
/*import org.apache.struts2.components.Component;
import org.apache.struts2.components.URL;
import org.apache.struts2.views.jsp.URLTag;

import com.opensymphony.xwork2.util.ValueStack;*/
import com.salesmanager.core.constants.CatalogConstants;
import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.entity.catalog.Category;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.module.model.application.CacheModule;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.catalog.CatalogService;
import com.salesmanager.core.util.CurrencyUtil;
import com.salesmanager.core.util.ProductUtil;
import com.salesmanager.core.util.PropertiesUtil;
import com.salesmanager.core.util.ReferenceUtil;
import com.salesmanager.core.util.SpringUtil;
import com.salesmanager.core.util.www.SessionUtil;

//@todo re-implement
public class UrlTag extends TagSupport {

	private static Logger log = Logger.getLogger(UrlTag.class);

	private static org.apache.commons.configuration.Configuration config = PropertiesUtil
			.getConfiguration();


	private static boolean multipleMerchants = true;

	static {

		try {
			multipleMerchants = PropertiesUtil.getConfiguration().getBoolean(
					"core.multiplemerchants");
		} catch (Exception e) {
			log.warn("Error while getting property core.multiplemerchants");
		}
	}
	
	private String scheme;
	public String getScheme() {
		return scheme;
	}


	public void setScheme(String scheme) {
		this.scheme = scheme;
	}


	public boolean isForceAddSchemeHostAndPort() {
		return forceAddSchemeHostAndPort;
	}


	public void setForceAddSchemeHostAndPort(boolean forceAddSchemeHostAndPort) {
		this.forceAddSchemeHostAndPort = forceAddSchemeHostAndPort;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public boolean isId() {
		return id;
	}


	public void setId(boolean id) {
		this.id = id;
	}

	private boolean forceAddSchemeHostAndPort;
	private String value;
	private boolean id;
	

	public int doStartTag() throws JspException {
		try {

			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			
			HttpSession session = request.getSession();
			
			Locale locale = (Locale) request.getAttribute("LOCALE");
			
			MerchantStore store = (MerchantStore) request.getAttribute("STORE");

			StringBuilder url = new StringBuilder();
			
			if (!StringUtils.isBlank(scheme)) {
				
				if(this.forceAddSchemeHostAndPort) {
					
					if (("https").equalsIgnoreCase(scheme)) {
						scheme = (String) config.getString("core.domain.http.secure",
								"https");
					} 
					
					String domain = config.getString("core.domain.server");
					
					if (store != null && !StringUtils.isBlank(store.getDomainName())) {
						domain = store.getDomainName();
					} else {
						domain = request.getRemoteAddr() + ":" + request.getRemotePort();
					}

					url.append(scheme)
							.append("://").append(domain);

				} else {
					if (("https").equalsIgnoreCase(scheme)) {
						scheme = (String) config.getString("core.domain.http.secure",
								"https");
					} 
				}
				
				String domain = config.getString("core.domain.server");
				
				if (store != null && !StringUtils.isBlank(store.getDomainName())) {
					domain = store.getDomainName();
				} else {
					domain = request.getRemoteAddr() + ":" + request.getRemotePort();
				}
				

				url.append(scheme)
					.append("://").append(domain);
			}
			url.append(request.getContextPath());
			url.append("/").append(value);
			
			/**
			 * @todo
			 */
			url.append(config.getString("core.urls.extension",".html"));
			if(id && store!=null) {
				if(value.contains("&")) {
					url.append("&");
				} else {
					url.append("?");
				}
				url.append("merchantId=").append(store.getMerchantId());
			} else {
				
				//check if multiple merchants
				if(multipleMerchants && store!=null) {
					
					if(value.contains("&")) {
						url.append("&");
					} else {
						url.append("?");
					}
					url.append("merchantId=").append(store.getMerchantId());
				}
			}

			
			pageContext.getOut().print(url.toString());


			
		} catch (Exception ex) {
			log.error(ex);
		}
		return SKIP_BODY;
	}

}
