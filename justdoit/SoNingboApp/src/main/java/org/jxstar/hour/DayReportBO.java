
package org.jxstar.hour;

import java.util.List;
import java.util.Map;

import org.jxstar.dao.DaoParam;
import org.jxstar.dao.DmDao;
import org.jxstar.service.BusinessObject;
import org.jxstar.util.DateUtil;
import org.jxstar.util.MapUtil;
import org.jxstar.util.factory.FactoryUtil;

/**
 * 日报处理类
 *
 * @author TonyTan
 * @version 1.0, 2010-10-30
 */
public class DayReportBO extends BusinessObject {
    private static final long serialVersionUID = 1L;

    /**
     * 1、负责创建日报记录；
     * 2、判断同一天日报不能重复；
     * 3、把上次日报的明日计划导入到当前日报的主要完成任务；
     * @param reportDate -- 报告日期，格式：yyyy-mm-dd
     * @param mpUser -- 用户信息
     * @return
     */
    public String createReport(String reportDate, Map<String,String> mpUser) {
        if (reportDate == null || reportDate.length() == 0 ||
                mpUser == null || mpUser.isEmpty()) {
            setMessage("创建日报的参数不完整！");
            return _returnFaild;
        }
        
        _log.showDebug("创建日报的参数：{0}, {1}", reportDate, mpUser.toString());
        
        String userId = mpUser.get("user_id");
        if (hasReport(userId, reportDate)) {
            setMessage("日期【{0}】的日报已存在！", reportDate);
            return _returnFaild;
        }
        
        //创建日报
        String dreportId = createDay(reportDate, mpUser);
        if (dreportId == null || dreportId.length() == 0) {
            return _returnFaild;
        }
        
        this.setReturnData("{dreportId:'"+ dreportId +"'}");
        
        return _returnSuccess;
    }
    
    /**
     * 创建今日日报
     * @param reportDate -- 报告日期
     * @param mpUser -- 用户信息
     * @return
     */
    private String createDay(String reportDate, Map<String,String> mpUser) {
        String userId = mpUser.get("user_id");
        
        Map<String,String> mpDay = FactoryUtil.newMap();
        mpDay.put("auditing", "0");
        mpDay.put("user_id", userId);
        mpDay.put("user_name", mpUser.get("user_name"));
        mpDay.put("report_date", reportDate);
        mpDay.put("write_date", reportDate);
        mpDay.put("dept_id", mpUser.get("dept_id"));
        mpDay.put("dept_name", mpUser.get("dept_name"));
        mpDay.put("add_userid", userId);
        mpDay.put("add_date", DateUtil.getTodaySec());
        //新增日报记录
        String dreportId = DmDao.insert("ph_dayreport", mpDay);
        _log.showDebug("新增日报记录ID=" + dreportId);
        
        //取上次日报信息
        Map<String, String> mpOldDay = lastOldReport(userId, reportDate);
        if (mpOldDay == null || mpOldDay.isEmpty()) {
            _log.showDebug("没有找到上次日报记录！");
            return dreportId;
        }
        String oldId = mpOldDay.get("dreport_id");
        String oldDate = mpOldDay.get("report_date");
        _log.showDebug("上次日报记录ID=" + oldId + ";上次日报日期=" + oldDate);
        
        //复制上次日报的明日计划
        if (!copyMorrowPlan(oldId, dreportId)) {
            setMessage("复制上次日报的明日计划失败！");
            return "";
        }
        
        return dreportId;
    }
    
    /**
     * 复制上次日报的明日计划
     * @param oldId
     * @param dreportId
     * @return
     */
    private boolean copyMorrowPlan(String oldId, String dreportId) {
        String sql = "select task_type_code,task_type,task_desc,project_id,project_name," +
        		"add_userid from ph_morrowplan where dreport_id = ?";
        DaoParam param = _dao.createParam(sql);
        param.addStringValue(oldId);
        List<Map<String, String>> lsday = _dao.query(param);
        if (lsday == null || lsday.isEmpty()) return true;
        
        _log.showDebug("复制上次日报的明日计划记录数=" + lsday.size());
        
        for(Map<String, String> mpday : lsday) {
            mpday.put("dreport_id", dreportId);
            mpday.put("add_date", DateUtil.getTodaySec());
            String newId = DmDao.insert("ph_timesheet", mpday);
            if (newId.length() == 0) return false;
        }
        
        return true;
    }
    
    /**
     * 取上次日报记录ID
     * @param userId -- 用户ID
     * @param reportDate -- 报告日期
     * @return
     */
    private Map<String, String> lastOldReport(String userId, String reportDate) {
        String sql = "select dreport_id, report_date from ph_dayreport where user_id = ? and report_date < ? order by report_date desc";
        //String sql = "select max(report_date) as report_date from ph_dayreport where user_id = ? and report_date < ?";
        
        DaoParam param = _dao.createParam(sql);
        param.addStringValue(userId);
        param.addDateValue(reportDate);
        
        Map<String, String> mpday = _dao.queryMap(param);
        return mpday;
    }
    
    /**
     * 是否有当天的日报
     * @param userId -- 用户ID
     * @param reportDate -- 报告日期
     * @return
     */
    private boolean hasReport(String userId, String reportDate) {
        String sql = "select count(*) as cnt from ph_dayreport where user_id = ? and report_date = ?";
        
        DaoParam param = _dao.createParam(sql);
        param.addStringValue(userId);
        param.addDateValue(reportDate);
        
        Map<String, String> mpcnt = _dao.queryMap(param);
        
        return MapUtil.hasRecord(mpcnt);
    }
}
