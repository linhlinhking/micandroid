package ningbo.media.service;

import java.util.Date;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Location;
import ningbo.media.bean.Rate;
import ningbo.media.bean.SystemUser;
import ningbo.media.rest.util.Constant;

import org.junit.Test;

public class RateServiceTest extends BaseTest{
	
	@Resource
	private RateService rateService ;
	@Resource
	private SystemUserService systemUserService ;
	
	@Resource
	private LocationService locationService ;
	
	@Test
	public void testAddRate(){
		Rate r = rateService.get(4) ;
		SystemUser u = systemUserService.get(Constant.MD5_FIELD,"eccbc87e4b5ce2fe28308fd9f2a7baf3");
		Location l = locationService.get(Constant.MD5_FIELD,"c4ca4238a0b923820dcc509a6f75849b") ;
		r.setSystemUser(u) ;
		r.setLocation(l) ;
		r.setOverAll(3) ;
		r.setRank1(2) ;
		r.setRank2(1) ;
		r.setRank3(2) ;
		r.setCreateTime(new Date()) ;
		
		rateService.save(r) ;
		
		System.out.println("OK"); 
		
	}
}
