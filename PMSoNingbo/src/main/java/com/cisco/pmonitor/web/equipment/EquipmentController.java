package com.cisco.pmonitor.web.equipment;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.pmonitor.dao.dataobject.EquipmentDo;
import com.cisco.pmonitor.dao.dataobject.ReserveDo;
import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.query.EquipmentQuery;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.dao.utils.DateUtils;
import com.cisco.pmonitor.service.IDepartmentService;
import com.cisco.pmonitor.service.IEquipmentService;
import com.cisco.pmonitor.service.IGroupService;
import com.cisco.pmonitor.service.IReserveService;
import com.cisco.pmonitor.service.IRoomService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;
import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

	protected final Logger logger = Logger.getLogger(EquipmentController.class);
	
	private IEquipmentService equipmentService;
	private IGroupService groupService;
	private IRoomService roomService;
	private IDepartmentService departmentService;
	private IReserveService reserveService;
	
	@RequestMapping(value = "/equipment_view", method = RequestMethod.GET)
	public String toEquipmentView(HttpServletRequest request) {
		
		return SessionHandler.verifySession(request, "equipment/equipment_view");
	}
	
	@RequestMapping(value = "/query_list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEquipmentView(EquipmentQuery query) {
		Result<Map<String, Object>> rs;
		try {
			rs = equipmentService.loadEquipmentsByQuery(query);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.doEquipmentView : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/query_union", method = RequestMethod.POST)
	@ResponseBody
	public List<EquipmentDo> queryUnion(@RequestParam int groupId) {
		Result<List<EquipmentDo>> rs;
		try {
			rs = equipmentService.loadEquipmentsByGroupId(groupId);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.queryUnion : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/query_all", method = RequestMethod.POST)
	@ResponseBody
	public List<EquipmentDo> queryAll() {
		Result<List<EquipmentDo>> rs;
		try {
			rs = equipmentService.loadAllEquipments();
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.queryAll : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/equipment_form", method = RequestMethod.POST)
	public String doForm(EquipmentQuery query, Model model) {
		int departmentId = query.getDepartmentId() == null ? -1 : query.getDepartmentId();
		int roomId = query.getRoomId() == null ? -1 : query.getRoomId();
		int groupId = query.getGroupId() == null ? -1 : query.getGroupId();
		String linkUrl = "equipment/query_list?name=" + query.getName() + 
						 "&owner=" + query.getOwner() +
						 "&departmentId=" + departmentId+
						 "&roomId=" + roomId + 
						 "&groupId=" + groupId + 
						 "&status=" + query.getStatus();
		model.addAttribute("linkUrl", linkUrl);
		return "linkurl";
	}
	
	@RequestMapping(value = "/equipment_add", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "equipment/equipment_add");
	}
	
	@RequestMapping(value = "/equipment_add", method = RequestMethod.POST)
	public String doAdd(EquipmentDo equipmentDo, Model model) {
		Result<Integer> rs;
		try {
			rs = equipmentService.addEquipment(equipmentDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The add equipment operation is success!");
			}
			else {
				model.addAttribute("msg", "The add equipment operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.doAdd : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/equipment_del", method = RequestMethod.POST)
	public String doDel(@RequestParam int id, Model model) {
		Result<Integer> rs;
		try {
			rs = equipmentService.deleteEquipment(id);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The delete equipment operation is success!");
			}
			else {
				model.addAttribute("msg", "The delete equipment operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.doDel : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/equipment_edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam int id, HttpServletRequest request, Model model) {
		Result<EquipmentDo> rs;
		try {
			rs = equipmentService.findEquipmentById(id);
			if(rs.isSuccess()) {
				model.addAttribute("equipmentDo", rs.getDefaultModel());
				model.addAttribute("groupList", groupService.loadAllGroups().getDefaultModel());
				model.addAttribute("roomList", roomService.loadAllRooms().getDefaultModel());
				model.addAttribute("departmentList", departmentService.loadAllDepartments().getDefaultModel());
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.toEdit : ", e);
		}
		return SessionHandler.verifySession(request, "equipment/equipment_edit");
	}
	
	@RequestMapping(value = "/equipment_edit", method = RequestMethod.POST)
	public String doEdit(EquipmentDo equipmentDo, Model model) {
		Result<Integer> rs;
		try {
			rs = equipmentService.updateEquipment(equipmentDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The edit equipment operation is success!");
			}
			else {
				model.addAttribute("msg", "The edit equipment operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.doEdit : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/equipment_reserve", method = RequestMethod.GET)
	public String toReserve(@RequestParam int equipmentId, HttpServletRequest request, Model model) {
		Result<EquipmentDo> rs;
		try {
			rs = equipmentService.findEquipmentById(equipmentId);
			if(rs.isSuccess()) {
				model.addAttribute("equipmentDo", rs.getDefaultModel());
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.toEdit : ", e);
		}
		HttpSession session = request.getSession(false);
		if(null != session && null != session.getAttribute(Constants.PM_USER_SESSION)) {
			model.addAttribute("reserver", ((UserDo)session.getAttribute(Constants.PM_USER_SESSION)).getUsername());
		}
		return SessionHandler.verifySession(request, "equipment/equipment_reserve");
	}
	
	@RequestMapping(value = "/equipment_reserve", method = RequestMethod.POST)
	public String doReserve(ReserveDo reserveDo, Model model) {
		Result<Integer> rs;
		try {
			reserveDo.setStartTime(DateUtils.convertStr2Str(reserveDo.getStartTime(), Constants.EASYUI_DATE_FORMAT, Constants.PM_DATE_FORMAT));
			reserveDo.setEndTime(DateUtils.convertStr2Str(reserveDo.getEndTime(), Constants.EASYUI_DATE_FORMAT, Constants.PM_DATE_FORMAT));
			rs = reserveService.addReserve(reserveDo);
			if(rs.isSuccess()) {
				//update equipment status
				EquipmentDo tmp = equipmentService.findEquipmentById(reserveDo.getEquipmentId()).getDefaultModel();
				tmp.setStatus(Constants.RESERVE_EQUIPMENT_STATUS);
				rs = equipmentService.updateEquipment(tmp);
				if(rs.isSuccess()) {
					model.addAttribute("msg", "The reserve operation is success!");
				}
				
			}
			else {
				model.addAttribute("msg", "The reserve operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("EquipmentController.doReserve : ", e);
		}
		return "msg";
	}

	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setReserveService(IReserveService reserveService) {
		this.reserveService = reserveService;
	}
}
