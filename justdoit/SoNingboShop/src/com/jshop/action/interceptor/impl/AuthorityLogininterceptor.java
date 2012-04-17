package com.jshop.action.interceptor.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.jshop.action.tools.BaseTools;
import com.jshop.entity.UserT;
import com.jshop.service.impl.UsertServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
@ParentPackage("jshop")


@Controller("authorityLogininterceptor")
public class AuthorityLogininterceptor extends MethodFilterInterceptor {

	public static final String COOKIE_REMEMBER_KEY="cookieadminid";
	public static final String GOING_TO_URL_KEY="going_to";
	@Resource(name="usertServiceImpl")
	private UsertServiceImpl usertServiceImpl;
	public UsertServiceImpl getUsertServiceImpl() {
		return usertServiceImpl;
	}
	public void setUsertServiceImpl(UsertServiceImpl usertServiceImpl) {
		this.usertServiceImpl = usertServiceImpl;
	}


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext=invocation.getInvocationContext();
		HttpServletRequest request=(HttpServletRequest)actionContext.get(StrutsStatics.HTTP_REQUEST);
		
		Map session=actionContext.getSession();
		if((session!=null)&&(session.get(BaseTools.BACK_USER_SESSION_KEY)!=null)){
			return invocation.invoke();
		}else{
			Cookie[] cookies=request.getCookies();
			if(cookies!=null){
				for(Cookie cookie:cookies){
					if(COOKIE_REMEMBER_KEY.equals(cookie.getName())){
						String value=cookie.getValue();
						if(StringUtils.isNotEmpty(value)){
							String [] split=value.split("==");
							String userName=split[0];
							String password=split[1];
							try{
								UserT user=new UserT();
								user.setUsername(userName);
								user.setPassword(password);
								user.setState("3");
								user=this.getUsertServiceImpl().login(user);
								if(user!=null){
									session.put(BaseTools.BACK_USER_SESSION_KEY, user.getUserid());
								}else{
									//setGoingToUrl(session,invocation);
									return "adminlogin";
								}
							}catch(Exception e){
								//setGoingToUrl(session,invocation);
								return "adminlogin";
							}
							}
						}
					}
				}
			
			}
				//setGoingToUrl(session,invocation);
				return "adminlogin";
		}
	
	
//	private void setGoingToUrl(Map session,ActionInvocation invocation){
//		String url="";
//		String namespace=invocation.getProxy().getNamespace();
//		if(StringUtils.isNotEmpty(namespace)&&!namespace.equals("/")){
//			url=url+namespace;
//		}
//		String actionName=invocation.getProxy().getActionName();
//		if(StringUtils.isNotEmpty(actionName)){
//			url=url+"/"+actionName+".action";
//		}
//		session.put(GOING_TO_URL_KEY, url);
//	}
	
}
