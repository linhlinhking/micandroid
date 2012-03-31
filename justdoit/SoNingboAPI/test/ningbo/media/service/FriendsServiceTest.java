package ningbo.media.service;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Friends;
import ningbo.media.bean.enums.FriendType;

import org.junit.Test;

public class FriendsServiceTest extends BaseTest{

	@Resource
	private FriendsService friendsService ;
	
	@Test
	public void testExists(){
		//boolean flag = friendsService.isExistsRelation(27, 29) ;
		Friends flag = friendsService.getRelationObject(27, 28) ;
		System.out.println(flag) ;
		System.out.println(FriendType.FOLLOWED.getValue()) ;
	}
}
