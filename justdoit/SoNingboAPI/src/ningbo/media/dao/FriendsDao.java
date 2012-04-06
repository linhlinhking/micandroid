package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.Friends;
import ningbo.media.core.dao.BaseDao;

public interface FriendsDao extends BaseDao<Friends, Integer>{

	public boolean isExistsRelation(Integer userA,Integer userB) ;
	
	public Friends getRelationObject(Integer userA,Integer userB) ;
	
	public List<Friends> getFriendsForUserId(Integer userId);
}
