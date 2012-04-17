package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.LogisticsBusinessTDaoImpl;
import com.jshop.entity.LogisticsBusinessT;
import com.jshop.service.LogisticsBusinessTService;
@Service("logisticsBusinessTServiceImpl")
@Scope("prototype")
public class LogisticsBusinessTServiceImpl implements LogisticsBusinessTService {
	@Resource(name="logisticsBusinessTDaoImpl")
	private LogisticsBusinessTDaoImpl logisticsBusinessTDaoImpl;

	public LogisticsBusinessTDaoImpl getLogisticsBusinessTDaoImpl() {
		return logisticsBusinessTDaoImpl;
	}

	public void setLogisticsBusinessTDaoImpl(LogisticsBusinessTDaoImpl logisticsBusinessTDaoImpl) {
		this.logisticsBusinessTDaoImpl = logisticsBusinessTDaoImpl;
	}

	public int delLogisticsBusiness(String[] list) {
		return this.getLogisticsBusinessTDaoImpl().delLogisticsBusiness(list);
	}

	public int updateLogisticsBusiness(LogisticsBusinessT lb) {
		return this.getLogisticsBusinessTDaoImpl().updateLogisticsBusiness(lb);
	}

	public int addLogisticsBusiness(LogisticsBusinessT lb) {
		return this.getLogisticsBusinessTDaoImpl().addLogisticsBusiness(lb);
	}

	public int countfindAllLogisticsBusiness() {
		return this.getLogisticsBusinessTDaoImpl().countfindAllLogisticsBusiness();
	}

	public List<LogisticsBusinessT> findAllLogisticsBusiness(int currentPage, int lineSize) {
		return this.getLogisticsBusinessTDaoImpl().findAllLogisticsBusiness(currentPage, lineSize);
	}

	public LogisticsBusinessT findLogisticsBusinessById(String logisticsid) {
		return this.getLogisticsBusinessTDaoImpl().findLogisticsBusinessById(logisticsid);
	}

	public List<LogisticsBusinessT> findAllLogisticsBusinessWithoutPage() {
		return this.getLogisticsBusinessTDaoImpl().findAllLogisticsBusinessWithoutPage();
	}

	public List<LogisticsBusinessT> findAllLogisticsBusinessTjson() {
		return this.getLogisticsBusinessTDaoImpl().findAllLogisticsBusinessTjson();
	}
}
