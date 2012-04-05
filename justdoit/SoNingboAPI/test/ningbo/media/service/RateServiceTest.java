package ningbo.media.service;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Rate;

import org.junit.Test;

public class RateServiceTest extends BaseTest{

	@Resource
	private RateService rateService ;
	
	@Test
	public void testGetRest(){
		Rate r = rateService.getRateByIds(27, 17) ;
		System.out.println(r) ;
	}
}
