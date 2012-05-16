package ningbo.media.system.service;

import java.util.Collection;

import ningbo.media.core.service.BaseService;
import ningbo.media.entity.SystemRole;

public interface SystemRoleService extends BaseService<SystemRole, Long> {

	public Collection<SystemRole> getRolesByUserName(String username) ;
}
