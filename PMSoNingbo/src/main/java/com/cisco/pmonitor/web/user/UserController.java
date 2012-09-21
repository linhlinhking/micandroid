package com.cisco.pmonitor.web.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.pmonitor.dao.dataobject.GroupDo;
import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.dataobject.UserGroupDo;
import com.cisco.pmonitor.dao.query.UserQuery;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.dao.utils.DateUtils;
import com.cisco.pmonitor.service.IGroupService;
import com.cisco.pmonitor.service.IUserGroupService;
import com.cisco.pmonitor.service.IUserService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;
import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
@RequestMapping("/user")
public class UserController {

	protected final Logger logger = Logger.getLogger(UserController.class);
	private IUserService userService;
	private IUserGroupService userGroupService;
	private IGroupService groupService;
	
	@RequestMapping(value = "/user_view", method = RequestMethod.GET)
	public String toUser(HttpServletRequest request, Model model) {
		int privilege = 0;
		HttpSession session = request.getSession(false);
		if(null != session) {
			UserDo userDo = (UserDo) session.getAttribute(Constants.PM_USER_SESSION);
			if(userDo.getRole() == Constants.SYSTEM_USER_ROLE) {
				privilege = 2;
			}
			else {
				Result<List<UserGroupDo>> rs;
				try {
					rs = userGroupService.findUserGroupByUserName(userDo.getUsername());
					if(rs.isSuccess()) {
						List<UserGroupDo> list = rs.getDefaultModel();
						if(null != list && list.size() > 0) {
							for(UserGroupDo ug : list) {
								if(ug.getRole() == Constants.SYSTEM_USER_GROUP_ROLE) {
									privilege = 1;
									break;
								}
							}
						}
					}
				} catch (ServiceException e) {
					logger.error("UserController.toUser : ", e);
				}
			}
			
		}
		model.addAttribute("privilege", privilege);
		return SessionHandler.verifySession(request, "user/user_view");
	}
	
	@RequestMapping(value = "/query_list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(UserQuery query) {
		Result<Map<String, Object>> rs;
		try {
			rs = userService.loadUsersByQuery(query);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("UserController.queryList : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/user_form", method = RequestMethod.POST)
	public String doForm(UserQuery query, Model model) {
		String linkUrl = "user/query_list?username=" + query.getUsername() + "&role=" + query.getRole();
		model.addAttribute("linkUrl", linkUrl);
		return "linkurl";
	}
	
	@RequestMapping(value = "/user_add", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "user/user_add");
	}
	
	@RequestMapping(value = "/user_add", method = RequestMethod.POST)
	public String doAdd(UserDo userDo, Model model) {
		Result<Integer> rs;
		try {
			if(StringUtils.isEmpty(userDo.getEmail())) {
				userDo.setEmail(userDo.getUsername() + "@cisco.com");
			}
			if(StringUtils.isEmpty(userDo.getFullName())) {
				userDo.setFullName(userDo.getUsername());
			}
			userDo.setGmtCreate(DateUtils.date2Str(new Date(), Constants.PM_DATE_FORMAT));
			userDo.setGmtModified(userDo.getGmtCreate());
			Result<UserDo> result = userService.findUserByUsername(userDo.getUsername());
			if(!result.isSuccess()) {
				rs = userService.addUser(userDo);
				model.addAttribute("msg", rs.getMsg());
			}
			else {
				model.addAttribute("msg", "The user is already existed.");
			}
		} catch (ServiceException e) {
			logger.error("UserController.doAdd : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/user_edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam int id, HttpServletRequest request, Model model) {
		Result<UserDo> rs;
		try {
			rs = userService.findUserById(id);
			if(rs.isSuccess()) {
				model.addAttribute("userDo", rs.getDefaultModel());
			}
		} catch (ServiceException e) {
			logger.error("UserController.toEdit : ", e);
		}
		return SessionHandler.verifySession(request, "user/user_edit");
	}
	
	@RequestMapping(value = "/user_edit", method = RequestMethod.POST)
	public String doEdit(UserDo userDo, Model model) {
		Result<UserDo> rs;
		Result<Integer> result;
		try {
			rs = userService.findUserById(userDo.getId());
			if(rs.isSuccess()) {
				UserDo tmp = rs.getDefaultModel();
				userDo.setGmtCreate(tmp.getGmtCreate());
				userDo.setGmtModified(DateUtils.date2Str(new Date(), Constants.PM_DATE_FORMAT));
				userDo.setPassword(tmp.getPassword());
				if(!tmp.getUsername().equalsIgnoreCase(userDo.getUsername())) {
					Result<UserDo> rt = userService.findUserByUsername(userDo.getUsername());
					if(rt.isSuccess()) {
						model.addAttribute("msg", "The user is already existed.");
						return "msg";
					}
				}
				result = userService.updateUser(userDo);
				if(result.isSuccess()) {
					model.addAttribute("msg", "The edit user operation is success!");
				}
				else {
					model.addAttribute("msg", "The edit user operation is error!");
				}
			}
		} catch (ServiceException e) {
			logger.error("UserController.doEdit : ", e);
		}
		return "msg";
	}
	
	@RequestMapping(value = "/user_del", method = RequestMethod.POST)
	public String doDel(@RequestParam int id, Model model) {
		Result<Integer> rs;
		try {
			rs = userService.deleteUser(id);
			if(rs.isSuccess()) {
				model.addAttribute("msg", "The delete user operation is success!");
			}
			else {
				model.addAttribute("msg", "The delete user operation is error!");
			}
		} catch (ServiceException e) {
			logger.error("UserController.doDel : ", e);
		}
		return "msg";
	}
	@RequestMapping(value = "/user_assign", method = RequestMethod.GET)
	public String toAssign(@RequestParam String username, HttpServletRequest request, Model model) {
		model.addAttribute("username", username);
		return SessionHandler.verifySession(request, "user/user_assign");
	}
	
	@RequestMapping(value = "/user_assign", method =  RequestMethod.POST)
	public String doAssign(@RequestParam int role, @RequestParam String username, HttpServletRequest request, Model model) {
		String[] ids = request.getParameterValues("group");
		if(null != ids && ids.length > 0) {
			int counter = 0;
			for(String id : ids) {
				UserGroupDo userGroupDo = new UserGroupDo();
				userGroupDo.setGroupId(Integer.parseInt(id));
				userGroupDo.setRole(role);
				userGroupDo.setUsername(username);
				try {
					Result<UserGroupDo> result  = userGroupService.findUserGroupByUserAndGroup(username, userGroupDo.getGroupId());
					if(result.isSuccess()) {
						counter++;
						break;
					}
					Result<Integer> rs = userGroupService.addUserGroup(userGroupDo);
					if(rs.isSuccess()) {
						model.addAttribute("msg", "The assign operation is success!");
					}
					else {
						model.addAttribute("msg", "The assign operation is error!");
					}
				} catch (ServiceException e) {
					logger.error("UserController.doAssign : ", e);
				}
			}
			if(counter == ids.length) {
				model.addAttribute("msg", "The assign operation is success!");	
			}
		}
		else {
			model.addAttribute("msg", "The assign operation is error!");
		}
		return "msg";
	}
	
	@RequestMapping(value = "/manage_group", method = RequestMethod.GET)
	public String toManageGroup(@RequestParam String username, HttpServletRequest request, Model model) {
		model.addAttribute("username", username);
		try {
			Result<List<UserGroupDo>> rs = userGroupService.findUserGroupByUserName(username);
			if(rs.isSuccess()) {
				List<UserGroupDo> list = rs.getDefaultModel();
				List<GroupDo> groupList = new ArrayList<GroupDo>();
				if(null != list && list.size() > 0) {
					for(UserGroupDo ug : list) {
						Result<GroupDo> tmp = groupService.findGroupById(ug.getGroupId());
						if(tmp.isSuccess()) {
							groupList.add(tmp.getDefaultModel());
						}
					}
				}
				model.addAttribute("list", groupList);
				return SessionHandler.verifySession(request, "user/manage_group");
			}
		} catch (ServiceException e) {
			logger.error("UserController.toManageGroup : ", e);
		}
		return SessionHandler.verifySession(request, "user/user_view");
	}
	
	@RequestMapping(value = "/manage_group", method = RequestMethod.POST)
	public String doManageGroup(@RequestParam String username, HttpServletRequest request, Model model) {
		String[] ids = request.getParameterValues("id");
		if(null == ids || ids.length == 0) {
			try {
				Result<List<UserGroupDo>> rs = userGroupService.findUserGroupByUserName(username);
				if(rs.isSuccess()) {
					List<UserGroupDo> list = rs.getDefaultModel();
					if(null != list && list.size() > 0) {
						for(UserGroupDo ug : list) {
							userGroupService.deleteUserGroup(ug.getId());
						}
					}
				}
			} catch (ServiceException e) {
				logger.error("UserController.doManageGroup : ", e);
			}
		}
		else {
			try {
				Result<List<UserGroupDo>> rs = userGroupService.findUserGroupByUserName(username);
				if(rs.isSuccess()) {
					List<UserGroupDo> list = rs.getDefaultModel();
					if(null != list && list.size() > 0) {
						for(UserGroupDo ug : list) {
							for(String id : ids) {
								if(ug.getGroupId() == Integer.parseInt(id)) {
									list.remove(ug);
									break;
								}
							}
						}
					}
					if(null != list && list.size() > 0) {
						for(UserGroupDo ug : list) {
							userGroupService.deleteUserGroup(ug.getId());
						}
					}
				}
			} catch (ServiceException e) {
				logger.error("UserController.doManageGroup : ", e);
			}
		}
		model.addAttribute("msg", "The manage operation is success!");
		return "msg";
	}
	

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}
}
