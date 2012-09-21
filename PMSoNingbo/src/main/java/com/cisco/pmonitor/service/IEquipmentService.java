package com.cisco.pmonitor.service;

import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.EquipmentDo;
import com.cisco.pmonitor.dao.query.EquipmentQuery;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IEquipmentService {
	
	public Result<Integer> addEquipment(EquipmentDo equipmentDo) throws ServiceException;
	
	public Result<Integer> updateEquipment(EquipmentDo equipmentDo) throws ServiceException;
	
	public Result<Integer> deleteEquipment(int id) throws ServiceException;
	
	public Result<EquipmentDo> findEquipmentById(int id) throws ServiceException;
	
	public Result<EquipmentDo> findEquipmentByName(String name) throws ServiceException;
	
	public Result<List<EquipmentDo>> loadAllEquipments() throws ServiceException;
	
	public Result<List<EquipmentDo>> loadEquipmentsByGroupId(int groupId) throws ServiceException;
	
	public Result<Map<String, Object>> loadEquipmentsByQuery(EquipmentQuery query) throws ServiceException;
}
