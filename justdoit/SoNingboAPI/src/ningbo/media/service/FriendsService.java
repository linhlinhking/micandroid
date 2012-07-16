package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Friends;
import ningbo.media.bean.enums.FriendType;
import ningbo.media.core.service.BaseService;

public interface FriendsService extends BaseService<Friends, Integer>{

	public boolean isExistsRelation(Integer userA,Integer userB) ;
	
	public Friends getRelationObject(Integer userA,Integer userB) ;
	
	public List<Friends> getFriendsForUserId(Integer userId,FriendType type);
}
