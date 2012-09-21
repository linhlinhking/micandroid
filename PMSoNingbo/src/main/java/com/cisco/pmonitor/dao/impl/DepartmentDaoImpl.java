package com.cisco.pmonitor.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IDepartmentDao;
import com.cisco.pmonitor.dao.dataobject.DepartmentDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.DepartmentQuery;
import com.ibatis.sqlmap.client.SqlMapClient;

public class DepartmentDaoImpl implements IDepartmentDao {

	private SqlMapClient sqlMapClient;
	@Override
	public int insert(DepartmentDo departmentDo) throws DaoException {
		int row = 0;
		if(null == departmentDo) {
			return row;
		}
		try {
			row = (Integer) sqlMapClient.insert("DepartmentDaoImpl.insert", departmentDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int update(DepartmentDo departmentDo) throws DaoException {
		int row = 0;
		if(null == departmentDo) {
			return row;
		}
		try {
			row = sqlMapClient.update("DepartmentDaoImpl.update", departmentDo);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@Override
	public int delete(int id) throws DaoException {
		int row = 0;
		if(0 == id) {
			return 0;
		}
		try {
			row = sqlMapClient.delete("DepartmentDaoImpl.delete", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return row;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentDo> loadAllDepartments() throws DaoException {
		try {
			return sqlMapClient.queryForList("DepartmentDaoImpl.loadAllDepartments");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public DepartmentDo findDepartmentById(int id) throws DaoException {
		if(0 == id) {
			return null;
		}
		try {
			return (DepartmentDo) sqlMapClient.queryForObject("DepartmentDaoImpl.findDepartmentById", id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public DepartmentDo findDepartmentByName(String name) throws DaoException {
		if(StringUtils.isEmpty(name)) {
			return null;
		}
		try {
			return (DepartmentDo) sqlMapClient.queryForObject("DepartmentDaoImpl.findDepartmentByName", name);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentQuery> loadDepartmentsbyQuery(DepartmentQuery query)
			throws DaoException {
		if(null == query) {
			return null;
		}
		try {
			return sqlMapClient.queryForList("DepartmentDaoImpl.loadDepartmentsbyQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int loadTotalNumByQuery(DepartmentQuery query) throws DaoException {
		if(null == query) {
			return 0;
		}
		try {
			return (Integer) sqlMapClient.queryForObject("DepartmentDaoImpl.loadTotalNumByQuery", query);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
