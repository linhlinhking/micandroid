package com.cisco.pmonitor.web.monitor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.pmonitor.dao.dataobject.PowerCyclerDo;
import com.cisco.pmonitor.dao.dataobject.PowercyclerEquipmentDo;
import com.cisco.pmonitor.dao.query.PowercyclerEquipmentQuery;
import com.cisco.pmonitor.service.IPowerService;
import com.cisco.pmonitor.service.IPowercyclerEquipmentService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;
import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
@RequestMapping("/monitor")
public class MonitorController {

	protected final Logger logger = Logger.getLogger(MonitorController.class);
	private IPowercyclerEquipmentService powercyclerEquipmentService;
	private IPowerService powerService;
	
	@RequestMapping(value = "/monitor_view", method = RequestMethod.GET)
	public String toMonitorView(HttpServletRequest request, Model model) {
		return SessionHandler.verifySession(request, "monitor/monitor_view");
	}
	
	@RequestMapping(value = "/query_list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doMonitorView(PowercyclerEquipmentQuery query) {
		Result<Map<String, Object>> rs;
		try {
			rs = powercyclerEquipmentService.loadPowercyclerEquipmentsByQuery(query);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("PowercyclerEquipmentController.doMonitorView : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/monitor_form", method = RequestMethod.POST)
	public String doForm(HttpServletRequest request, Model model) {
		String equipmentId = request.getParameter("equipmentId");
		String owner = request.getParameter("owner");
		String status = request.getParameter("status");
		if(StringUtils.isEmpty(equipmentId)) {
			equipmentId = "";
		}
		if(StringUtils.isEmpty(owner)) {
			owner = "";
		}
		if(StringUtils.isEmpty(status)) {
			status = "-1";
		}
		String linkUrl = "monitor/query_list?equipmentId=" + equipmentId + 
						 "&owner=" + owner + 
						 "&status=" + status;
		model.addAttribute("linkUrl", linkUrl);
		return "linkurl";
	}

	public void setPowercyclerEquipmentService(
			IPowercyclerEquipmentService powercyclerEquipmentService) {
		this.powercyclerEquipmentService = powercyclerEquipmentService;
	}
	
	@RequestMapping(value = "monitor_monitor", method = RequestMethod.GET)
	public String toMonitor(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "monitor/monitor_monitor");
	}
	
	@RequestMapping(value = "monitor_monitor", method = RequestMethod.POST)
	public String doMonitor(PowercyclerEquipmentDo powercyclerEquipmentDo, HttpServletRequest request, Model model) {
		Result<Integer> rs;
		try {
			rs = powercyclerEquipmentService.addPowercyclerEquipment(powercyclerEquipmentDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The powercycler monitor operation is success.");
			}
			else {
				model.addAttribute("msg", "The powercycler monitor operation is error.");
			}
		} catch (ServiceException e) {
			model.addAttribute("msg", "The powercycler monitor operation is error.");
			logger.error("PowercyclerEquipmentController.doMonitor : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "monitor_release", method = RequestMethod.POST)
	public String doRelease(@RequestParam int id, Model model) {
		Result<Integer> rs;
		try {
			rs = powercyclerEquipmentService.deletePowercyclerEquipment(id);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The powercycler release operation is success.");
			}
			else {
				model.addAttribute("msg", "The powercycler release operation is error.");
			}
		} catch (ServiceException e) {
			model.addAttribute("msg", "The powercycler release operation is error.");
			logger.error("PowercyclerEquipmentController.doRelease : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/monitor_poweron", method = RequestMethod.POST)
	public String turnOn(@RequestParam String id, Model model) {
		String[] strId = id.split(",");
		if(null == strId || strId.length == 0) {
			model.addAttribute("msg", "The powercycler turnOn operation is failed.");
			return "msg";
		}
		int[] ids = new int[strId.length];
		for(int i = 0; i < strId.length; i ++) {
			ids[i] = Integer.parseInt(strId[i]);
		}
		Result<Map<PowerCyclerDo, Map<Integer, Boolean>>> rs;
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		try {
			rs = powerService.turnPowerOn(ids);
			if(rs.isSuccess()) {
				Map<PowerCyclerDo, Map<Integer, Boolean>> map = rs.getDefaultModel();
				for(Iterator<PowerCyclerDo> it = map.keySet().iterator(); it.hasNext();) {
					PowerCyclerDo pco = it.next();
					sb.append("{" + pco.getName() + "'s outlet [");
					Map<Integer, Boolean> outletMap = map.get(pco);
					for(Iterator<Integer> outlet = outletMap.keySet().iterator(); outlet.hasNext();) {
						int ol = outlet.next();
						if(!outletMap.get(ol)) {
							counter ++;
							sb.append(ol + ",");
						}
					}
					sb.append("]}");
				}
			}
			if(counter > 0) {
				model.addAttribute("msg", sb.toString() + " turnOn operation is failed.");
			}
			else {
				model.addAttribute("msg", "The powercycler turnOn operation is success.");
			}
		} catch (ServiceException e) {
			model.addAttribute("msg", "The powercycler turnOn operation is failed.");
			logger.error("PowercyclerEquipmentController.turnOn : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/monitor_poweroff", method = RequestMethod.POST)
	public String turnOff(@RequestParam String id, Model model) {
		String[] strId = id.split(",");
		if(null == strId || strId.length == 0) {
			model.addAttribute("msg", "The powercycler turnOn operation is failed.");
			return "msg";
		}
		int[] ids = new int[strId.length];
		for(int i = 0; i < strId.length; i ++) {
			ids[i] = Integer.parseInt(strId[i]);
		}
		Result<Map<PowerCyclerDo, Map<Integer, Boolean>>> rs;
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		try {
			rs = powerService.turnPowerOff(ids);
			if(rs.isSuccess()) {
				Map<PowerCyclerDo, Map<Integer, Boolean>> map = rs.getDefaultModel();
				for(Iterator<PowerCyclerDo> it = map.keySet().iterator(); it.hasNext();) {
					PowerCyclerDo pco = it.next();
					sb.append("{" + pco.getName() + "'s outlet [");
					Map<Integer, Boolean> outletMap = map.get(pco);
					for(Iterator<Integer> outlet = outletMap.keySet().iterator(); outlet.hasNext();) {
						int ol = outlet.next();
						if(!outletMap.get(ol)) {
							counter ++;
							sb.append(ol + ",");
						}
					}
					sb.append("]}");
				}
			}
			if(counter > 0) {
				model.addAttribute("msg", sb.toString() + " turnOff operation is failed.");
			}
			else {
				model.addAttribute("msg", "The powercycler turnOff operation is success.");
			}
		} catch (ServiceException e) {
			model.addAttribute("msg", "The powercycler turnOff operation is failed.");
			logger.error("PowercyclerEquipmentController.turnOff : ", e);
		}
		return "msg";
	}

	public void setPowerService(IPowerService powerService) {
		this.powerService = powerService;
	}
	
}
