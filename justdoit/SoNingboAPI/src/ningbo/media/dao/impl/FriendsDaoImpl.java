package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.Friends;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FriendsDao;

@Repository("friendsDao")
public class FriendsDaoImpl extends BaseDaoImpl<Friends, Integer> implements
		FriendsDao {

	private static final String hql = "from Friends as m where 1=1 and m.userId = ? and m.followId = ? " ;
	
	public FriendsDaoImpl(){
		super(Friends.class) ;
	}

	public boolean isExistsRelation(Integer userA, Integer userB) {
		boolean flag = false ;
		Friends f = getRelationObject(userA, userB) ;
		if(f != null){
			flag = true ;
		}
		return flag;
	}
	
	
	public Friends getRelationObject(Integer userA,Integer userB){
		Friends f = null ;
		try{
			f = (Friends)this.findUnique(hql, userA,userB) ;
		}catch(Exception ex){
			ex.printStackTrace() ;
		}
		return f ;
	}
	
	
	
	
}
