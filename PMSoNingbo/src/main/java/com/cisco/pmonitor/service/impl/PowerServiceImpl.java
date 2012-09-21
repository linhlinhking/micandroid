package com.cisco.pmonitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.dataobject.PowercyclerEquipmentDo;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.service.IPowerCommand;
import com.cisco.pmonitor.service.IPowerCyclerService;
import com.cisco.pmonitor.service.IPowerService;
import com.cisco.pmonitor.service.IPowercyclerEquipmentService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

public class PowerServiceImpl implements IPowerService{
	

	private Map<PowerCyclerDo, List<Integer>> pcMap = new HashMap<PowerCyclerDo, List<Integer>>();
	private IPowercyclerEquipmentService powercyclerEquipmentService;
	private IPowerCyclerService powerCyclerService;
	private IPowerCommand snmpPowerCommand;
	private IPowerCommand telnetPowerCommand;
	private IPowerCommand socketPowerCommand;
	@Override
	public Result<Map<PowerCyclerDo, Map<Integer, Boolean>>> turnPowerOn(int[] ids) throws ServiceException {
		Result<Map<PowerCyclerDo, Map<Integer, Boolean>>> rs = new Result<Map<PowerCyclerDo, Map<Integer, Boolean>>>();
		Map<PowerCyclerDo, Map<Integer, Boolean>> map = new HashMap<PowerCyclerDo, Map<Integer, Boolean>>();
		rs.setSuccess(false);
		if(null == ids || ids.length == 0) {
			return rs;
		}
		makePcMap(ids);
		for(Iterator<PowerCyclerDo> it = pcMap.keySet().iterator();it.hasNext();) {
			PowerCyclerDo pco = it.next();
			List<Integer> outletList = pcMap.get(pco);
			Map<Integer, Boolean> outletMap = dispatchOperation(pco, outletList, pco.getOn());
			//if the operation is success, update the database
			for(Iterator<Integer> i = outletMap.keySet().iterator();i.hasNext();) {
				int outlet = i.next();
				boolean success =outletMap.get(outlet);
				if(success) {
					Result<PowercyclerEquipmentDo> rs1 = powercyclerEquipmentService.findPowercyclerAndOutlet(pco.getId(), outlet);
					if(rs1.isSuccess()) {
						PowercyclerEquipmentDo pco1 = rs1.getDefaultModel();
						if(pco1.getStatus() == Constants.POWER_OFF) {
							pco1.setStatus(Constants.POWER_ON);
							Result<Integer> r = powercyclerEquipmentService.updatePowercyclerEquipment(pco1);
							if(r.isSuccess()) {
								//left the failed operation in the map
								outletMap.remove(outlet);
							}
						}
					}
				}
			}
			map.put(pco, outletMap);
		}
		rs.setSuccess(true);
		rs.setDefaultModel(map);
		pcMap.clear();
		return rs;
	}

	@Override
	public Result<Map<PowerCyclerDo, Map<Integer, Boolean>>> turnPowerOff(int[] ids) throws ServiceException {
		Result<Map<PowerCyclerDo, Map<Integer, Boolean>>> rs = new Result<Map<PowerCyclerDo, Map<Integer, Boolean>>>();
		Map<PowerCyclerDo, Map<Integer, Boolean>> map = new HashMap<PowerCyclerDo, Map<Integer, Boolean>>();
		rs.setSuccess(false);
		if(null == ids || ids.length == 0) {
			return rs;
		}
		makePcMap(ids);
		for(Iterator<PowerCyclerDo> it = pcMap.keySet().iterator();it.hasNext();) {
			PowerCyclerDo pco = it.next();
			List<Integer> outletList = pcMap.get(pco);
			Map<Integer, Boolean> outletMap = dispatchOperation(pco, outletList, pco.getOff());
			//if the operation is success, update the database
			for(Iterator<Integer> i = outletMap.keySet().iterator();i.hasNext();) {
				int outlet = i.next();
				boolean success =outletMap.get(outlet);
				if(success) {
					Result<PowercyclerEquipmentDo> rs1 = powercyclerEquipmentService.findPowercyclerAndOutlet(pco.getId(), outlet);
					if(rs1.isSuccess()) {
						PowercyclerEquipmentDo pco1 = rs1.getDefaultModel();
						if(pco1.getStatus() == Constants.POWER_ON) {
							pco1.setStatus(Constants.POWER_OFF);
							Result<Integer> r = powercyclerEquipmentService.updatePowercyclerEquipment(pco1);
							if(r.isSuccess()) {
								//left the failed operation in the map
								outletMap.remove(outlet);
							}
						}
					}
				}
			}
			map.put(pco, outletMap);
		}
		rs.setSuccess(true);
		rs.setDefaultModel(map);
		pcMap.clear();
		return rs;
	}

	public void setPowercyclerEquipmentService(
			IPowercyclerEquipmentService powercyclerEquipmentService) {
		this.powercyclerEquipmentService = powercyclerEquipmentService;
	}

	public void setPowerCyclerService(IPowerCyclerService powerCyclerService) {
		this.powerCyclerService = powerCyclerService;
	}
	
	/**
	 * make the sname protocol powercycler to one map.
	 * @param ids
	 * @throws ServiceException
	 */
	private void makePcMap(int[] ids) throws ServiceException{
		for(int id : ids) {
			Result<PowercyclerEquipmentDo> result = powercyclerEquipmentService.findPowercyclerEquipmentById(id);
			if(result.isSuccess()) {
				PowercyclerEquipmentDo peo = result.getDefaultModel();
				Result<PowerCyclerDo> rpco = powerCyclerService.findPowerCyclerById(peo.getPowercyclerId());
				if(rpco.isSuccess()) {
					PowerCyclerDo pco = rpco.getDefaultModel();
					List<Integer> outletList = pcMap.get(pco);
					if(null == outletList) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(peo.getOutlet());
						pcMap.put(pco, list);
					}
					else {
						outletList.add(peo.getOutlet());
						pcMap.put(pco, outletList);
					}
				}
			}
		}
	}
	
	private Map<Integer, Boolean> dispatchOperation(PowerCyclerDo pco, List<Integer> outletList, int state) throws ServiceException{
		Map<Integer, Boolean> outletMap = new HashMap<Integer, Boolean>();
		for(int outlet : outletList) {
			outletMap.put(outlet, false);
		}
		int protocol = pco.getProtocol();
		switch(protocol) {
		
			case Constants.SNMP_PROTOCOL: {
				return snmpPowerCommand.execute(pco, outletMap, state);
			}
			case Constants.TELNET_PROTOCOL: {
				return telnetPowerCommand.execute(pco, outletMap, state);
			}
			case Constants.SOCKET_PROTOCOL: {
				return socketPowerCommand.execute(pco, outletMap, state);
			}
		}
		return outletMap;
	}

	public void setSnmpPowerCommand(IPowerCommand snmpPowerCommand) {
		this.snmpPowerCommand = snmpPowerCommand;
	}

	public void setTelnetPowerCommand(IPowerCommand telnetPowerCommand) {
		this.telnetPowerCommand = telnetPowerCommand;
	}

	public void setSocketPowerCommand(IPowerCommand socketPowerCommand) {
		this.socketPowerCommand = socketPowerCommand;
	}


}
