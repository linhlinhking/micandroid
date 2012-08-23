package ningbo.media.service.impl;

import ningbo.media.bean.tuan.GroupBuy;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.GroupBuyDao;
import ningbo.media.service.GroupBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("groupBuyService")
public class GroupBuyServiceImpl extends BaseServiceImpl<GroupBuy, Integer>
		implements GroupBuyService {

	@Autowired
	public GroupBuyServiceImpl(@Qualifier("groupBuyDao")
	GroupBuyDao groupBuyDao) {
		super(groupBuyDao);
	}
}
