package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IDepartmentDao;
import com.cisco.pmonitor.dao.dataobject.DepartmentDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.DepartmentQuery;
import com.cisco.pmonitor.service.IDepartmentService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class DepartmentServiceImpl implements IDepartmentService {

	private IDepartmentDao departmentDao;
	@Override
	public Result<List<DepartmentDo>> loadAllDepartments()
			throws ServiceException {
		Result<List<DepartmentDo>> rs = new Result<List<DepartmentDo>>();
		rs.setSuccess(false);
		try {
			List<DepartmentDo> list = departmentDao.loadAllDepartments();
			rs.setSuccess(true);
			rs.setDefaultModel(list);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public Result<Integer> addDepartment(DepartmentDo departmentDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == departmentDo) {
			return rs;
		}
		try {
			int id = departmentDao.insert(departmentDo);
			if(id > 0) {
				rs.setSuccess(true);
				rs.setDefaultModel(id);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> updateDepartment(DepartmentDo departmentDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == departmentDo) {
			return rs;
		}
		try {
			int row = departmentDao.update(departmentDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> deleteDepartment(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = departmentDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<DepartmentDo> findDepartmentById(int id) throws ServiceException {
		Result<DepartmentDo> rs = new Result<DepartmentDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			DepartmentDo departmentDo = departmentDao.findDepartmentById(id);
			if(null != departmentDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(departmentDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<DepartmentDo> findDepartmentByName(String name) throws ServiceException {
		Result<DepartmentDo> rs = new Result<DepartmentDo>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(name)) {
			return rs;
		}
		try {
			DepartmentDo departmentDo = departmentDao.findDepartmentByName(name);
			if(null != departmentDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(departmentDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Map<String, Object>> loadDepartmentsByQuery(DepartmentQuery query)
			throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		rs.setSuccess(false);
		if(null == query) {
			return rs;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<DepartmentQuery> list = departmentDao.loadDepartmentsbyQuery(query);
			int total = departmentDao.loadTotalNumByQuery(query);
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<DepartmentDo>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

}
