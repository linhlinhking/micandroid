package ningbo.media.service.impl;

import ningbo.media.bean.tuan.MenuTool;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.MenuToolDao;
import ningbo.media.service.MenuToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("menuToolService")
public class MenuToolServiceImpl extends BaseServiceImpl<MenuTool, Integer>
		implements MenuToolService {

	@Autowired
	public MenuToolServiceImpl(@Qualifier("menuToolDao")
	MenuToolDao menuToolDao) {
		super(menuToolDao);
	}
}
