package com.cisco.pmonitor.service;

import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.ReserveDo;
import com.cisco.pmonitor.dao.query.ReserveQuery;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public interface IReserveService {

	public Result<Integer> addReserve(ReserveDo reserveDo) throws ServiceException;
	
	public Result<Integer> updateReserve(ReserveDo reserveDo) throws ServiceException;
	
	public Result<Integer> deleteReserve(int id) throws ServiceException;
	
	public Result<ReserveDo> findReserveById(int id) throws ServiceException;
	public Result<ReserveDo> findReserveByEquipmentId(int equipmentId, int status) throws ServiceException;
	
	public Result<Map<String, Object>> loadAllReserves() throws ServiceException;
	public Result<Map<String, Object>> loadReservesByQuery(ReserveQuery query) throws ServiceException;
}
