package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.IEquipmentDao;
import com.cisco.pmonitor.dao.IUseRatioDao;
import com.cisco.pmonitor.dao.dataobject.EquipmentDo;
import com.cisco.pmonitor.dao.dataobject.UseRatioDo;
import com.cisco.pmonitor.dao.expection.DaoException;
import com.cisco.pmonitor.dao.query.EquipmentQuery;
import com.cisco.pmonitor.service.IEquipmentService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class EquipmentServiceImpl implements IEquipmentService {

	private IEquipmentDao equipmentDao;
	private IUseRatioDao useRatioDao;
	@Override
	public Result<Integer> addEquipment(EquipmentDo equipmentDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == equipmentDo) {
			return rs;
		}
		try {
			int id = equipmentDao.insert(equipmentDo);
			UseRatioDo useRatioDo = new UseRatioDo();
			useRatioDo.setEquipmentId(id);
			int id2 = useRatioDao.insert(useRatioDo);
			if(id > 0 && id2 > 0) {
				rs.setSuccess(true);
				rs.setDefaultModel(id);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> updateEquipment(EquipmentDo equipmentDo) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(null == equipmentDo) {
			return rs;
		}
		try {
			int row = equipmentDao.update(equipmentDo);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<Integer> deleteEquipment(int id) throws ServiceException {
		Result<Integer> rs = new Result<Integer>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			int row = equipmentDao.delete(id);
			if(row == 1) {
				rs.setSuccess(true);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<EquipmentDo> findEquipmentById(int id) throws ServiceException {
		Result<EquipmentDo> rs = new Result<EquipmentDo>();
		rs.setSuccess(false);
		if(0 == id) {
			return rs;
		}
		try {
			EquipmentDo equipmentDo = equipmentDao.findEquipmentById(id);
			if(null != equipmentDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(equipmentDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<EquipmentDo> findEquipmentByName(String name) throws ServiceException {
		Result<EquipmentDo> rs = new Result<EquipmentDo>();
		rs.setSuccess(false);
		if(StringUtils.isEmpty(name)) {
			return rs;
		}
		try {
			EquipmentDo equipmentDo = equipmentDao.findEquipmentByName(name);
			if(null != equipmentDo) {
				rs.setSuccess(true);
				rs.setDefaultModel(equipmentDo);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	public void setEquipmentDao(IEquipmentDao equipmentDao) {
		this.equipmentDao = equipmentDao;
	}

	@Override
	public Result<Map<String, Object>> loadEquipmentsByQuery(EquipmentQuery query)
			throws ServiceException {
		Result<Map<String, Object>> rs = new Result<Map<String, Object>>();
		rs.setSuccess(false);
		if(null == query) {
			return rs;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<EquipmentQuery> list = equipmentDao.loadEquipmentsbyQuery(query);
			int total = equipmentDao.loadTotalNumByQuery(query);
			map.put("total", total);
			map.put("rows", list == null ? new ArrayList<EquipmentDo>() : list);
			rs.setSuccess(true);
			rs.setDefaultModel(map);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	@Override
	public Result<List<EquipmentDo>> loadAllEquipments() throws ServiceException {
		Result<List<EquipmentDo>> rs = new Result<List<EquipmentDo>>();
		rs.setSuccess(false);
		try {
			List<EquipmentDo> list = equipmentDao.loadAllEquipments();
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
	public Result<List<EquipmentDo>> loadEquipmentsByGroupId(int groupId)
			throws ServiceException {
		Result<List<EquipmentDo>> rs = new Result<List<EquipmentDo>>();
		rs.setSuccess(false);
		if(0 == groupId) {
			return rs;
		}
		try {
			List<EquipmentDo> list = equipmentDao.loadEquipmentsByGroupId(groupId);
			if(null != list) {
				rs.setSuccess(true);
				rs.setDefaultModel(list);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return rs;
	}

	public void setUseRatioDao(IUseRatioDao useRatioDao) {
		this.useRatioDao = useRatioDao;
	}

}
