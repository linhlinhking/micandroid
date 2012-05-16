package ningbo.media.core.security.resource;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import ningbo.media.core.security.cache.SecurityResourceCache;
import ningbo.media.entity.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

/**
 * Description:授权资源动态载入
 * @author Devon.Ning
 * @2012-4-18下午03:36:36
 * @version 1.0
 * Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 */
public class DynamicInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	protected final Log logger = LogFactory.getLog(getClass());

	protected LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap() {
		Collection<Resource> reses = SecurityResourceCache.getAllCache();
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> distMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		for (Resource res : reses) {
			distMap.put(new AntPathRequestMatcher(res.getPath()),
					getConfigAttributes(res.getName()));
		}
		return distMap;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : buildRequestMap()
				.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}
		return allAttributes;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) {
		final HttpServletRequest request = ((FilterInvocation) object)
				.getRequest();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : buildRequestMap()
				.entrySet()) {
			if (entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	private Collection<ConfigAttribute> getConfigAttributes(String... value) {
		return SecurityConfig.createList(value);
	}

}
