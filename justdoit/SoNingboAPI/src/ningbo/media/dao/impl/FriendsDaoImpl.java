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

	private static final String hql = "from Friends as m where 1=1 and m.userId = ? and m.followId = ? ";

	public FriendsDaoImpl() {
		super(Friends.class);
	}

	public boolean isExistsRelation(Integer userA, Integer userB) {
		boolean flag = false;
		Friends f = getRelationObject(userA, userB);
		if (f != null) {
			flag = true;
		}
		return flag;
	}

	public Friends getRelationObject(Integer userA, Integer userB) {
		Friends f = null;
		try {
			f = (Friends) this.findUnique(hql, userA, userB);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return f;
	}

	public List<Friends> getFriendsForUserId(Integer userId, FriendType type) {
		try {
			String hql = "";
			if (FriendType.FANS.equals(type)) {
				hql = "from Friends as m where 1=1 and m.userId = ? and m.followId in (select n.userId from Friends as n where 1=1 and n.followId = ? and n.isFollowed = 1 ) and m.isFollowed = 0 ";
			} else if (FriendType.FOLLOWED.equals(type)) {
				hql = "from Friends as m where 1=1 and m.userId = ? and m.followId in (select n.userId from Friends as n where 1=1 and n.followId = ? and n.isFollowed = 0 ) and m.isFollowed = 1 ";
			} else {
				hql = " from Friends as m where 1=1 and m.userId = ? and m.followId in (select n.userId from Friends as n where 1=1 and n.followId = ? and n.isFollowed = 1 ) and m.isFollowed = 1 ";
			}

			List<Friends> list = findByHql(hql, userId, userId);
			if (null == list || list.size() < 0) {
				return null;
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
