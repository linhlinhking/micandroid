package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.tuan.MenuItem;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.MenuItemDao;

@Repository("menuItemDao")
public class MenuItemDaoImpl extends BaseDaoImpl<MenuItem, Integer> implements
		MenuItemDao {

	public MenuItemDaoImpl() {
		super(MenuItem.class);
	}
}
