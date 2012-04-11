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
	
	
	
	public void testLogin(){
		Integer u = systemUserService.login("leyxan.nb@qq.com", "123") ;
		System.out.println(u) ;
	}
	
	@Test
	public void testIsContainTool(){
		boolean flag = systemUserService.isContainTool(1) ;
		System.out.println(flag) ;
	}

}
