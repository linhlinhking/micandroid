package ningbo.media.admin.vo;

import java.util.List;

import ningbo.media.bean.Location;

public class LocationListVO {

	private List<Location> locations ;
	
	private Integer pageNumber ;
	
	private Integer pageSize ;



	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
