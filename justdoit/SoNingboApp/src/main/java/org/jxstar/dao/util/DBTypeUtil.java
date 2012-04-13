/*
 * DBTypeUtil.java 2008-4-8
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.dao.util;

import org.jxstar.dao.pool.DataSourceConfig;
import org.jxstar.dao.pool.DataSourceConfigManager;

/**
 * 数据库类型工具类.
 * 
 * @author TonyTan
 * @version 1.0, 2008-4-8
 */
public class DBTypeUtil {

	/**
	 * 获取缺省数据源的数据库类型.
	 * 
	 * @return String
	 */
	public static String getDbmsType() {
		return getDbmsType(DataSourceConfig.getDefaultName());
	}
	
	/**
	 * 获取指定数据源的数据库类型.
	 * 
	 * @param sDataSource - 数据源名
	 * @return String
	 */
	public static String getDbmsType(String sDataSource) {
		if (sDataSource == null || sDataSource.length() == 0) {
			sDataSource = DataSourceConfig.getDefaultName();
		}
		
		//取数据源配置对象
		DataSourceConfig dsc = DataSourceConfigManager.getInstance().
					getDataSourceConfig(sDataSource);
		if (dsc == null) return "";
		
		return dsc.getDbmsType();
	}
}
