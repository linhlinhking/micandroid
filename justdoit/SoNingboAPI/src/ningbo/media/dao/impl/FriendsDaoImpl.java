package ningbo.media.dao.impl;

import java.util.List;

import ningbo.media.bean.Friends;
import ningbo.media.bean.enums.FriendType;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FriendsDao;

import org.springframework.stereotype.Repository;

@Repository("friendsDao")
public class FriendsDaoImpl extends BaseDaoImpl<Friends, Integer> implements
		FriendsDao {


	public FriendsDaoImpl() {
		super(Friends.class);
	}

	public boolean isExistsRelation(String userA, String userB) {
		boolean flag = false;
		Friends f = getRelationObject(userA, userB);
		if (f != null) {
			flag = true;
		}
		return flag;
	}

	public Friends getRelationObject(String userA, String userB) {
		final String hql = "from Friends as m where 1=1 and m.userId = ? and m.followId = ? ";
		Friends f = null;
		try {
			f = (Friends) this.findUnique(hql, userA, userB);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return f;
	}

	public List<Friends> getFriendsForUserId(String userId, FriendType type) {
		try {
			String hql = "";
			List<Friends> list = null ;
			if (FriendType.FANS.equals(type)) {
				hql = " from Friends as m where 1=1 and m.userId = ? and m.followId in (select n.userId from Friends as n where 1=1 and n.followId = ? and n.isFollowed = 1 ) and m.isFollowed = 0 ";
				list = findByHql(hql, userId,userId);
			} else if (FriendType.FOLLOWED.equals(type)) {
				hql = " from Friends as m where 1=1 and m.userId = ? and m.isFollowed = 1 ";
				list = findByHql(hql, userId);
			} else {
				hql = " from Friends as m where 1=1 and m.userId = ? and m.followId in (select n.userId from Friends as n where 1=1 and n.followId = ? and n.isFollowed = 1 ) and m.isFollowed = 1 ";
				list = findByHql(hql, userId, userId);
			}

			
			if (null == list || list.size() < 0) {
				return null;
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	
	public long getFollowedNumber(String userId) {
		String hql = " from Friends as m where 1=1 and m.userId = ? and m.followId in (select n.userId from Friends as n where 1=1 and n.followId = ? and n.isFollowed = 1 ) and m.isFollowed = 0 ";
		List<Friends> list = findByHql(hql, userId,userId);
		if(null == list || list.size() < 0){
			return 0 ;
		}
		long num = list.size() ;
		return num ;
	}

	
	public long getFollowingNumber(String userId) {
		String hql = " from Friends as m where 1=1 and m.userId = ? and m.isFollowed = 1 ";
		List<Friends> list = findByHql(hql, userId);
		if(null == list || list.size() < 0){
			return 0 ;
		}
		long num = list.size() ;
		return num ;
	}

}
