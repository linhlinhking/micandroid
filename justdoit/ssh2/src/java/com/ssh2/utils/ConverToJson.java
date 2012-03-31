package com.ssh2.utils;

import java.util.List;

import com.ssh2.vo.menu.MenuModel;

public class ConverToJson {
	
	public static String ConverMenuListToJson(List<MenuModel> list) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append("{");
			
			sb.append("\"id\":\"");
			sb.append(list.get(i).getId());
			sb.append("\",");
			
			sb.append("\"description\":\"");
			sb.append(list.get(i).getMenuDescription());
			sb.append("\",");
			
			sb.append("\"icon\":\"");
			if(StringUtils.isEmpty(list.get(i).getMenuIconClass()))
				sb.append("null");
			else
				sb.append(list.get(i).getMenuIconClass());
			sb.append("\",");
			
			sb.append("\"order\":");
			if(null == list.get(i).getMenuOrder())
				sb.append("null");
			else
				sb.append(list.get(i).getMenuOrder());
			sb.append(",");
			
			sb.append("\"title\":\"");
			sb.append(list.get(i).getMenuTitle());
			sb.append("\",");
			
			sb.append("\"url\":\"");
			sb.append(list.get(i).getMenuURL());
			sb.append("\",");
			
			sb.append("\"parent\":");
			if(null == list.get(i).getMenuParent())
				sb.append("null");
			else
				sb.append(list.get(i).getMenuParent().getId());
			
			sb.append("}");
			if(i < list.size()-1)
				sb.append(",");
		}
		return sb.toString();
	}

}
