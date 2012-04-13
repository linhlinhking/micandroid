/*
 * ReportXlsGrid.java 2010-11-11
 * 
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
package org.jxstar.report.xls;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.jxstar.report.ReportException;
import org.jxstar.report.util.ReportXlsUtil;
import org.jxstar.util.resource.JsMessage;

/**
 * 输出Excel表格报表。
 *
 * @author TonyTan
 * @version 1.0, 2010-11-11
 */
public class ReportXlsGrid extends ReportXls {

	public Object output() throws ReportException {
		_log.showDebug("excel grid report output ...");

		String area_num = _mpMainArea.get("page_size");
		if (area_num.length() == 0) {//"报表输出错误：报表区域每页行数不能为空！"
			throw new ReportException(JsMessage.getValue("reportxlsgrid.hint01"));
		}

		//报表输出开始行
		int pos = 0;
		//报表每页行数
		int pageSize = Integer.parseInt(area_num);
		//报表页数
		int pageNum = ReportXlsUtil.calPageNum(_lsMainRecord.size(), pageSize);

		//每输出一页构建一个临时报表对象
		HSSFWorkbook tmpwb = ReportXlsUtil.readWorkBook(_xlsFile);
		HSSFSheet tmpsheet = tmpwb.getSheetAt(0);

		HSSFSheet sheet = _hssfWB.getSheetAt(0);

		//输出所有报表页
		for (int i = 0; i < pageNum; i++) {
			pos = i * pageSize;

			if (i == 0) {
				sheet = ReportXlsUtil.fillGrid(sheet, _lsMainRecord, _lsMainCol, pageSize, pos, i + 1, pageNum);
				sheet = ReportXlsUtil.fillHead(sheet, _lsHeadInfo);
			} else {
				tmpsheet = ReportXlsUtil.fillGrid(tmpsheet, _lsMainRecord, _lsMainCol, pageSize, pos, i + 1, pageNum);
				tmpsheet = ReportXlsUtil.fillHead(tmpsheet, _lsHeadInfo);

				//每输出一页临时报表，就添加到原报表中
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
