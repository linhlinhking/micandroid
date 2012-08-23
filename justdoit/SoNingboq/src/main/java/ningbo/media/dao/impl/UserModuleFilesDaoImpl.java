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


	public UserModuleFiles getUserModuleFilesByUserId(Integer fileId,
			String md5Value) {
		try{
			String hql = "from UserModuleFiles as m where 1=1 and m.fileId = ? and m.md5Value = ? " ;
			UserModuleFiles temp = (UserModuleFiles)findUnique(hql, fileId,md5Value) ;
			if(null != temp){
				return temp ;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null ;
	}
}
