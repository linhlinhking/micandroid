package ningbo.media.dao.impl;

import javax.annotation.Resource;

import ningbo.media.bean.ImageInformation;
import ningbo.media.bean.ModuleFile;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.ImageInformationDao;
import ningbo.media.dao.ModuleFileDao;

import org.springframework.stereotype.Repository;

@Repository("moduleFileDao")
public class ModuleFileDaoImpl extends BaseDaoImpl<ModuleFile, Integer>
		implements ModuleFileDao {
	
	@Resource
	private ImageInformationDao imageInformationDao ;

	public ModuleFileDaoImpl() {
		super(ModuleFile.class);
	}

	public boolean deleteModuleFileByUserId(Integer userId, Integer fileId) {
		boolean flag = false ;
		ModuleFile tempFile = get(fileId) ;
		if(null == tempFile){
			return flag ;
		}
		
		ImageInformation infor = tempFile.getImageInfo() ;
		imageInformationDao.delete(infor);
		
		
		
		String hql = "from ModuleFile model where 1=1 and model.uploaderId = ? and model.fileId = ? ";
		
		
		
		
		return false;
	}
}
