package com.ssh2.web.action.general;

import java.util.LinkedHashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ssh2.service.user.RoleService;
import com.ssh2.utils.MenuUtil;
import com.ssh2.vo.menu.MenuLinked;
import com.ssh2.vo.user.RoleModel;
import com.ssh2.web.action.BaseAction;

public class MenuAction extends BaseAction {
	private RoleService roleService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1624883805540773145L;
	
	public String index()throws Exception{
		JSONArray array = new JSONArray();
		RoleModel role = roleService.getRoleById("1");
		
		if(null != role){
			
			
			Set<MenuLinked> menus = new LinkedHashSet<MenuLinked>();
			MenuUtil.menu2linked(menus, roleService.getMenusByRole(role));
			
			for(MenuLinked menu: menus){		
				JSONObject json = new JSONObject();
                json.element("text", menu.getMenuTitle());
                if (menu.getMenuChildren() != null && menu.getMenuChildren().size()>0) {
                	json.element("leaf", false);
                	json.element("children", getGrandChildrenMenuTree(menu.getMenuChildren()));
                }else {
                	json.element("linkUrl", menu.getMenuURL());
					json.element("leaf", true);
					json.element("id", menu.getMenuId());
				}
                array.add(json);
			}
		}
		servletResponse.setCharacterEncoding("utf-8");
		ajaxPrint(servletResponse, array.toString());
		System.out.println(array.toString());
		return NONE;
	}
	
	private JSONArray getGrandChildrenMenuTree(Set<MenuLinked> set) {
	   JSONArray array = new JSONArray();
	   for (MenuLinked child : set) {
	      JSONObject json = new JSONObject();
	      json.element("text", child.getMenuTitle());
	      if(child.getMenuChildren() != null && child.getMenuChildren().size()>0){
              json.element("leaf", false);
              json.element("children", getGrandChildrenMenuTree(child.getMenuChildren()));
	      }else{
             json.element("leaf", true);
             json.element("linkUrl", child.getMenuURL());
             json.element("id", child.getMenuId());
//             if(child.getComponent() != null){
//                  json.element("id", child.getComponent());
//             }
	      }
	    array.add(json);
	   }
	   return array;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

}
