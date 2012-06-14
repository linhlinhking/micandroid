package ningbo.media.dao;

import ningbo.media.bean.ModuleFile;
import ningbo.media.core.dao.BaseDao;

public interface ModuleFileDao extends BaseDao<ModuleFile, Integer>{

	public boolean deleteModuleFileByUserId(Integer userId, Integer fileId) ;
}
