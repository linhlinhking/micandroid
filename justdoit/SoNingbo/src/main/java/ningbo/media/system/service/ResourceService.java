package ningbo.media.system.service;

import java.util.Collection;

import ningbo.media.core.service.BaseService;
import ningbo.media.entity.Resource;

public interface ResourceService extends BaseService<Resource, Long> {

	public Collection<Resource> getResourcesByUserName(String username);
}
