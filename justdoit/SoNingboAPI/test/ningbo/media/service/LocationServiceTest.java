package ningbo.media.service;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Location;
import ningbo.media.util.Pinyin;

import org.junit.Test;

public class LocationServiceTest extends BaseTest {

	@Resource
	private LocationService locationService;

	@Test
	public void updateLocation() {
		List<Location> locations = locationService.getAll();
		System.out.println(locations.size());
		for (int i = 0; i < 2; i++) {
			Location loc = locations.get(i) ;
			String py = Pinyin.getPinYin(loc.getName_cn());
			loc.setName_py(py) ;
			locationService.update(loc) ;
			//System.out.println(loc.getName_cn());
			//System.out.println(py);
			
		}
		System.out.println("finished") ;
	}

}
