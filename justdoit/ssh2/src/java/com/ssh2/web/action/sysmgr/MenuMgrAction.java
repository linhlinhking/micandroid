package com.ssh2.web.action.sysmgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.ssh2.ServiceException;
import com.ssh2.service.menu.MenuService;
import com.ssh2.utils.ConverToJson;
import com.ssh2.utils.PaginationSupport;
import com.ssh2.vo.menu.MenuModel;
import com.ssh2.web.action.BaseAction;

public class MenuMgrAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9213437462784247567L;
	private static final Logger LOGGER = Logger.getLogger(MenuMgrAction.class);
	private MenuService menuService;
	private int start;
	private int limit;
	private int totalCount;
	private List<MenuModel> menuList;
	
	public String index() {
		return defaultResult();
	}
	
	public String get()throws Exception{
		servletResponse.setCharacterEncoding("UTF-8");
		String json = "";
		
		try {
			PaginationSupport<MenuModel> menus = menuService.getPage(limit, start, null, true);
			if (menus == null) {
				json = "{\"rows\":null,\"totalCount\":0}";
			}else {
				List<MenuModel> list = menus.getItems();
				json = "{\"rows\":[";
				json += ConverToJson.ConverMenuListToJson(list) + "],\"totalCount\":";
				totalCount = menuService.getTotal();
				json += totalCount + "}";
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ajaxPrint(servletResponse, json);
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@JSON(serialize=false) 
	public MenuService getMenuService() {
		return menuService;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JSON(serialize=false) 
	public int getStart() {
		return start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@JSON(serialize=false) 
	public int getLimit() {
		return limit;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setMenuList(List<MenuModel> menuList) {
		this.menuList = menuList;
	}

	@JSON(name="rows") 
	public List<MenuModel> getMenuList() {
		return menuList;
	}



}
