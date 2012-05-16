package ningbo.media.core.security.cache;

import java.util.List;
import ningbo.media.entity.Resource;
import ningbo.media.system.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:初始化权限缓存。
 * 
 * @author Devon.Ning
 * @2012-4-18下午03:25:46
 * @version 1.0 Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 */
public class SecurityCacheInit {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@javax.annotation.Resource
	private ResourceService resourceService;

	public void init() {
		logger.info("Init Cache Beginning...");
		List<Resource> res = resourceService.getAll();
		for (Resource r : res) {
			SecurityResourceCache.put(r);
		}
		logger.info("Init Cache Finish.");
	}
}
