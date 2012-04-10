package ningbo.media.dao.impl;


import org.springframework.stereotype.Repository;
import ningbo.media.bean.ModuleType;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.ModuleTypeDao;

@Repository("moduleTypeDao")
public class ModuleTypeDaoImpl extends BaseDaoImpl<ModuleType, Integer>
		implements ModuleTypeDao {

	public ModuleTypeDaoImpl(){
		super(ModuleType.class) ;
	}
}
