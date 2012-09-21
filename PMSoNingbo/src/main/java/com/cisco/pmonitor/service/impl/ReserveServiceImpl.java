package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.IReserveDao;
import com.cisco.pmonitor.dao.dataobject.ReserveDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.ReserveQuery;
import com.cisco.pmonitor.service.IReserveService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class ReserveServiceImpl implements IReserveService {

	private IReserveDao reserveDao;
	@Override
	public Result<Integer> addReserve(ReserveDo reserveDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == reserveDo) {
			return rs;
		}
		try {
			int id = reserveDao.insert(reserveDo);
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
	public Result<Integer> updateReserve(ReserveDo reserveDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == reserveDo) {
			return rs;
		}
		try {
			int row = reserveDao.update(reserveDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> deleteReserve(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = reserveDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<ReserveDo> findReserveById(int id) throws ServiceException {
		Result<ReserveDo> rs = new Result<ReserveDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			ReserveDo reserveDo = reserveDao.findReserveById(id);
			if(null != reserveDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(reserveDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	public void setReserveDao(IReserveDao reserveDao) {
		this.reserveDao = reserveDao;
	}

	@Override
	public Result<ReserveDo> findReserveByEquipmentId(int equipmentId, int status)
			throws ServiceException {
		Result<ReserveDo> rs = new Result<ReserveDo>();
		rs.setSuccess(false);
		if(0 == equipmentId) {
			return rs;
		}
		try {
			ReserveDo reserveDo = reserveDao.findReserveByEquipmentId(equipmentId, status);
			if(null != reserveDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(reserveDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Map<String, Object>> loadAllReserves() throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		rs.setSuccess(false);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<ReserveQuery> list = reserveDao.loadAllReserves();
			int total = reserveDao.loadTotalNum();
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<ReserveQuery>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Map<String, Object>> loadReservesByQuery(ReserveQuery query)
			throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		rs.setSuccess(false);
		if(null == query) {
			return rs;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<ReserveQuery> list = reserveDao.loadReservesByQuery(query);
			int total = reserveDao.loadTotalNumByQuery(query);
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<ReserveQuery>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}
}
