package ningbo.media.core.security.resource;

import java.util.Collection;
import java.util.LinkedHashMap;

import ningbo.media.core.security.cache.SecurityResourceCache;
import ningbo.media.entity.Resource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

/**
 * Description:授权资源静态载入(启动服务时一次性加载)
 * 
 * @author Devon.Ning
 * @2012-4-18下午03:34:47
 * @version 1.0 Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 */
public class StaticInvocationSecurityMetadataSource implements
		FactoryBean<Object> {

	public Object getObject() throws Exception {
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = buildRequestMap();
		DefaultFilterInvocationSecurityMetadataSource definitionSource = new DefaultFilterInvocationSecurityMetadataSource(
				requestMap);
		return definitionSource;
	}

	public Class<?> getObjectType() {
		return DefaultFilterInvocationSecurityMetadataSource.class;
	}

	public boolean isSingleton() {
		return true;
	}

	protected LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap()
			throws Exception {
		Collection<Resource> reses = SecurityResourceCache.getAllCache();
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> distMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		for (Resource res : reses) {
			distMap.put(new AntPathRequestMatcher(res.getPath()),
					getConfigAttributes(res.getName()));
		}
		return distMap;
	}

	private Collection<ConfigAttribute> getConfigAttributes(String... value) {
		// SecurityConfig.createListFromCommaDelimitedString(value[0]);
		return SecurityConfig.createList(value);
	}

}
