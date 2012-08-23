package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.tuan.GroupBuy;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.GroupBuyDao;

@Repository("groupBuyDao")
public class GroupBuyDaoImpl extends BaseDaoImpl<GroupBuy, Integer> implements GroupBuyDao {

	public GroupBuyDaoImpl(){
		super(GroupBuy.class) ;
	}
	
}
