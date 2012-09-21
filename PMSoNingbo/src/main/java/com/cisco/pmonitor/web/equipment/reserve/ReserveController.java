package com.cisco.pmonitor.web.equipment.reserve;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.pmonitor.dao.dataobject.EquipmentDo;
import com.cisco.pmonitor.dao.dataobject.ReserveDo;
import com.cisco.pmonitor.dao.query.ReserveQuery;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.dao.utils.DateUtils;
import com.cisco.pmonitor.service.IEquipmentService;
import com.cisco.pmonitor.service.IReserveService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

	protected final Logger logger = Logger.getLogger(ReserveController.class);
	private IReserveService reserveService;
	private IEquipmentService equipmentService;
	
	@RequestMapping(value = "/query_list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(ReserveQuery query) {
		Result<Map<String, Object>> rs;
		try {
			query.setSort("START_TIME");
			query.setOrder("asc");
			rs = reserveService.loadReservesByQuery(query);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("ReserveController.queryList : ", e);
		}
		return null;
	}
	public void setReserveService(IReserveService reserveService) {
		this.reserveService = reserveService;
	}
	
	@RequestMapping(value = "/reserve_del", method = RequestMethod.POST)
	public String doDel(@RequestParam int id, Model model) {
		Result<Integer> rs;
		try {
			Result<ReserveDo> result = reserveService.findReserveById(id);
			if(result.isSuccess()) {
				ReserveDo reserveDo = result.getDefaultModel();
				if(reserveDo.getStatus() == Constants.RESERVED_STATUS) {
					EquipmentDo equipment = equipmentService.findEquipmentById(reserveDo.getEquipmentId()).getDefaultModel();
					equipment.setStatus(Constants.IDLE_EQUIPMENT_STATUS);
					equipmentService.updateEquipment(equipment);
				}
			}
			rs = reserveService.deleteReserve(id);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The delete operation is success!");
				return "msg";
			}
		} catch (ServiceException e) {
			logger.error("ReserveController.doDel : ", e);
		}
		model.addAttribute("msg", "The delete operation is error!");
		return "msg";
	}
	
	@RequestMapping(value = "equipment_reserve", method = RequestMethod.POST)
	public String doReserve(ReserveDo reserveDo, Model model) {
		String startTime = DateUtils.convertStr2Str(reserveDo.getStartTime(), Constants.EASYUI_DATE_FORMAT, Constants.PM_DATE_FORMAT);
		String endTime = DateUtils.convertStr2Str(reserveDo.getEndTime(), Constants.EASYUI_DATE_FORMAT, Constants.PM_DATE_FORMAT);
		if(startTime.compareTo(endTime) >= 0) {
			model.addAttribute("msg", "The start time is greater than end time!");
			return "msg";
		}
		reserveDo.setStartTime(startTime);
		reserveDo.setEndTime(endTime);
		int status = -2;
		Result<Map<String, Object>> rs;
		Result<Integer> result;
		ReserveQuery query = new ReserveQuery();
		query.setEquipmentId(reserveDo.getEquipmentId());
		query.setSort("START_TIME");
		query.setOrder("asc");
		try {
			rs = reserveService.loadReservesByQuery(query);
			if(rs.isSuccess()) {
				@SuppressWarnings("unchecked")
				List<ReserveQuery> list = (List<ReserveQuery>) rs.getDefaultModel().get("rows");
				//if no history in database, it must be reserved status.
				if(null == list || list.size() == 0) {
					status = Constants.RESERVED_STATUS;
					//update the equipment status
					EquipmentDo equipment = equipmentService.findEquipmentById(reserveDo.getEquipmentId()).getDefaultModel();
					equipment.setStatus(Constants.RESERVE_EQUIPMENT_STATUS);
					equipmentService.updateEquipment(equipment);
				}
				else {
					//We should make a schedule to update the reservation status, once a day, maybe.
					for(int i = 0; i < list.size(); i ++) {
						ReserveQuery reserve = list.get(i);
						if(reserve.getStartTime().equalsIgnoreCase(startTime)) {
							model.addAttribute("msg", "The start time of reservation is already existed!");
							return "msg";
						}
						if(reserve.getEndTime().equalsIgnoreCase(endTime)) {
							model.addAttribute("msg", "The end time of reservation is already existed!");
							return "msg";
						}
						if(reserve.getStatus() == Constants.RESERVED_STATUS) {
							if(i < list.size() - 1) {
								if(startTime.compareTo(reserve.getEndTime()) > 0) {
									for(int j = i + 1; j < list.size(); j ++) {
										if(startTime.compareTo(list.get(j).getStartTime()) > 0 && startTime.compareTo(list.get(j).getEndTime()) < 0) {
											model.addAttribute("msg", "The start time of reservation is invalid!");
											return "msg";
										}
										if(endTime.compareTo(list.get(j).getStartTime()) > 0 && endTime.compareTo(list.get(j).getEndTime()) < 0) {
											model.addAttribute("msg", "The end time of reservation is invalid!");
											return "msg";
										}
									}
								}
							}
							
							if(reserve.getStartTime().compareTo(startTime) >= 0 || startTime.compareTo(reserve.getEndTime()) <= 0) {
								model.addAttribute("msg", "The start time of reservation is less than existed start date!");
								return "msg";
							}
							if(reserve.getEndTime().compareTo(endTime) >= 0 || endTime.compareTo(reserve.getStartTime()) <= 0) {
								model.addAttribute("msg", "The end time of reservation is less than existed end date!");
								return "msg";
							}
						}
					}
					status = Constants.PENDING_STATUS;
				}
				reserveDo.setStatus(status);
				result = reserveService.addReserve(reserveDo);
				if(result.isSuccess()) {
					model.addAttribute("msg", "The reserve operation is success!");
					return "msg";
				}
				else {
					model.addAttribute("msg", "The reserve operation is failed!");
					return "msg";
				}
			}
			else {
				model.addAttribute("msg", "The system is error....");
			}
		} catch (ServiceException e) {
			model.addAttribute("msg", "The system is error....");
			logger.error("ReserveController.doReserve : ", e);
		}
		return "msg";
	}
	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
}
