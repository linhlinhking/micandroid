package ningbo.media.service.impl;

import ningbo.media.bean.UserModuleFiles;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.UserModuleFilesDao;
import ningbo.media.service.UserModuleFilesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userModuleFilesService")
public class UserModuleFilesServiceImpl extends
		BaseServiceImpl<UserModuleFiles, Integer> implements
		UserModuleFilesService {

	@Autowired
	public UserModuleFilesServiceImpl(@Qualifier("userModuleFilesDao")
	UserModuleFilesDao userModuleFilesDao) {
		super(userModuleFilesDao);
	}
}
