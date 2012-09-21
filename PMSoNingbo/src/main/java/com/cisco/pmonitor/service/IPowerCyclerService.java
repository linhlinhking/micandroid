package com.cisco.pmonitor.service;

import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.query.PowerCyclerQuery;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IPowerCyclerService {

	public Result<Integer> addPowerCycler(PowerCyclerDo powerCyclerDo) throws ServiceException;
	
	public Result<Integer> updatePowerCycler(PowerCyclerDo powerCyclerDo) throws ServiceException;
	
	public Result<Integer> deletePowerCycler(int id) throws ServiceException;
	
	public Result<PowerCyclerDo> findPowerCyclerById(int id) throws ServiceException;
	
	public Result<PowerCyclerDo> findPowerCyclerByName(String name) throws ServiceException;
	
	public Result<List<PowerCyclerDo>> loadAllPowerCyclers() throws ServiceException;
	
	public Result<Map<String, Object>> loadPowerCyclersByQuery(PowerCyclerQuery query) throws ServiceException;
}
