package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.ModuleFile;
import ningbo.media.core.service.BaseService;

public interface ModuleFileService extends BaseService<ModuleFile,Integer> {

	public List<ModuleFile> queryModuleFileByType(Integer userId,
			Integer toolId, Integer typeId) ;
	
}
