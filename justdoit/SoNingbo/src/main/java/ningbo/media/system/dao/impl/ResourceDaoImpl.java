package ningbo.media.system.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.entity.Resource;
import ningbo.media.system.dao.ResourceDao;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource, Long> implements ResourceDao{

	public ResourceDaoImpl(){
		super(Resource.class) ;
	}
}
