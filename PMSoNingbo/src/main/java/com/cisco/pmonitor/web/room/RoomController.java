package com.cisco.pmonitor.web.room;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.pmonitor.dao.dataobject.RoomDo;
import com.cisco.pmonitor.dao.query.RoomQuery;
import com.cisco.pmonitor.service.IDepartmentService;
import com.cisco.pmonitor.service.IRoomService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;
import com.cisco.pmonitor.web.util.SessionHandler;


@Controller
@RequestMapping("/room")
public class RoomController {

	protected final Logger logger = Logger.getLogger(RoomController.class);
	private IRoomService roomService;
	private IDepartmentService departmentService;
	
	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}
	
	@RequestMapping(value = "/query_list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(RoomQuery query){
		Result<Map<String, Object>> rs;
		try {
			rs = roomService.loadRoomsByQuery(query);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("GroupController.queryList : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/room_view", method = RequestMethod.GET)
	public String doRoom(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "room/room_view");
	}
	
	@RequestMapping(value = "/query_all", method = RequestMethod.POST)
	@ResponseBody
	public List<RoomDo> queryAll() {
		Result<List<RoomDo>> rs;
		try {
			rs = roomService.loadAllRooms();
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("GroupController.queryAll : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/query_union", method = RequestMethod.POST)
	@ResponseBody
	public List<RoomDo> queryUnion(@RequestParam int departmentId) {
		Result<List<RoomDo>> rs;
		try {
			rs = roomService.loadRoomsByDepartmentId(departmentId);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("GroupController.queryAll : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/room_form", method = RequestMethod.POST)
	public String doForm(@RequestParam int roomId, Model model) {
		String linkUrl = "room/query_list?id=" + roomId;
		model.addAttribute("linkUrl", linkUrl);
		return "linkurl";
	}
	
	@RequestMapping(value = "/room_add", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "room/room_add");
	}
	
	@RequestMapping(value = "/room_add", method = RequestMethod.POST)
	public String doAdd(RoomDo roomDo, Model model) {
		Result<Integer> rs;
		try {
			rs = roomService.addRoom(roomDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The add room operation is success!");
			}
			else {
				model.addAttribute("msg", "The add room operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("RoomController.doAdd : ", e);
		}
		return "msg";
	}
	@RequestMapping(value = "/room_del", method = RequestMethod.POST)
	public String doDel(@RequestParam int id, Model model) {
		Result<Integer> rs;
		try {
			rs = roomService.deleteRoom(id);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The del room operation is success!");
			}
			else {
				model.addAttribute("msg", "The del room operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("RoomController.doDel : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/room_edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam int id, Model model,HttpServletRequest request) {
		Result<RoomDo> rs;
		try {
			rs = roomService.findRoomById(id);
			if(rs.isSuccess()) {
				model.addAttribute("roomDo", rs.getDefaultModel());
				model.addAttribute("departmentList", departmentService.loadAllDepartments().getDefaultModel());
			}
		} catch (ServiceException e) {
			logger.error("RoomController.toEdit : ", e);
		}
		return SessionHandler.verifySession(request, "room/room_edit");
	}
	
	@RequestMapping(value = "/room_edit", method = RequestMethod.POST)
	public String doEdit(RoomDo roomDo, Model model) {
		Result<Integer> rs;
		try {
			rs = roomService.updateRoom(roomDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The edit room operation is success!");
			}
			else {
				model.addAttribute("msg", "The edit room operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("RoomController.doEdit : ", e);
		}
		return "msg";
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
