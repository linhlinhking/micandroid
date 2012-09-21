package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.PowerCyclerQuery;

public interface IPowerCyclerDao {

	public int insert(PowerCyclerDo powerCyclerDo) throws DaoException;
	
	public int update(PowerCyclerDo powerCyclerDo) throws DaoException;
	
	public int delete(int id) throws DaoException;
	
	public List<PowerCyclerDo> loadAllPowerCyclers() throws DaoException;
	
	public PowerCyclerDo findPowerCyclerById(int id) throws DaoException;
	
	public PowerCyclerDo findPowerCyclerByName(String name) throws DaoException;
	
	public List<PowerCyclerQuery> loadPowerCyclersbyQuery(PowerCyclerQuery query)throws DaoException;
	
	public int loadTotalNumByQuery(PowerCyclerQuery query) throws DaoException;
}
