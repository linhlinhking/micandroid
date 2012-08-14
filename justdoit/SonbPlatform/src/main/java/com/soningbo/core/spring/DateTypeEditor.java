package com.soningbo.core.spring;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.util.StringUtils;

public class DateTypeEditor extends PropertyEditorSupport {
	public static final DateFormat DATE_FORMAT_LONG = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static final DateFormat DATE_FORMAT_SHORT = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final int SHORT_DATE = 10;

	public void setAsText(String text) throws IllegalArgumentException {
		text = text.trim();
		if (!StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		try {
			if (text.length() <= SHORT_DATE) {
				setValue(new java.sql.Date(DATE_FORMAT_SHORT.parse(text)
						.getTime()));
			} else {
				setValue(new java.sql.Timestamp(DATE_FORMAT_LONG.parse(text)
						.getTime()));
			}
		} catch (ParseException ex) {
			IllegalArgumentException iae = new IllegalArgumentException(
					"Could not parse date: " + ex.getMessage());
			iae.initCause(ex);
			throw iae;
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DATE_FORMAT_LONG.format(value) : "");
	}
}
