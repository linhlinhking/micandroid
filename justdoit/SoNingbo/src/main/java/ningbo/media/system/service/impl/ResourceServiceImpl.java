package ningbo.media.system.service.impl;

import java.util.Collection;

import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.entity.Resource;
import ningbo.media.system.dao.ResourceDao;
import ningbo.media.system.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long> implements ResourceService{

	
	@Autowired
	public ResourceServiceImpl(@Qualifier("resourceDao")ResourceDao resourceDao){
		super(resourceDao) ;
	}

	public Collection<Resource> getResourcesByUserName(String username) {
		return null;
	}
	
	
}
