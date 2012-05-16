package ningbo.media.core.security.cache;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import ningbo.media.entity.Resource;

/**
 * Description:Cache Resource.
 * 
 * @author Devon.Ning
 * @2012-4-18下午02:55:59
 * @version 1.0
 * 
 * Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 * 
 */
public class SecurityResourceCache {

	private final static String SECURITY_RESOURCE_NAME = "security_resource";
	private static Cache cache = CacheManager.getInstance().getCache(
			SECURITY_RESOURCE_NAME);

	public synchronized static void put(Resource resource) {
		Element element = new Element(resource.getPath(), resource);
		cache.put(element);
	}

	public synchronized static Resource get(String path) {
		Element element = null;
		try {
			element = cache.get(path);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("ResourceCache failure: "
					+ cacheException.getMessage(), cacheException);
		}
		if (element == null) {
			return null;
		} else {
			return (Resource) element.getValue();
		}
	}

	public synchronized static void remove(String path) {
		cache.remove(path);
	}

	public synchronized static void removeAll(String path) {
		cache.removeAll();
		cache.clearStatistics();
		cache.flush();
	}

	@SuppressWarnings("unchecked")
	public synchronized static Collection<Resource> getAllCache() {
		Collection<String> resources;
		Collection<Resource> resclist = new ArrayList<Resource>();
		try {
			resources = cache.getKeys();
		} catch (IllegalStateException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} catch (CacheException e) {
			throw new UnsupportedOperationException(e.getMessage(), e);
		}
		for (String resPath : resources) {
			Resource rd = get(resPath);
			resclist.add(rd);
		}
		return resclist;
	}

	public synchronized static GrantedAuthority[] getAuthoritysInCache(
			String path) {
		GrantedAuthority[] gas = { new SimpleGrantedAuthority(get(path)
				.getName()) };
		return gas;
	}
}
