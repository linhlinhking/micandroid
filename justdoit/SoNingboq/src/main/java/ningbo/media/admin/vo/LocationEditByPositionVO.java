package ningbo.media.admin.vo;

import java.util.List;

import ningbo.media.bean.Location;

public class LocationEditByPositionVO {

	private Location location ;
	
	private  List<LocationEditVO> locationEdits ;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<LocationEditVO> getLocationEdits() {
		return locationEdits;
	}

	public void setLocationEdits(List<LocationEditVO> locationEdits) {
		this.locationEdits = locationEdits;
	}
	
	
}
