package ningbo.media.service;

import java.util.List;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.ModuleFile;
import ningbo.media.core.service.BaseService;
import ningbo.media.rest.dto.ModuleFileData;

public interface ModuleFileService extends BaseService<ModuleFile,Integer> {

	public List<ModuleFileData> queryModuleFileByUserHeader(Integer userId) ;
	
	public List<ModuleFileData> queryModuleFileByLocation(String md5_id) ;
	
	public List<ModuleFileData> queryAllFile();
	
	public ModuleFileData getModuleFileById(Integer fileId);
	
	public boolean deleteModuleFileByUserId(Integer userId,String locationMd5);
	
	public boolean deleteModuleFile(Integer id);
	
	public List<ModuleFile> queryFilesByEventId(Integer eventId) throws ServiceException;
	
}
