package ningbo.media.admin.vo;

import java.util.List;

public class LocationEditListVO {

	private List<LocationEditVO> locationEdits ;
	
	private Integer pageSize ;
	
	private Integer pageNumber ;

	public List<LocationEditVO> getLocationEdits() {
		return locationEdits;
	}

	public void setLocationEdits(List<LocationEditVO> locationEdits) {
		this.locationEdits = locationEdits;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
}
