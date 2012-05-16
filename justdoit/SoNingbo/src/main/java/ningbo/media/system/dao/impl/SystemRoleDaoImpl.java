package ningbo.media.system.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.entity.SystemRole;
import ningbo.media.system.dao.SystemRoleDao;

@Repository("systemRoleDao")
public class SystemRoleDaoImpl extends BaseDaoImpl<SystemRole, Long> implements SystemRoleDao {

	public SystemRoleDaoImpl() {
		super(SystemRole.class);
	}
}
