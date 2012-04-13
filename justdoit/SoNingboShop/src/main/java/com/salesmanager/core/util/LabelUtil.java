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
package com.salesmanager.core.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

public class LabelUtil {
	


	private Locale locale;
	
	private static ApplicationContext context = null;
	
	static {
		
		context = SpringUtil.getInstance().getApplicationContext();
		
	}

	private LabelUtil() {

	}

	public static LabelUtil getInstance() {

		return new LabelUtil();
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}

	public String getText(String aTextName) {

		if (locale == null) {
			setLocale(LocaleUtil.getDefaultLocale());
		}
		
		

		return context.getMessage(aTextName, null, locale);
		
	}

	public String getText(HttpServletRequest request, String aTextName) {

		setLocale(LocaleUtil.getLocale(request));
		

		return context.getMessage(aTextName, null, locale);
		
	}

	public String getText(Locale locale, String aTextName) {

		setLocale(locale);
		
		return context.getMessage(aTextName, null, locale);
	}

	public String getText(String lang, String aTextName, String parameter) {
		getLocale(lang);
		return context.getMessage(aTextName, new String[] { parameter }, locale);
	}

	public String getText(Locale locale, String aTextName, List params) {
		Object[] p = new Object[params.size()];
		
		for(int i = 0;i<params.size(); i++) {
			p[i] = params.get(i);
		}
		
		//p = (String[]) params.toArray(p);
		return context.getMessage(aTextName, p, locale);
	}

	public String getText(String lang, String aTextName) {

		getLocale(lang);
		return context.getMessage(aTextName, null, locale);
	}

	private void getLocale(String lang) {
		
		if(StringUtils.isBlank(lang)) {
			lang = LanguageUtil.getDefaultLanguage();
		}
		
		if (lang.equals("en")) {
			setLocale(Locale.ENGLISH);
		} else if (lang.equals("fr")) {
			setLocale(Locale.FRENCH);
		} else {
			setLocale(new Locale(lang));
		}
	}



}




/*class CustomLabelUtil implements SmTextProvider {

	private static List bundleList = new ArrayList();

	static {// load config files

		Configuration props = PropertiesUtil.getConfiguration();
		List lst = (List) props.getProperty("struts.custom.i18n.resources");
		if (lst != null) {
			bundleList = lst;
		}

	}

	public String getText(Locale locale, String key) {

		Iterator bundleListIterator = bundleList.iterator();
		ResourceBundle myResources = null;
		String label = "";
		while (bundleListIterator.hasNext()) {
			String bundle = (String) bundleListIterator.next();
			try {
				myResources = ResourceBundle.getBundle(bundle, locale);
				if (myResources != null) {
					String l = myResources.getString(key);
					if (l != null) {
						label = l;
						break;
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return label;
	}

	public String getText(Locale locale, String key, List parameters) {

		Iterator bundleListIterator = bundleList.iterator();
		ResourceBundle myResources = null;
		String label = "";
		while (bundleListIterator.hasNext()) {
			String bundle = (String) bundleListIterator.next();

			try {

				myResources = ResourceBundle.getBundle(bundle, locale);
				if (myResources != null) {
					String l = myResources.getString(key);
					if (l != null) {
						MessageFormat mFormat = new MessageFormat(l);
						String[] params = new String[parameters.size()];
						params = (String[]) parameters.toArray(params);
						l = mFormat.format(params);
						label = l;
						break;
					}
				}

			} catch (Exception e) {
				// Handle exception
			}

		}
		return label;
	}

	public String getText(Locale locale, String key, String parameter) {
		Iterator bundleListIterator = bundleList.iterator();
		ResourceBundle myResources = null;
		String label = "";
		while (bundleListIterator.hasNext()) {
			String bundle = (String) bundleListIterator.next();

			try {

				myResources = ResourceBundle.getBundle(bundle, locale);
				if (myResources != null) {
					String l = myResources.getString(key);
					if (l != null) {
						MessageFormat mFormat = new MessageFormat(l);
						l = mFormat.format(parameter);
						label = l;
						break;
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return label;
	}
}*/
