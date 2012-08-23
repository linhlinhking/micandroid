package ningbo.media.service.impl;

import ningbo.media.bean.ModuleType;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.ModuleTypeDao;
import ningbo.media.service.ModuleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("moduleTypeService")
public class ModuleTypeServiceImpl extends BaseServiceImpl<ModuleType, Integer>
		implements ModuleTypeService {

	@Autowired
	public ModuleTypeServiceImpl(@Qualifier("moduleTypeDao")
	ModuleTypeDao moduleTypeDao) {
		super(moduleTypeDao);
	}
}
