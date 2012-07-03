package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;
import ningbo.media.bean.UserModuleFiles;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.UserModuleFilesDao;

@Repository("userModuleFilesDao")
public class UserModuleFilesDaoImpl extends
		BaseDaoImpl<UserModuleFiles, Integer> implements UserModuleFilesDao {

	public UserModuleFilesDaoImpl(){
		super(UserModuleFiles.class);
	}
}
