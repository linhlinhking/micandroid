package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.IPowercyclerEquipmentDao;
import com.cisco.pmonitor.dao.dataobject.PowercyclerEquipmentDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.PowercyclerEquipmentQuery;
import com.cisco.pmonitor.service.IPowercyclerEquipmentService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class PowercyclerEquipmentServiceImpl implements
		IPowercyclerEquipmentService {

	private IPowercyclerEquipmentDao powercyclerEquipmentDao;
	@Override
	public Result<Integer> addPowercyclerEquipment(PowercyclerEquipmentDo powercyclerEquipmentDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == powercyclerEquipmentDo) {
			return rs;
		}
		try {
			int id = powercyclerEquipmentDao.insert(powercyclerEquipmentDo);
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
	public Result<Integer> updatePowercyclerEquipment(PowercyclerEquipmentDo powercyclerEquipmentDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == powercyclerEquipmentDo) {
			return rs;
		}
		try {
			int row = powercyclerEquipmentDao.update(powercyclerEquipmentDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> deletePowercyclerEquipment(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = powercyclerEquipmentDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<PowercyclerEquipmentDo> findPowercyclerEquipmentById(int id) throws ServiceException {
		Result<PowercyclerEquipmentDo> rs = new Result<PowercyclerEquipmentDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			PowercyclerEquipmentDo powercyclerEquipmentDo = powercyclerEquipmentDao.findPowercyclerEquipmentById(id);
			if(null != powercyclerEquipmentDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(powercyclerEquipmentDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	public void setPowercyclerEquipmentDao(IPowercyclerEquipmentDao powercyclerEquipmentDao) {
		this.powercyclerEquipmentDao = powercyclerEquipmentDao;
	}

	@Override
	public Result<Map<String, Object>> loadPowercyclerEquipmentsByQuery(PowercyclerEquipmentQuery query)
			throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		rs.setSuccess(false);
		if(null == query) {
			return rs;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<PowercyclerEquipmentQuery> list = powercyclerEquipmentDao.loadPowercyclerEquipmentsbyQuery(query);
			int total = powercyclerEquipmentDao.loadTotalNumByQuery(query);
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<PowercyclerEquipmentDo>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<List<PowercyclerEquipmentDo>> loadAllPowercyclerEquipments() throws ServiceException {
		Result<List<PowercyclerEquipmentDo>> rs = new Result<List<PowercyclerEquipmentDo>>();
		rs.setSuccess(false);
		try {
			List<PowercyclerEquipmentDo> list = powercyclerEquipmentDao.loadAllPowercyclerEquipments();
			if(null != list) {
				rs.setSuccess(true);
				rs.setDefaultModel(list);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<PowercyclerEquipmentDo> findPowercyclerAndOutlet(
			int powercyclerId, int outlet) throws ServiceException {
		Result<PowercyclerEquipmentDo> rs = new Result<PowercyclerEquipmentDo>();
		rs.setSuccess(false);
		if(0 == powercyclerId) {
			return rs;
		}
		try {
			PowercyclerEquipmentDo powercyclerEquipmentDo = powercyclerEquipmentDao.findPowercyclerAndOutlet(powercyclerId, outlet);
			if(null != powercyclerEquipmentDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(powercyclerEquipmentDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}
}
