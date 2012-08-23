package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ningbo.media.data.entity.LocationData;

@XmlRootElement(name = "locations")
public class LocationList {

	private Collection<LocationData> locations ;
	
	public LocationList(){}
	
	public LocationList(Collection<LocationData> locations){
		this.locations = locations ;
	}
	
	@XmlElement(name = "location")
	public Collection<LocationData> getData(){
		return locations ;
	}
}
