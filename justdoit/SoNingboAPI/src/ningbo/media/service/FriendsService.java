package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Friends;
import ningbo.media.bean.enums.FriendType;
import ningbo.media.core.service.BaseService;

public interface FriendsService extends BaseService<Friends, Integer>{

	public boolean isExistsRelation(String userA,String userB) ;
	
	public Friends getRelationObject(String userA,String userB) ;
	
	public List<Friends> getFriendsForUserId(String userId,FriendType type);
}
