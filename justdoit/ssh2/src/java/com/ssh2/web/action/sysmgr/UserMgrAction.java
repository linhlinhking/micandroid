package com.ssh2.web.action.sysmgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ssh2.Constants;
import com.ssh2.ServiceException;
import com.ssh2.utils.PaginationSupport;
import com.ssh2.utils.StringUtils;
import com.ssh2.vo.user.UserModel;
import com.ssh2.web.action.BaseAction;

public class UserMgrAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2576005221298600672L;
	private static final Logger LOGGER = Logger.getLogger(UserMgrAction.class);
	
	private String start;
	private String limit;
	private String callback;
	private String _dc;


	@SuppressWarnings("unchecked")
	public String index() {
		Map map = new HashMap();
		List mapList = new ArrayList();
		String outputString = "";
		PaginationSupport<UserModel> user = null;
		int total = 1;
		
		if(StringUtils.isNotEmpty(ajax)){
			
			try {
				user = userService.getPageByModel(new UserModel(), Constants.DefaultPageSize,
						Integer.parseInt(start), null, true);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.error(e.getStackTrace());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.error(e.getStackTrace());
			} 
			
			if(user != null){
				List<UserModel> userList = user.getItems();
				if (userList != null) {
					for (UserModel userModel : userList) {
						Map cellMap = new HashMap();
						cellMap.put("id", userModel.getId());
						cellMap.put("name", userModel.getName());
						cellMap.put("mail", userModel.getMail());
						cellMap.put("preview", "this is preview");
						mapList.add(cellMap);
					}
				}
			}
			
			map.put("totalCount", total);
			map.put("users", mapList);
			
			JSONObject object = JSONObject.fromObject(map);
			servletResponse.setCharacterEncoding("utf-8");
			
			outputString = callback + "(" + object.toString() + ");";
		}
				
		return isAjaxReq()?ajaxPrint(servletResponse, outputString):defaultResult();
	}
	
	
	public String del() {
		return NONE;
	}
	
	public String getStart() {
		return start;
	}


	public void setStart(String start) {
		this.start = start;
	}


	public void setLimit(String limit) {
		this.limit = limit;
	}


	public String getLimit() {
		return limit;
	}


	public void setCallback(String callback) {
		this.callback = callback;
	}


	public String getCallback() {
		return callback;
	}


	public void set_dc(String _dc) {
		this._dc = _dc;
	}


	public String get_dc() {
		return _dc;
	}


	public static Logger getLogger() {
		return LOGGER;
	}

}
