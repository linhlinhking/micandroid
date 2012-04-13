/*
 * SysDataUtil.java 2008-5-18
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.service.util;

import java.util.List;
import java.util.Map;


import org.jxstar.dao.BaseDao;
import org.jxstar.dao.DaoParam;
import org.jxstar.service.BoException;
import org.jxstar.service.define.DefineDataManger;
import org.jxstar.service.define.FunDefineDao;
import org.jxstar.util.MapUtil;
import org.jxstar.util.StringUtil;
import org.jxstar.util.factory.FactoryUtil;
import org.jxstar.util.log.Log;
import org.jxstar.util.resource.JsMessage;


/**
 * 数据权限工具类。
 * 
 * @author TonyTan
 * @version 1.0, 2008-5-18
 */
public class SysDataUtil {
	private static Log _log = Log.getInstance();
	private static BaseDao _dao = BaseDao.getInstance();

	/**
	 * 取该用户指定功能的数据权限过滤语句。
	 * 
	 * @param userId -- 用户ID
	 * @param funId -- 功能ID
	 * @return String
	 */
	public static String getDataWhere(String userId, String funId) throws BoException {
		if (userId == null || userId.length() == 0) {
			throw new BoException(JsMessage.getValue("param.null.userid"));
		}
		if (funId == null || funId.length() == 0) {
			throw new BoException(JsMessage.getValue("param.null.funid"));
		}
		
		//判断用户是否为系统管理员
		boolean isAdmin = SysUserUtil.isAdmin(userId);
		if (isAdmin) return "";
		
		//获取用户数据类型值定义
		List<Map<String,String>> lsData;
		
		//判断是否有指定功能的数据权限
		if (hasPropFun(userId, funId)) {
			lsData = queryPropDataType(userId, funId);
		} else {
			lsData = queryDataType(userId, funId);
		}
		
		if (lsData.isEmpty()) {
			//_log.showDebug("query [userid={0} funid={1}] user data type is null!", userId, funId);
			return ""; 
		}
		
		return buildWhere(funId, lsData);
	}
	
	/**
	 * 取树形数据的数据权限过滤语句，与普通功能的差别是：
	 * 如果是树形功能主键作为数据权限控制字段，则过滤语句取当前级别的数据类型值，
	 * 如：数据权限设置部门ID为10010001，则
	 *    在第1级只能看到 = '1001'的记录，
	 *    在第2级只能看到 = '10010001'的记录，
	 *    在第3级能看到 like '10010001%'的记录。
	 * 
	 * @param userId -- 用户ID
	 * @param funId -- 功能ID
	 * @param level -- 当前控制的级别
	 * @return
	 * @throws BoException
	 */
	public static String getTreeDataWhere(String userId, String funId, int level) throws BoException {
		if (userId == null || userId.length() == 0) {
			throw new BoException(JsMessage.getValue("param.null.userid"));
		}
		if (funId == null || funId.length() == 0) {
			throw new BoException(JsMessage.getValue("param.null.funid"));
		}
		
		//判断用户是否为系统管理员
		boolean isAdmin = SysUserUtil.isAdmin(userId);
		if (isAdmin) return "";
		
		//获取用户数据类型值定义
		List<Map<String,String>> lsData = queryTreeDataType(userId, funId, level);
		if (lsData.isEmpty()) {
			_log.showDebug("query [userid={0} funid={1}] user data type is null!", userId, funId);
			return ""; 
		}
		
		return buildWhere(funId, lsData);
	}
	
	//提取构建SQL的公共部分
	private static String buildWhere(String funId, List<Map<String,String>> lsData) 
							throws BoException {
		//取当前功能的字段信息
		List<String> lsField = getFunColumn(funId);
		//取功能定义from语句中的相关表名
		String[] tables = getFunTables(funId);
		if (tables == null) {
			throw new BoException(JsMessage.getValue("system.fromsql.error"));
		}
		
		String oldField = "";
		//保存单个数据权限控制字段拼凑的where
		StringBuilder sbType = new StringBuilder();
		//保存所有数据权限控制字段拼凑的where
		StringBuilder sbWhere = new StringBuilder();
		for(int i = 0, n = lsData.size(); i < n; i++) {
			Map<String,String> mpData = lsData.get(i);
			
			String hasSub = mpData.get("has_sub");
			String typeValue = mpData.get("dtype_data");
			String typeField = mpData.get("dtype_field");
			
			//如果数据值为空，不能查询数据
			if (typeValue == null || typeValue.length() == 0) {
				typeValue = "&notdata&";
			}
			
			//取功能字段列定义中的字段名
			String funField = getTableField(tables, typeField, lsField);
			//如果字段列表中没有该字段，则不处理
			if (funField.length() == 0) continue;
			//_log.showDebug("--------i="+i+" oldField="+oldField+" typeField=" + typeField);
			//如果字段相同，则添加or，否则添加and
			if (!oldField.equals(typeField)) {
				oldField = typeField;
				
				if (i > 0) {
					//取单个字段的where，并清除单个字段的where
					String oneWhere = sbType.substring(0, sbType.length()-4);
					sbType.delete(0, sbType.length());
					
					//把同一个字段where添加到总where中
					sbWhere.append("(").append(oneWhere).append(") and ");
					_log.showDebug("--------data where sql=" + sbWhere.toString());
				}
			} 
			
			//添加单个值比较的where语句
			sbType.append(funField);
			if (hasSub.equals("1")) {
				sbType.append(" like '").append(typeValue).append("%'");
			} else {
				sbType.append(" = '").append(typeValue).append("'");
			}
			sbType.append(" or ");
			//_log.showDebug("--------data type sql=" + sbType.toString());
		}
		
		//最后一个字段的where
		if (sbType.length() > 0) {
			String endWhere = sbType.substring(0, sbType.length()-4);
			sbWhere.append("(").append(endWhere).append(") and ");
		}
		
		//取数据权限的where
		String dataWhere = "";
		if (sbWhere.length() > 0) {
			dataWhere = sbWhere.substring(0, sbWhere.length()-5);
		}
		_log.showDebug("--------data dataWhere=" + dataWhere);
		
		return dataWhere;
	}
	
	/**
	 * 判断是否为指定功能设置了数据权限。
	 * @param userId -- 用户ID
	 * @param funId -- 功能ID
	 * @return
	 */
	private static boolean hasPropFun(String userId, String funId) {
		String sql = "select count(*) as cnt from sys_user_funx where fun_id = ? and user_id = ?";
		
		DaoParam param = _dao.createParam(sql);
		param.addStringValue(funId);
		param.addStringValue(userId);
		
		return MapUtil.hasRecord(_dao.queryMap(param));
	}
	
	/**
	 * 取用户与指定功能的数据权限控制值
	 * 
	 * @param userId -- 用户ID
	 * @param funId -- 功能ID
	 * @return
	 */
	private static List<Map<String,String>> queryDataType(String userId, String funId) {
		String sql = "select distinct dtype_data, has_sub, dtype_field " +
					 "from v_user_datatype where user_id = ? and fun_id = ? " + 
					 "order by dtype_field";
		
		DaoParam param = _dao.createParam(sql);
		param.addStringValue(userId);
		param.addStringValue(funId);
		
		return _dao.query(param);
	}
	
	/**
	 * 如果单独针对用户的某个功能设置了数据权限，则取指定的数据权限
	 * 
	 * @param userId -- 用户ID
	 * @param funId -- 功能ID
	 * @return
	 */
	private static List<Map<String,String>> queryPropDataType(String userId, String funId) {
		String sql = "select distinct dtype_data, has_sub, dtype_field " +
					 "from v_userx_datatype where user_id = ? and fun_id = ? " + 
					 "order by dtype_field";
		
		DaoParam param = _dao.createParam(sql);
		param.addStringValue(userId);
		param.addStringValue(funId);
		
		return _dao.query(param);
	}
	
	/**
	 * 如果是树形功能主键作为数据权限控制字段，则过滤语句取当前级别的数据类型值。
	 * 
	 * @param userId -- 用户ID
	 * @param funId -- 功能ID
	 * @param level -- 当前级别
	 * @return
	 */
	private static List<Map<String,String>> queryTreeDataType(String userId, String funId, int level) {
		List<Map<String,String>> lsData;		
		//判断是否有指定功能的数据权限
		if (hasPropFun(userId, funId)) {
			lsData = queryPropDataType(userId, funId);
		} else {
			lsData = queryDataType(userId, funId);
		}
		if (lsData.isEmpty()) return lsData;
		
		//取功能定义信息
		Map<String,String> mpFun = FunDefineDao.queryFun(funId);
		
		//取功能定义主键值
		String pk_col = mpFun.get("pk_col").toLowerCase();
		pk_col = StringUtil.getNoTableCol(pk_col);
		
		for (int i = 0, n = lsData.size(); i < n; i++) {
			Map<String,String> mpData = lsData.get(i);
			
			String dtype_data = mpData.get("dtype_data");
			String dtype_field = mpData.get("dtype_field");
			
			//如果主键字段，则处理数据类型值
			if (pk_col.equals(dtype_field)) {
				if (dtype_data.length() <= level*4) continue;
				
				dtype_data = dtype_data.substring(0, level*4);
				mpData.put("dtype_data", dtype_data);
				mpData.put("has_sub", "0");
			}
		}
		
		return lsData;
	}
	
	/**
	 * 取功能中是否包括数据权限控制字段，如果有则返回控制字段，否则为空
	 * @param tables -- 当前功能相关的表名
	 * @param field -- 控制字段
	 * @param lsCol -- 功能字段列表
	 * @return
	 */
	private static String getTableField(String[] tables, String field, List<String> lsCol) {
		StringBuilder sbField = new StringBuilder();
		for (int i =0, n = tables.length; i < n; i++){
			sbField.append(tables[i] + "." + field);		

			if (lsCol.contains(sbField.toString())){
				break;
			} else {
				if (field.equalsIgnoreCase("add_userid")) {
					break;
				}	
			}
			sbField.delete(0, sbField.length());
		}
		
		return sbField.toString();
	}
	
	/**
	 * 取一个功能的字段列表
	 * @param funId -- 功能ID
	 * @return
	 */
	private static List<String> getFunColumn(String funId) {
		List<String> lsRet = FactoryUtil.newList();
		
		//取字段设计信息
		DefineDataManger config = DefineDataManger.getInstance();
		List<Map<String,String>> lsCol = config.getColData(funId);
		
		for (int i = 0, n = lsCol.size(); i < n; i++) {
			Map<String,String> mp = lsCol.get(i);
			
			lsRet.add(mp.get("col_code"));
		}
		
		return lsRet;
	}
	
	/**
	 * 取功能定义from语句中的相关表名
	 * @param funId -- 功能ID
	 * @return
	 */
	private static String[] getFunTables(String funId) {
		//取功能定义信息
		DefineDataManger config = DefineDataManger.getInstance();
		Map<String,String> mpFun = config.getFunData(funId);
		
		//取功能定义from语句
		String formTable = mpFun.get("from_sql").toLowerCase();
		if (formTable == null || formTable.length() == 0) return null;
		//取功能主表名
		String mainTable = mpFun.get("table_name").toLowerCase();
		
		String[] froms = formTable.split("from ");
		if (froms.length < 2) return null;
		String[] tables = froms[1].split(",");
		
		//清除表名间空格，并把主表名排在第1个
		for (int i =0, n = tables.length; i < n ; i ++){
			tables[i] = tables[i].trim();
			
			if (tables[i].equals(mainTable) && i > 0){
				String strTmp = tables[0];
				tables[0] = mainTable;
				tables[i] = strTmp;
			}
		}
		return tables;
	}
}
