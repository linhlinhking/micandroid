package com.cisco.pmonitor.service;

import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.service.exception.ServiceException;

public interface IPowerCommand {

	
	public Map<Integer, Boolean> execute(PowerCyclerDo pco, Map<Integer, Boolean> outletMap, int state) throws ServiceException;
}
