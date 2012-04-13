/*
 * Copyright 2008-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jxstar.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.jxstar.control.action.Action;
import org.jxstar.util.factory.SystemFactory;
import org.jxstar.util.resource.JsMessage;

/**
 * 所有前端请求的控制器。
 *
 * @author TonyTan
 * @version 1.0, 2009-5-28
 */
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 3080747755569542886L;
	
	//private static final Log _log = Log.getInstance();	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		processRequest(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		processRequest(request, response);
	}	
	
	/**
	 * 处理HTTP请求。
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException  {
		//取actionName，取URL中最后一个/与.do之间的字符串
		String requestUrl = request.getRequestURI();
		String actionName = parseActionName(requestUrl);
		
		//创建action的实例
		Action action = (Action) SystemFactory.createSystemObject(actionName);
		if (action == null) {
			request.setAttribute("error_code", "401");
			request.setAttribute("error_info", 
					JsMessage.getValue("controlservlet.actionisnull", actionName));
			response.sendError(401);
			
			return;
		}

		//执行action
		action.execute(request, response);
	}
	
	/**
	 * 根据请求路径返回action名称
	 * 
	 * @param reqUrl - 请求路径
	 * @return String
	 */
	private String parseActionName(String requestUrl) {
		if (requestUrl == null || requestUrl.length() == 0) {
			return "";
		}
		
		requestUrl = requestUrl.replaceAll("\\\\", "/");
		int last = requestUrl.lastIndexOf("/");
		if (last >= 0) {
			requestUrl = requestUrl.substring(last + 1);
		}

		return requestUrl.split("\\.")[0];
	}
}
