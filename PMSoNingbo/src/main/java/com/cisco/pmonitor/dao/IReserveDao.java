package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.ReserveDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.ReserveQuery;

public interface IReserveDao {

	public int insert(ReserveDo reserveDo) throws DaoException;
	
	public int update(ReserveDo reserveDo) throws DaoException;
	
	public int delete(int id) throws DaoException;
	
	public List<ReserveQuery> loadAllReserves() throws DaoException;
	
	public List<ReserveQuery> loadReservesByQuery(ReserveQuery query) throws DaoException;
	public int loadTotalNumByQuery(ReserveQuery query) throws DaoException;
	public ReserveDo findReserveById(int id) throws DaoException;
	public ReserveDo findReserveByEquipmentId(int equipmentId, int status) throws DaoException;
	public int loadTotalNum() throws DaoException;
	
}
