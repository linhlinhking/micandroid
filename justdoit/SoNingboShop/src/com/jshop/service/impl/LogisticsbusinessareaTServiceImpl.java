package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.LogisticsbusinessareaTDaoImpl;
import com.jshop.entity.LogisticsbusinessareaT;
import com.jshop.service.LogisticsbusinessareaTService;
@Service("logisticsbusinessareaTServiceImpl")
@Scope("prototype")
public class LogisticsbusinessareaTServiceImpl implements LogisticsbusinessareaTService {
	@Resource(name="logisticsbusinessareaTDaoImpl")
	private LogisticsbusinessareaTDaoImpl logisticsbusinessareaTDaoImpl;
	
	public LogisticsbusinessareaTDaoImpl getLogisticsbusinessareaTDaoImpl() {
		return logisticsbusinessareaTDaoImpl;
	}

	public void setLogisticsbusinessareaTDaoImpl(LogisticsbusinessareaTDaoImpl logisticsbusinessareaTDaoImpl) {
		this.logisticsbusinessareaTDaoImpl = logisticsbusinessareaTDaoImpl;
	}

	public int addLogisticsbusinessarea(LogisticsbusinessareaT lba) {
		return this.getLogisticsbusinessareaTDaoImpl().addLogisticsbusinessarea(lba);
	}

	public int countfindAllLogisticsbusinessareaT() {
		return this.getLogisticsbusinessareaTDaoImpl().countfindAllLogisticsbusinessareaT();
	}

	public int delLogisticsbusinessarea(String[] list) {
		return this.getLogisticsbusinessareaTDaoImpl().delLogisticsbusinessarea(list);
	}

	public List<LogisticsbusinessareaT> findAllLogisticsbusinessareaT(int currentPage, int lineSize) {
		return this.getLogisticsbusinessareaTDaoImpl().findAllLogisticsbusinessareaT(currentPage, lineSize);
	}

	public LogisticsbusinessareaT findLogisticsbusinessareaTById(String logbusareaid) {
		return this.getLogisticsbusinessareaTDaoImpl().findLogisticsbusinessareaTById(logbusareaid);
	}

	public int updateLogisticsbusinessarea(LogisticsbusinessareaT lba) {
		return this.getLogisticsbusinessareaTDaoImpl().updateLogisticsbusinessarea(lba);
	}

	public int updateLogisticsbusinessareaBystate(String[] list, String state) {
		return this.getLogisticsbusinessareaTDaoImpl().updateLogisticsbusinessareaBystate(list, state);
	}

	public List<LogisticsbusinessareaT> findAllLogisticsbusinessareaTBylogisticsid(String logisticsid) {
		return this.getLogisticsbusinessareaTDaoImpl().findAllLogisticsbusinessareaTBylogisticsid(logisticsid);
	}
}
