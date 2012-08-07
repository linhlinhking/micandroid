package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ningbo.media.data.entity.LocationCommentData;
import ningbo.media.data.entity.LocationDetail;

@XmlRootElement
public class LocationCommentList {

	private LocationDetail locationData;

	private Collection<LocationCommentData> data;

	
	public LocationCommentList(){}
	

	public LocationCommentList(Collection<LocationCommentData> data,LocationDetail locationData){
		this.data = data ;
		this.locationData = locationData ;
	}
	
	
	@XmlElement(name = "locationData")
	public LocationDetail getLocationDetail() {
		return locationData;
	}

	@XmlElement(name = "data")
	public Collection<LocationCommentData> getData() {
		return data;
	}

	
}
