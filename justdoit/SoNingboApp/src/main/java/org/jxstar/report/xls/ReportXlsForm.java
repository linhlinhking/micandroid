/*
 * ReportXlsForm.java 2010-11-11
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.report.xls;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jxstar.report.ReportException;
import org.jxstar.report.util.ReportXlsUtil;

/**
 * 输出Excel表单报表。
 *
 * @author TonyTan
 * @version 1.0, 2010-11-11
 */
public class ReportXlsForm extends ReportXls {

	public Object output() throws ReportException {
		_log.showDebug("excel form report output ...");
		//取报表所属功能ID，用于报表图片输出时取功能表名、主键信息用
		String funId = _mpReptInfo.get("fun_id");
		//报表区域ID
        String areaId = _mpMainArea.get("area_id");
        
		//每输出一页构建一个临时报表对象
		HSSFWorkbook tmpwb = ReportXlsUtil.readWorkBook(_xlsFile);
		HSSFSheet tmpsheet = tmpwb.getSheetAt(0);

		HSSFSheet sheet = _hssfWB.getSheetAt(0);

		Map<String, String> mpValue = null;
		for (int i = 0; i < _lsMainRecord.size(); i++) {
			mpValue = _lsMainRecord.get(i);
			
			//填写一条记录
			if (i == 0) {
				sheet = ReportXlsUtil.fillForm(funId, sheet, mpValue, _lsMainCol, i + 1, _lsMainRecord.size());
				sheet = ReportXlsUtil.fillCheckInfo(funId, areaId, sheet, mpValue);
				sheet = ReportXlsUtil.fillHead(sheet, _lsHeadInfo);
			} else {
				tmpsheet = ReportXlsUtil.fillForm(funId, tmpsheet, mpValue, _lsMainCol, i + 1, _lsMainRecord.size());
				tmpsheet = ReportXlsUtil.fillCheckInfo(funId, areaId, tmpsheet, mpValue);
				tmpsheet = ReportXlsUtil.fillHead(tmpsheet, _lsHeadInfo);

				sheet = ReportXlsUtil.appendSheet(sheet, tmpsheet);
				tmpwb = ReportXlsUtil.readWorkBook(_xlsFile);
				tmpsheet = tmpwb.getSheetAt(0);
			}

			//判断报表是否允许输出
			if (!ReportXlsUtil.isAllowOut(sheet)) break;
		}

		return _hssfWB;
	}

}
