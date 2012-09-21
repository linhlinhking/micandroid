package com.cisco.pmonitor.service.impl;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.service.IPowerCommand;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Telnet;

public class TelnetPowerCommandImpl implements IPowerCommand {

	@Override
	public Map<Integer, Boolean> execute(PowerCyclerDo pco,
			Map<Integer, Boolean> outletMap, int state) throws ServiceException {
		String ip = pco.getHost();
		String loginUser = pco.getLoginUser();
		String loginPwd = pco.getLoginPwd();
		String enablepwd = pco.getEnablePwd();
		if(StringUtils.isEmpty(ip)) {
			throw new ServiceException("The TelnetPowerCommandImpl.execute parameter is error...");
		}
		
		Telnet telnet = new Telnet(ip);
		telnet.init();
		telnet.receive();
		boolean power = false;
		if(state == pco.getOn()) {
			power = true;
		}
		int type = pco.getType();
		switch(type) {
		case Constants.DTR_POWERCYCLER: {
			return executeDTR(telnet, loginUser, loginPwd, enablepwd, outletMap, power);
		}
		case Constants.UBR10KLC_POWERCYCLER: {
			return executeUBR10KLC(telnet, loginUser, loginPwd, enablepwd, outletMap, power);
		}
		}
		return outletMap;
	}
	
	private Map<Integer, Boolean> executeDTR(Telnet telnet, String loginUser, String loginPwd, String enablepwd, Map<Integer, Boolean> outletMap, boolean power) {
		if(StringUtils.isNotBlank(loginUser)) {
			if(telnet.finishCommand("Username")) {
				telnet.send(loginUser);
			}
			else {
				return outletMap;
			}
		}
		if(StringUtils.isNotBlank(loginPwd)) {
			if(telnet.finishCommand("Password")) {
				telnet.send(loginPwd);
			}
			else {
				return outletMap;
			}
		}
		if(StringUtils.isNotBlank(enablepwd)) {
			if(telnet.finishCommand(">")) {
				telnet.send("enable");
			}
			else {
				return outletMap;
			}
			if(telnet.finishCommand("Password")) {
				telnet.send(enablepwd);
			}
			else {
				return outletMap;
			}
		}
		else {
			if(telnet.finishCommand(">")) {
				telnet.send("enable");
			}
			else {
				return outletMap;
			}
		}
		telnet.send("");
		if(telnet.finishCommand("#")){
			telnet.send("conf t");
		}
		else {
			return outletMap;
		}
		for(final Iterator<Integer> it = outletMap.keySet().iterator();it.hasNext();) {
			int line = it.next();
			telnet.send("line " + line);
			if(telnet.finishCommand("(config-line)#")) {
				if(power) {
					telnet.send("no modem dtr-active");
				}
				else {
					telnet.send("modem dtr-active");
				}
				//the operation is success and then to update outlet map
				if(telnet.finishCommand("(config-line)#")){
					outletMap.put(line, true);
				}
			}
		}
		telnet.send("end");
		telnet.send("exit");
		return outletMap;
	}
	
	private Map<Integer, Boolean> executeUBR10KLC(Telnet telnet, String loginUser, String loginPwd, String enablepwd, Map<Integer, Boolean> outletMap, boolean power) {
		if(StringUtils.isNotBlank(loginUser)) {
			if(telnet.finishCommand("Username")) {
				telnet.send(loginUser);
			}
			else {
				return outletMap;
			}
		}
		if(StringUtils.isNotBlank(loginPwd)) {
			if(telnet.finishCommand("Password")) {
				telnet.send(loginPwd);
			}
			else {
				return outletMap;
			}
		}
		if(StringUtils.isNotBlank(enablepwd)) {
			if(telnet.finishCommand(">")) {
				telnet.send("enable");
			}
			else {
				return outletMap;
			}
			if(telnet.finishCommand("Password")) {
				telnet.send(enablepwd);
			}
			else {
				return outletMap;
			}
		}
		else {
			if(telnet.finishCommand(">")) {
				telnet.send("enable");
			}
			else {
				return outletMap;
			}
		}
		telnet.send("");
		if(power) {
			telnet.send("cable power on 5/0");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power on 5/1");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power on 6/0");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power on 6/1");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power on 7/0");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power on 7/1");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power on 8/0");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power on 8/1");
			if(!telnet.finishCommand("POWERED ON")) {
				return outletMap;
			}
			for(final Iterator<Integer> it = outletMap.keySet().iterator();it.hasNext();) {
				int line = it.next();
				outletMap.put(line, true);
			}
		}
		else {
			telnet.send("cable power off 5/0");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power off 5/1");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power off 6/0");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power off 6/1");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power off 7/0");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power off 7/1");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power off 8/0");
			if(!telnet.finishCommand("#")) {
				return outletMap;
			}
			telnet.send("cable power off 8/1");
			if(!telnet.finishCommand("POWERED OFF")) {
				return outletMap;
			}
			for(final Iterator<Integer> it = outletMap.keySet().iterator();it.hasNext();) {
				int line = it.next();
				outletMap.put(line, true);
			}
		}
		telnet.send("exit");
		return outletMap;
	}

}
