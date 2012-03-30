package com.jeecms.cms.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;

@Controller
public class AndroidAct {

	private static final Logger log = LoggerFactory.getLogger(AndroidAct.class);
	
	@RequestMapping("/androidapp/v_list.do")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		
		String pageNo = RequestUtils.getQueryParam(request, "pageNo");
		String path = RequestUtils.getQueryParam(request, "path");
		String unicode = RequestUtils.getQueryParam(request, "unicode");
		
		String title= null;
		int orderBy = 0;
		int pageSize = 10;
		Boolean titleImg = false;
		Boolean recommend = false;
		Integer[] siteIds = new Integer[]{1};
		Integer[] typeIds = new Integer[]{1};
		
		String[] paths = null;
		if(path!=null && !"".equals(path)){
			paths = path.split(",");
		}
		
		if(unicode==null){unicode="UTF-8";}
		
		try {
			
			Pagination pagelist = contentMng.getPageByChannelPathsForTag(paths,siteIds, typeIds, titleImg, recommend, title, orderBy, Integer.parseInt(pageNo), pageSize);
			
			JSONArray jsonArray = new JSONArray();
			if(pagelist!=null){
				List<Content> contentlist = (List<Content>)pagelist.getList();
				JSONObject json;
				for(Content c : contentlist){
					json = new JSONObject();
					json.put("title", c.getTitle());
					json.put("content", c.getContentTxt().getTxt());
					jsonArray.put(json);
				}
			}
			response.getOutputStream().write(jsonArray.toString().getBytes(unicode));
		} catch (Exception e) {
			log.error("/androidapp/v_list.do :", e);
		}
	}
	
	@Autowired
	protected ContentMng contentMng;
}
