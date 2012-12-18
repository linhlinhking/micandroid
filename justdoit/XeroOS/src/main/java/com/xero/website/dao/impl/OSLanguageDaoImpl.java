package com.xero.website.dao.impl;

import org.springframework.stereotype.Repository;

import com.xero.core.common.dao.impl.BaseDaoImpl;
import com.xero.website.bean.OSLanguage;
import com.xero.website.dao.OSLanguageDao;

@Repository("oSLanguageDao")
public class OSLanguageDaoImpl extends BaseDaoImpl<OSLanguage, Integer>
		implements OSLanguageDao {

	public OSLanguageDaoImpl() {
		super(OSLanguage.class);
	}
}
