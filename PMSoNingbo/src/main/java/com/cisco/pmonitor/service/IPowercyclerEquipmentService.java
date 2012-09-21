package com.cisco.pmonitor.service;

import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.PowercyclerEquipmentDo;
import com.cisco.pmonitor.dao.query.PowercyclerEquipmentQuery;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IPowercyclerEquipmentService {

	public Result<Integer> addPowercyclerEquipment(PowercyclerEquipmentDo powercyclerEquipmentDo) throws ServiceException;
	
	public Result<Integer> updatePowercyclerEquipment(PowercyclerEquipmentDo powercyclerEquipmentDo) throws ServiceException;
	
	public Result<Integer> deletePowercyclerEquipment(int id) throws ServiceException;
	
	public Result<PowercyclerEquipmentDo> findPowercyclerEquipmentById(int id) throws ServiceException;
	
	public Result<PowercyclerEquipmentDo> findPowercyclerAndOutlet(int powercyclerId, int outlet)throws ServiceException;
	
	public Result<List<PowercyclerEquipmentDo>> loadAllPowercyclerEquipments() throws ServiceException;
	
	public Result<Map<String, Object>> loadPowercyclerEquipmentsByQuery(PowercyclerEquipmentQuery query) throws ServiceException;
}
