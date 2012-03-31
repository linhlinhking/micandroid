package ningbo.media.service;


import javax.annotation.Resource;

import ningbo.media.BaseTest;

import org.junit.Test;

public class LocationServiceTest extends BaseTest {

	@Resource
	private LocationService locationService;

	@Test
	public void testGetAllCagegoryName() {
		Long lon = locationService.getTotalCount() ;
		System.out.println(lon) ;
	}
}
