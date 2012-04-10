package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.ModuleFile;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.ModuleFileDao;

@Repository("moduleFileDao")
public class ModuleFileDaoImpl extends BaseDaoImpl<ModuleFile, Integer>
		implements ModuleFileDao {

	public ModuleFileDaoImpl(){
		super(ModuleFile.class) ;
	}
}
