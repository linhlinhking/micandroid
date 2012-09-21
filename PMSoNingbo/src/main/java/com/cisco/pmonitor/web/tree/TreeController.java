package com.cisco.pmonitor.web.tree;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisco.pmonitor.dao.dataobject.EquipmentDo;
import com.cisco.pmonitor.dao.dataobject.GroupDo;
import com.cisco.pmonitor.dao.dataobject.UserDo;
import com.cisco.pmonitor.dao.dataobject.UserGroupDo;
import com.cisco.pmonitor.dao.utils.Constants;
import com.cisco.pmonitor.service.IEquipmentService;
import com.cisco.pmonitor.service.IGroupService;
import com.cisco.pmonitor.service.IUserGroupService;
import com.cisco.pmonitor.service.exception.ServiceException;
import com.cisco.pmonitor.service.util.Result;
import com.cisco.pmonitor.web.util.SessionHandler;

@Controller
@RequestMapping("/tree")
public class TreeController {
	
	private IGroupService groupService;
	private IUserGroupService userGroupService;
	private IEquipmentService equipmentService;

	@RequestMapping(value = "/tree_view", method = RequestMethod.POST)
	public String toView(HttpServletRequest request, Model model) {
		try {
			model.addAttribute("treeView", treeView(request));
		} catch (ServiceException e) {
		}
		return SessionHandler.verifySession(request, "tree/tree_view");
	}
	
	private String treeView(HttpServletRequest request) throws ServiceException {
		StringBuilder sb = new StringBuilder();
		HttpSession session = request.getSession(false);
		if(null != session && null != session.getAttribute(
										Constants.PM_USER_SESSION)) {
			UserDo user = (UserDo) session.getAttribute(Constants.PM_USER_SESSION);
			Result<List<UserGroupDo>> ugRs = userGroupService.findUserGroupByUserName(user.getUsername());
			if(ugRs.isSuccess()) {
				sb.append("[");
				List<UserGroupDo> ugList = ugRs.getDefaultModel();
				for(int i = 0; i < ugList.size(); i ++) {
					sb.append("{\n");
					UserGroupDo ug = ugList.get(i);
					Result<GroupDo> gRs = groupService.findGroupById(ug.getGroupId());
					if(gRs.isSuccess()) {
						GroupDo g = gRs.getDefaultModel();
						sb.append("\"id\":" + i + ",\n");
						sb.append("\"text\":\"" + g.getName() + "\",\n");
						Result<List<EquipmentDo>> eRs = equipmentService.loadEquipmentsByGroupId(g.getId());
						if(eRs.isSuccess()) {
							List<EquipmentDo> eList = eRs.getDefaultModel();
							if(eList != null && eList.size() > 0) {
								sb.append("\"children\":[\n");
								for(int j = 0; j < eList.size(); j ++) {
									EquipmentDo e = eList.get(j);
									sb.append("{");
									sb.append("\"id\":" + j + ",\n");
									sb.append("\"text\":\"" + e.getName() + "\",\n");
									sb.append("\"attributes\":{\"url\":\"");
									sb.append("monitor/query_list?equipmentId=" + e.getId());
									sb.append("\"}");
									sb.append("}");
									if(j < eList.size() - 1) {
										sb.append(",");
									}
								}
								sb.append("]\n");
							}
						}
					}
					sb.append("}\n");
					if(i < ugList.size() - 1) {
						sb.append(",");
					}
				}
				sb.append("]\n");
			}
		}
		return sb.toString();
	}

	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
}
