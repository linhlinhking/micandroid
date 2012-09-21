package com.cisco.pmonitor.dao;

import com.cisco.pmonitor.dao.dataobject.UseRatioDo;
import com.cisco.pmonitor.dao.expection.DaoException;

public interface IUseRatioDao {

	
	public int insert(UseRatioDo useRatioDo) throws DaoException;
	
	public int update(UseRatioDo useRatioDo) throws DaoException;
}
