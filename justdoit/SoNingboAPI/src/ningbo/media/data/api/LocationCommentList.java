package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ningbo.media.data.entity.LocationCommentData;

@XmlRootElement
public class LocationCommentList {

	private String locationId;

	private Collection<LocationCommentData> data;

	
	public LocationCommentList(){}
	

	public LocationCommentList(Collection<LocationCommentData> data,String locationId){
		this.data = data ;
		this.locationId = locationId ;
	}
	
	
	@XmlElement(name = "locationId")
	public String getLocationId() {
		return locationId;
	}

	@XmlElement(name = "data")
	public Collection<LocationCommentData> getData() {
		return data;
	}

	
}
