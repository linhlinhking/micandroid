package ningbo.media.service;

import ningbo.media.bean.Friends;
import ningbo.media.core.service.BaseService;

public interface FriendsService extends BaseService<Friends, Integer>{

	public boolean isExistsRelation(Integer userA,Integer userB) ;
	
	public Friends getRelationObject(Integer userA,Integer userB) ;
}
