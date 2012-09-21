package com.cisco.pmonitor.web.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.pmonitor.dao.dataobject.GroupDo;
import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.dataobject.UserGroupDo;
import com.cisco.pmonitor.dao.query.GroupQuery;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.service.IGroupService;
import com.cisco.pmonitor.service.IUserGroupService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;

@Controller
@RequestMapping("/group")
public class GroupController {

	protected final Logger logger = Logger.getLogger(GroupController.class);
	private IGroupService groupService;
	private IUserGroupService userGroupService;
	
	/**
	 * spring can auto load the query value, so nice.
	 */
	@RequestMapping(value = "/query_list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(GroupQuery query){
		//GroupQuery query = new GroupQuery();
		Result<Map<String, Object>> rs;
		try {
			rs = groupService.loadGroupsByQuery(query);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("GroupController.queryList : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = "/query_union", method = RequestMethod.POST)
	@ResponseBody
	public List<GroupDo> queryUnion(@RequestParam int roomId) {
		Result<List<GroupDo>> rs;
		try {
			rs = groupService.loadGroupsByRoomId(roomId);
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("GroupController.queryAll : ", e);
		}
		return null;
	}
	@RequestMapping(value = "/query_all", method = RequestMethod.POST)
	@ResponseBody
	public List<GroupDo> queryAll() {
		Result<List<GroupDo>> rs;
		try {
			rs = groupService.loadAllGroups();
			if(rs.isSuccess()) {
				return rs.getDefaultModel();
			}
		} catch (ServiceException e) {
			logger.error("GroupController.queryAll : ", e);
		}
		return null;
	}
	@RequestMapping(value = "/query_assign", method = RequestMethod.POST)
	@ResponseBody
	public List<GroupDo> queryAssign(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(null != session) {
			UserDo userDo = (UserDo) session.getAttribute(Constants.PM_USER_SESSION);
			if(userDo.getRole() == Constants.SYSTEM_USER_ROLE) {
				return queryAll();
			}
			else {
				List<GroupDo> tmp = new ArrayList<GroupDo>();
				try {
					Result<List<UserGroupDo>> rs = userGroupService.findUserGroupByUserName(userDo.getUsername());
					if(rs.isSuccess()) {
						List<UserGroupDo> list = rs.getDefaultModel();
						if(null != list && list.size() > 0) {
							for(UserGroupDo ug : list) {
								if(ug.getRole() == Constants.SYSTEM_USER_GROUP_ROLE) {
									Result<GroupDo> rg = groupService.findGroupById(ug.getGroupId());
									if(rs.isSuccess()) {
										tmp.add(rg.getDefaultModel());
									}
								}
							}
						}
					}
					return tmp;
				} catch (ServiceException e) {
					logger.error("GroupController.queryAssign : ", e);
				}
			}
		}
		return null;
	}
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
}
