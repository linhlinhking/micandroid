package ningbo.media.core.security.cache;

import java.util.ArrayList;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import ningbo.media.entity.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;

/**
 * Description:Cache Current Login UserDetail
 * 
 * @author Devon.Ning
 * @2012-4-18下午12:03:31
 * @version 1.0 Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 */
public class OnlineSystemUserCache {

	private static Logger log = LoggerFactory
			.getLogger(OnlineSystemUserCache.class);

	private final static String ONLINE_CACHE_NAME = "online_user_cache";
	private static Cache cache = CacheManager.getInstance().getCache(
			ONLINE_CACHE_NAME);

	public static void put(SystemUser user) {
		if (user != null && user.getUsername() != null) {
			Element element = new Element(user.getUsername(), user);
			cache.put(element);
			log.info("{} have sign in.", user.getUsername());
		}
	}

	public static SystemUser get(String username) {
		Element element = null;
		try {
			element = cache.get(username);
		} catch (CacheException ex) {
			throw new DataRetrievalFailureException("UserCache failure: "
					+ ex.getMessage(), ex);
		}
		if (element == null) {
			return null;
		} else {
			return (SystemUser) element.getValue();
		}
	}

	public static void remove(String username) {
		if (username != null) {
			cache.remove(username);
			log.info("{} have login out.", username);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<SystemUser> getOnlineUsers() {
		List<String> usernames;
		List<SystemUser> userlist = new ArrayList<SystemUser>();
		try {
			usernames = cache.getKeys();
		} catch (IllegalStateException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} catch (CacheException e) {
			throw new UnsupportedOperationException(e.getMessage(), e);
		}
		for (String username : usernames) {
			SystemUser user = get(username);
			userlist.add(user);
		}
		return userlist;
	}

}
