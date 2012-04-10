package ningbo.media.service.impl;

import ningbo.media.bean.Tools;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.ToolsDao;
import ningbo.media.service.ToolsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("toolsService")
public class ToolsServiceImpl extends BaseServiceImpl<Tools, Integer> implements
		ToolsService {

	@Autowired
	public ToolsServiceImpl(@Qualifier("toolsDao")
	ToolsDao toolsDao) {
		super(toolsDao);
	}
}
