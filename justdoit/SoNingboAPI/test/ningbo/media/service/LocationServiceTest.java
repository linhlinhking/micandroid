package ningbo.media.service;


import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;

import org.junit.Test;
import ningbo.media.bean.Location ;
public class LocationServiceTest extends BaseTest {

	@Resource
	private LocationService locationService;

	
	public void testGetAllCagegoryName() {
		Long lon = locationService.getTotalCount() ;
		System.out.println(lon) ;
	}
	
	@Test
	public void testLocationPage() {
		List<Location> lon = locationService.queryLocationByPage(767, 20) ;
		for(Location l : lon){
			System.out.println(l.getAddress_en()) ;
		}
	}
	
}
