package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.Tools;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.ToolsDao;

@Repository("toolsDao")
public class ToolsDaoImpl extends BaseDaoImpl<Tools, Integer> implements
		ToolsDao {

	public ToolsDaoImpl(){
		super(Tools.class) ;
	}
}
