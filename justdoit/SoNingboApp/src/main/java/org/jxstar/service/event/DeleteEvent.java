/*
 * DeleteEvent.java 2009-5-29
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.service.event;


import org.jxstar.control.action.RequestContext;
import org.jxstar.dao.DaoParam;
import org.jxstar.service.BoException;
import org.jxstar.service.BusinessEvent;
import org.jxstar.service.define.FunctionDefine;
import org.jxstar.util.StringUtil;
import org.jxstar.util.resource.JsMessage;
import org.jxstar.util.resource.JsParam;

/**
 * 业务记录删除事件。
 *
 * @author TonyTan
 * @version 1.0, 2009-5-29
 */
public class DeleteEvent extends BusinessEvent {
	private static final long serialVersionUID = 6950498972296811537L;

	/**
	 * 执行删除方法
	 */
	public String delete(RequestContext requestContext) {
		try {
			init(requestContext);
		} catch (BoException e) {
			_log.showError(e);
			return _returnFaild;
		}
		
		String[] asKey = requestContext.getRequestValues(JsParam.KEYID);
		if (asKey == null || asKey.length == 0) {
			//找不到删除记录的键值！
			setMessage(JsMessage.getValue("functionbm.deletekeynull"));
			return _returnFaild;
		}
		
		//取删除SQL语句
		String delSql = _funObject.getDeleteSQL();
		_log.showDebug("delete main sql=" + delSql);
		
		String funType = _funObject.getElement("reg_type");
		for (int i = 0; i < asKey.length; i++) {
			String sKeyID = asKey[i];
			//如果为树形主功能,则删除下级数据
			if (funType.equals("treemaster")) {
				sKeyID += "%";
			}
			_log.showDebug("delete keyid=" + sKeyID);
			
			//删除子记录
			if (! deleteSub(sKeyID)) {
				return _returnFaild;
			}
			
			//删除主记录
			DaoParam param = _dao.createParam(delSql);
			param.addStringValue(sKeyID).setDsName(_dsName);
			if (!_dao.update(param)) {
				//删除记录失败！
				setMessage(JsMessage.getValue("functionbm.deletefaild"));
				return _returnFaild;
			}
		}
		
		return _returnSuccess;
	}
	
	/**
	 * 删除当前功能的子记录
	 * 
	 * @param sKeyID - 外键值
	 * @return boolean
	 */
	private boolean deleteSub(String sKeyID) {
		//取当前功能的子功能定义ID字符串
		String sSubID = _funObject.getElement("subfun_id");
		if (sSubID.length() == 0) {
			return true;
		}
		
		FunctionDefine subfun = null;
		String[] aSubID = sSubID.split(",");
		//分别删除单个子功能的数据
		for (int i = 0; i < aSubID.length; i++) {
			String subFunId = aSubID[i].trim();
			//创建子功能对象
			subfun = _funManger.getDefine(subFunId);
			if (subfun == null) {
				_log.showWarn("create sub function object fiald, funid:{0}! ", subFunId);
				return false;
			}
			
			//如果该子功能定义了外键,则取外键字段名,如果没有定义则采用主表的主键名
			String fkCol = subfun.getElement("fk_col");
			if (fkCol.length() == 0) {
				fkCol = StringUtil.getNoTableCol(_pkColName);
			}
			
			//构建删除子功能数据的SQL
			StringBuilder subdel = new StringBuilder("delete from ")
				.append(subfun.getElement("table_name"))
				.append(" where " + fkCol + " like ?");
			_log.showDebug("delete subfun sql=" + subdel.toString());
			
			DaoParam param = _dao.createParam(subdel.toString());
			param.addStringValue(sKeyID).setDsName(_dsName);
			if (!_dao.update(param)) {
				_log.showWarn("delete {0} data faild! ", subFunId);
				return false;
			}
		}
		
		return true;
	}
}
