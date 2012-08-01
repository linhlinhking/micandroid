package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.Friends;
import ningbo.media.bean.enums.FriendType;
import ningbo.media.core.dao.BaseDao;

public interface FriendsDao extends BaseDao<Friends, Integer>{

	public boolean isExistsRelation(String userA,String userB) ;
	
	public Friends getRelationObject(String userA,String userB) ;
	
	public List<Friends> getFriendsForUserId(String userId,FriendType type);
	
	public long getFollowingNumber(String userId);
	
	public long getFollowedNumber(String userId);
}
