package com.ssh2.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh2.service.user.UserService;

public class BaseAction extends ActionSupport implements SessionAware,ApplicationAware ,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6561672365157071699L;
	
		protected Map<String, Object>  session;
		protected Map<String, Object>  application;
	    protected HttpServletResponse servletResponse;

		protected String ajax;
		protected UserService userService;
		protected String resultPath;//返回的路径
		
		public static class AjaxResponse{
			public AjaxResponse(){}
			public static final String RESPONSE_ERROR = "fail";
			public static final String RESPONSE_SUCCESS = "success";
		}
		
		
		/**
		 * 是否是ajax request
		 * @author yufeng
		 */
		protected boolean isAjaxReq(){
			return ajax != null;
		}
		
		/**
		 * 
		 * @param response
		 * @param msg
		 * @return
		 */
		protected String ajaxPrint(HttpServletResponse response, String msg) {
			try{
				response.getWriter().write(msg);
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 
		 * @return SUCCESS
		 * @author yufeng
		 */
		protected String defaultResult(){
			return SUCCESS;
		}
		
		/**
		 * 
		 * @return datagrid
		 * @author yufeng
		 */
		protected String dataSliceResult() {
			return "datagrid";
		}
		
		/**
		 * 
		 * @return view  path
		 * @author yufeng
		 */
		protected String viewResult(String path) {
			resultPath = path;
			return "view";
		}
		
		/**
		 * 
		 * @param path
		 * @return
		 * @author yufeng
		 */
		protected String redirectAction(String path) {
			resultPath = path;
			return "redirect-action";
		}	
		
		/**
		 * 
		 * 
		 * @return
		 */
		protected String jsonResult(){
			return "json";
		}
		
		
		public String getAjax() {
			return ajax;
		}

		public void setAjax(String ajax) {
			this.ajax = ajax;
		}

		public Map<String, Object> getApplication() {
			return application;
		}
		

		@Override
		public void setSession(Map<String, Object> session) {
			// TODO Auto-generated method stub
			this.session = session;
		}

		@Override
		public void setApplication(Map<String, Object> application) {
			// TODO Auto-generated method stub
			this.application = application;
		}

		public UserService getUserService() {
			return userService;
		}

		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		public Map<String, Object> getSession() {
			return session;
		}

		public String getResultPath() {
			return resultPath;
		}

		public void setResultPath(String resultPath) {
			this.resultPath = resultPath;
		}


		@Override
		public void setServletResponse(HttpServletResponse servletResponse) {
			// TODO Auto-generated method stub
			this.servletResponse = servletResponse;
		}
		public HttpServletResponse getServletResponse() {
			return servletResponse;
		}

}
