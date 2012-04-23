package ningbo.media.service.impl;

import ningbo.media.bean.tuan.MenuItem;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.MenuItemDao;
import ningbo.media.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("menuItemService")
public class MenuItemServiceImpl extends BaseServiceImpl<MenuItem, Integer>
		implements MenuItemService {

	@Autowired
	public MenuItemServiceImpl(@Qualifier("menuItemDao")
	MenuItemDao menuItemDao) {
		super(menuItemDao);
	}
}
