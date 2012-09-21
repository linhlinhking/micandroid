package com.cisco.pmonitor.service.impl;

import java.util.Map;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.service.IPowerCommand;
import com.cisco.pmonitor.service.exception.ServiceException;

public class SocketPowerCommandImpl implements IPowerCommand {

	@Override
	public Map<Integer, Boolean> execute(PowerCyclerDo pco,
			Map<Integer, Boolean> outletMap, int state) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
