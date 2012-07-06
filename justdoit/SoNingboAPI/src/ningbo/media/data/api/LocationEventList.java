package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.data.entity.LocationEventData;

@XmlType(name = "",propOrder = {"locationMd5","data"})
@XmlRootElement
public class LocationEventList {

	private String locationMd5;

	private Collection<LocationEventData> data;

	public LocationEventList() {
	}

	public LocationEventList(String locationMd5,
			Collection<LocationEventData> data) {
		this.locationMd5 = locationMd5;
		this.data = data;
	}

	@XmlElement
	public String getLocationMd5() {
		return locationMd5;
	}

	@XmlElement(name = "events",defaultValue = "null")
	public Collection<LocationEventData> getData() {
		return data;
	}

}
