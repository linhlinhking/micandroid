package com.cisco.pmonitor.dao;

import java.util.List;

import com.cisco.pmonitor.dao.dataobject.DepartmentDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.DepartmentQuery;

public interface IDepartmentDao {

	public int insert(DepartmentDo departmentDo) throws DaoException;
	
	public int update(DepartmentDo departmentDo) throws DaoException;
	
	public int delete(int id) throws DaoException;
	
	public List<DepartmentDo> loadAllDepartments() throws DaoException;
	
	public DepartmentDo findDepartmentById(int id) throws DaoException;
	
	public DepartmentDo findDepartmentByName(String name) throws DaoException;
	
	public List<DepartmentQuery> loadDepartmentsbyQuery(DepartmentQuery query)
												throws DaoException;
	public int loadTotalNumByQuery(DepartmentQuery query) throws DaoException;
}
