package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.PowercyclerEquipmentDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.PowercyclerEquipmentQuery;

public interface IPowercyclerEquipmentDao {

	public int insert(PowercyclerEquipmentDo powercyclerEquipmentDo) throws DaoException;
	
	public int update(PowercyclerEquipmentDo powercyclerEquipmentDo) throws DaoException;
	
	public int delete(int id) throws DaoException;
	
	public List<PowercyclerEquipmentDo> loadAllPowercyclerEquipments() throws DaoException;
	
	public PowercyclerEquipmentDo findPowercyclerEquipmentById(int id) throws DaoException;
	
	public PowercyclerEquipmentDo findPowercyclerAndOutlet(int powercyclerId, int outlet) throws DaoException;
	
	public List<PowercyclerEquipmentQuery> loadPowercyclerEquipmentsbyQuery(PowercyclerEquipmentQuery query)throws DaoException;
	
	public int loadTotalNumByQuery(PowercyclerEquipmentQuery query) throws DaoException;
}
