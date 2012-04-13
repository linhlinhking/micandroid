package com.salesmanager.core.util;

import java.util.*;
import java.util.Locale;

public class MessagesListUtil {
	
		private Locale locale;
		public MessagesListUtil(Locale locale) {
			this.locale = locale;
		}
	
		private List errorMessages;
		private List successMessages;

		public String getErrorMessage() {
			//return errorMessage;
			StringBuilder sb = new StringBuilder();
			if(errorMessages!=null) {
				for(Object o : errorMessages) {
					String s = (String)o;
					sb.append(s).append("<br/>");
				}
			} else {
				return "";
			}
			return sb.toString();
		}

		public String getSuccessMessage() {
			//return successMessage;
			StringBuilder sb = new StringBuilder();
			if(successMessages!=null) {
				for(Object o : successMessages) {
					String s = (String)o;
					sb.append(s).append("<br/>");
				}
			} else {
				return "";
			}
			return sb.toString();
		}

		
		
		public void addErrorMessage(String messageKey) {

			LabelUtil label = LabelUtil.getInstance();
			label.setLocale(locale);
			
			if(errorMessages==null) {
				errorMessages = new ArrayList();
			}
			
			errorMessages.add(label.getText(
					locale, messageKey));

		}
		
		public void addSuccessMessage(String messageKey) {

			LabelUtil label = LabelUtil.getInstance();
			label.setLocale(locale);
			
			if(successMessages==null) {
				successMessages = new ArrayList();
			}
			
			successMessages.add(label.getText(
					locale, messageKey));

		}


}
