package com.xero.website.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name = "tb_os_language")
public class OSLanguage extends BaseEntity {

	private static final long serialVersionUID = 4429183195154257383L;

	private String language;

	private String languageCode;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

}
