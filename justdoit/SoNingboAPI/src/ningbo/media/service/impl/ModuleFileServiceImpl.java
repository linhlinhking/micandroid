package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.bean.ModuleFile;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.ModuleFileDao;
import ningbo.media.service.ModuleFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("moduleFileServie")
public class ModuleFileServiceImpl extends BaseServiceImpl<ModuleFile, Integer>
		implements ModuleFileService {

	@Resource
	private ModuleFileDao moduleFileDao;
	

	@Autowired
	public ModuleFileServiceImpl(@Qualifier("moduleFileDao")
	ModuleFileDao moduleFileDao) {
		super(moduleFileDao);
	}

	
	public List<ModuleFile> queryModuleFileByType(Integer userId,
			Integer toolId, Integer typeId) {
		final String hql = "from ModuleFile as m where 1=1 and m.tools.id in (select id from Tools as t inner join SystemUser as u where 1=1 and u.id = ? and t.id = ?) and m.moduleType.id = ? ";
		List<ModuleFile> list = moduleFileDao.findByHql(hql, userId, toolId,
				typeId);
		if (null != list && list.size() > 0) {
			return list;
		}
		return null;
	}
	
}
