package ningbo.media.service.impl;

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

	@Autowired
	public ModuleFileServiceImpl(@Qualifier("moduleFileDao")
	ModuleFileDao moduleFileDao) {
		super(moduleFileDao);
	}
}
