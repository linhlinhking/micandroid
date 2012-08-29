package ningbo.media.service;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.SystemUser;

import org.junit.Test;

public class SystemUserServiceTest extends BaseTest{
	
	@Resource
	private SystemUserService systemUserService ;
	

	public String testVerificationUser(){
		SystemUser u = new SystemUser() ;
		String username = "ningzhuping" ;
		String email = "leyxan@gmail.com" ;
		String password = "12345678" ;
		u.setUsername(username) ;
		u.setEmail(email) ;
		u.setPassword(password) ;
		return null ;
	}
	
	@Test
	public void testEmailIsUnique(){
		//String email = "leyxan@gmail.com" ;
		//System.out.println(systemUserService.isExist("email", email)) ;
		//SystemUser one = SystemUser.fromJson(testVerificationUser()) ;
		//systemUserService.save(one) ;
		//System.out.println("save success!") ;
	}

}
