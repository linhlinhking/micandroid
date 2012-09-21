package com.cisco.pmonitor.service;

import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IPowerService {

	
	public Result<Map<PowerCyclerDo, Map<Integer, Boolean>>> turnPowerOn(int[] ids) throws ServiceException;
	
	public Result<Map<PowerCyclerDo, Map<Integer, Boolean>>> turnPowerOff(int[] ids) throws ServiceException;
}
