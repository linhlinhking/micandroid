package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.tuan.MenuTool;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.MenuToolDao;


@Repository("menuToolDao")
public class MenuToolDaoImpl extends BaseDaoImpl<MenuTool, Integer> implements
		MenuToolDao {

	public MenuToolDaoImpl(){
		super(MenuTool.class) ;
	}
}
