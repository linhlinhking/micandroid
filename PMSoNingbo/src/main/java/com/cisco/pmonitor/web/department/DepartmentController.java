package com.cisco.pmonitor.web.department;

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

import com.cisco.pmonitor.dao.dataobject.DepartmentDo;
import com.cisco.pmonitor.dao.query.DepartmentQuery;
import com.cisco.pmonitor.service.IDepartmentService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;
import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	protected final Logger logger = Logger.getLogger(DepartmentController.class);
	private IDepartmentService departmentService;
	@RequestMapping(value="/query_all", method = RequestMethod.POST)
	@ResponseBody
	public List<DepartmentDo> queryAll() {
		Result<List<DepartmentDo>> rs;
		try {
			rs = departmentService.loadAllDepartments();
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("DepartmentController.queryAll : ", e);
		}
		return null;
	}
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value = "/department_view", method = RequestMethod.GET)
	public String toDepartment(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "department/department_view");
	}
	
	@RequestMapping(value = "/query_list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DepartmentQuery query){
		Result<Map<String, Object>> rs;
		try {
			rs = departmentService.loadDepartmentsByQuery(query);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("DepartmentController.queryList : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/department_form", method = RequestMethod.POST)
	public String doForm(@RequestParam int departmentId, Model model) {
		String linkUrl = "department/query_list?id=" + departmentId;
		model.addAttribute("linkUrl", linkUrl);
		return "linkurl";
	}
	
	@RequestMapping(value = "/department_add", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "department/department_add");
	}
	
	@RequestMapping(value = "/department_add", method = RequestMethod.POST)
	public String doAdd(DepartmentDo departmentDo, Model model) {
		Result<Integer> rs;
		try {
			rs = departmentService.addDepartment(departmentDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The add department operation is success!");
			}
			else {
				model.addAttribute("msg", "The add department operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("DepartmentController.doAdd : ", e);
		}
		return "msg";
	}
	@RequestMapping(value = "/department_del", method = RequestMethod.POST)
	public String doDel(@RequestParam int id, Model model) {
		Result<Integer> rs;
		try {
			rs = departmentService.deleteDepartment(id);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The del department operation is success!");
			}
			else {
				model.addAttribute("msg", "The del department operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("DepartmentController.doDel : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/department_edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam int id, Model model,HttpServletRequest request) {
		Result<DepartmentDo> rs;
		try {
			rs = departmentService.findDepartmentById(id);
			if(rs.isSuccess()) {
				model.addAttribute("departmentDo", rs.getDefaultModel());
			}
		} catch (ServiceException e) {
			logger.error("DepartmentController.toEdit : ", e);
		}
		return SessionHandler.verifySession(request, "department/department_edit");
	}
	
	@RequestMapping(value = "/department_edit", method = RequestMethod.POST)
	public String doEdit(DepartmentDo departmentDo, Model model) {
		Result<Integer> rs;
		try {
			rs = departmentService.updateDepartment(departmentDo);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The edit department operation is success!");
			}
			else {
				model.addAttribute("msg", "The edit department operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("DepartmentController.doEdit : ", e);
		}
		return "msg";
	}
}
