package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.EquipmentDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.EquipmentQuery;

public interface IEquipmentDao {

	public int insert(EquipmentDo equipmentDo) throws DaoException;
	
	public int update(EquipmentDo equipmentDo) throws DaoException;
	
	public int delete(int id) throws DaoException;
	
	public List<EquipmentDo> loadAllEquipments() throws DaoException;
	
	public List<EquipmentDo> loadEquipmentsByGroupId(int groupId) throws DaoException;
	
	public EquipmentDo findEquipmentById(int id) throws DaoException;
	
	public EquipmentDo findEquipmentByName(String name) throws DaoException;
	
	public List<EquipmentQuery> loadEquipmentsbyQuery(EquipmentQuery query)throws DaoException;
	
	public int loadTotalNumByQuery(EquipmentQuery query) throws DaoException;
}
