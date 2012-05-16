package ningbo.media.system.service.impl;

import java.util.Collection;

import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.entity.SystemRole;
import ningbo.media.system.dao.SystemRoleDao;
import ningbo.media.system.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("systemRoleService")
public class SystemRoleServiceImpl extends BaseServiceImpl<SystemRole, Long> implements SystemRoleService{

	@Autowired
	public SystemRoleServiceImpl(@Qualifier("systemRoleDao")SystemRoleDao systemRoleDao){
		super(systemRoleDao) ;
	}

	public Collection<SystemRole> getRolesByUserName(String username) {
		return null;
	}
}
