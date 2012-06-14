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

	
	public void updateLocation() {
		List<Location> locations = locationService.getAll();
		
		int size = locations.size();
		for (int i = 0; i < size; i++) {
			Location loc = locations.get(i) ;
			String py = Pinyin.getPinYin(loc.getName_cn());
			loc.setName_py(py) ;
			locationService.update(loc) ;
		
		}
		System.out.println("finished") ;
	}
	//select location0_.id as id7_, location0_.address_cn as address2_7_, location0_.address_en as address3_7_, location0_.description_cn as descript4_7_, location0_.description_en as descript5_7_, location0_.latitude as latitude7_, location0_.longitude as longitude7_, location0_.md5_value as md8_7_, location0_.name_cn as name9_7_, location0_.name_en as name10_7_, location0_.name_py as name11_7_, location0_.photo_path as photo12_7_, location0_.telephone as telephone7_ from locations location0_ where 1=1 and location0_.latitude>29.8673665 and location0_.latitude<29.8683665 and location0_.longitude>121.5656315 and location0_.longitude<121.5666315
	@Test
	public void listLocations(){
		List<Location> lists = locationService.queryLoctionsByLat(29.8679775, 121.5450105);
		//System.out.println(lists.size()) ;
		for(Location loc : lists){
			System.out.println(loc.getAddress_cn());
		}
	}

}
