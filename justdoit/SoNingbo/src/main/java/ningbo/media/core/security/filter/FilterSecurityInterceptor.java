package ningbo.media.core.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import ningbo.media.core.security.OnlineSystemUserEventPublisher;
import ningbo.media.core.security.cache.OnlineSystemUserCache;
import ningbo.media.core.security.resource.SecurityUtils;
import ningbo.media.entity.SystemUser;
import ningbo.media.entity.UserDetailsImpl;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * Description:安全过滤器
 * 
 * @author Devon.Ning
 * @2012-4-18下午03:44:41
 * @version 1.0 Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 */
public class FilterSecurityInterceptor extends AbstractSecurityInterceptor
		implements Filter {

	private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	private boolean observeOncePerRequest = true;
	private boolean onlineUser = false;

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		onLoginAddOnLineCache(request);
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	/**
	 * 在登录时放入在线用户缓存
	 * 
	 * @param servletRequest
	 */
	private void onLoginAddOnLineCache(ServletRequest servletRequest) {
		if (isOnlineUser()) {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			SystemUser user = SecurityUtils.getCurrentUser();
			String username = (String) request.getSession().getAttribute(
					OnlineSystemUserEventPublisher.LOGIN_USER_NAME);
			if (user != null) {
				if (username == null) {
					request.getSession().setAttribute(
							OnlineSystemUserEventPublisher.LOGIN_USER_NAME,
							user.getUsername());
				}
				if (user.getLoginIp() == null) {
					user.setLoginIp(SecurityUtils.getCurrentUserIPAddress());
					UserDetails userDetails = new UserDetailsImpl(user);
					SecurityUtils
							.saveUserDetailsToContext(userDetails, request);
					OnlineSystemUserCache.put(user);
				}
			}
		}
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource newSource) {
		this.securityMetadataSource = newSource;
	}

	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public void invoke(FilterInvocation fi) throws IOException,
			ServletException {
		if ((fi.getRequest() != null)
				&& (fi.getRequest().getAttribute(FILTER_APPLIED) != null)
				&& observeOncePerRequest) {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} else {
			if (fi.getRequest() != null) {
				fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
			}
			InterceptorStatusToken token = super.beforeInvocation(fi);
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
			super.afterInvocation(token, null);
		}
	}

	public boolean isObserveOncePerRequest() {
		return observeOncePerRequest;
	}

	public void setObserveOncePerRequest(boolean observeOncePerRequest) {
		this.observeOncePerRequest = observeOncePerRequest;
	}

	public boolean isOnlineUser() {
		return onlineUser;
	}

	public void setOnlineUser(boolean onlineUser) {
		this.onlineUser = onlineUser;
	}

}
