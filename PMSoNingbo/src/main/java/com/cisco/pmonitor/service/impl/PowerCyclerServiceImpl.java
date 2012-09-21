package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IPowerCyclerDao;
import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.PowerCyclerQuery;
import com.cisco.pmonitor.service.IPowerCyclerService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class PowerCyclerServiceImpl implements IPowerCyclerService {

	private IPowerCyclerDao powerCyclerDao;
	@Override
	public Result<Integer> addPowerCycler(PowerCyclerDo powerCyclerDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == powerCyclerDo) {
			return rs;
		}
		try {
			int id = powerCyclerDao.insert(powerCyclerDo);
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
	public Result<Integer> updatePowerCycler(PowerCyclerDo powerCyclerDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == powerCyclerDo) {
			return rs;
		}
		try {
			int row = powerCyclerDao.update(powerCyclerDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> deletePowerCycler(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = powerCyclerDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<PowerCyclerDo> findPowerCyclerById(int id) throws ServiceException {
		Result<PowerCyclerDo> rs = new Result<PowerCyclerDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			PowerCyclerDo powerCyclerDo = powerCyclerDao.findPowerCyclerById(id);
			if(null != powerCyclerDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(powerCyclerDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<PowerCyclerDo> findPowerCyclerByName(String name) throws ServiceException {
		Result<PowerCyclerDo> rs = new Result<PowerCyclerDo>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(name)) {
			return rs;
		}
		try {
			PowerCyclerDo powerCyclerDo = powerCyclerDao.findPowerCyclerByName(name);
			if(null != powerCyclerDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(powerCyclerDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	public void setPowerCyclerDao(IPowerCyclerDao powerCyclerDao) {
		this.powerCyclerDao = powerCyclerDao;
	}

	@Override
	public Result<Map<String, Object>> loadPowerCyclersByQuery(PowerCyclerQuery query)
			throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		rs.setSuccess(false);
		if(null == query) {
			return rs;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<PowerCyclerQuery> list = powerCyclerDao.loadPowerCyclersbyQuery(query);
			int total = powerCyclerDao.loadTotalNumByQuery(query);
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<PowerCyclerDo>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<List<PowerCyclerDo>> loadAllPowerCyclers() throws ServiceException {
		Result<List<PowerCyclerDo>> rs = new Result<List<PowerCyclerDo>>();
		rs.setSuccess(false);
		try {
			List<PowerCyclerDo> list = powerCyclerDao.loadAllPowerCyclers();
			if(null != list) {
				rs.setSuccess(true);
				rs.setDefaultModel(list);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

}
