package ningbo.media.dao;

import ningbo.media.bean.UserModuleFiles;
import ningbo.media.core.dao.BaseDao;

public interface UserModuleFilesDao extends BaseDao<UserModuleFiles, Integer>{

	public UserModuleFiles getUserModuleFilesByUserId(Integer fileId,String md5Value) ;
}
