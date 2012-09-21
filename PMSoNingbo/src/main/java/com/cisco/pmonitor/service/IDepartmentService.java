package com.cisco.pmonitor.service;

import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.DepartmentDo;
import com.cisco.pmonitor.dao.query.DepartmentQuery;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IDepartmentService {

	
	public Result<List<DepartmentDo>> loadAllDepartments() throws ServiceException;
	public Result<Integer> addDepartment(DepartmentDo departmentDo) throws ServiceException;
	
	public Result<Integer> updateDepartment(DepartmentDo departmentDo) throws ServiceException;
	
	public Result<Integer> deleteDepartment(int id) throws ServiceException;
	
	public Result<DepartmentDo> findDepartmentById(int id) throws ServiceException;
	
	public Result<DepartmentDo> findDepartmentByName(String name) throws ServiceException;
	
	public Result<Map<String, Object>> loadDepartmentsByQuery(DepartmentQuery query) throws ServiceException;
}
