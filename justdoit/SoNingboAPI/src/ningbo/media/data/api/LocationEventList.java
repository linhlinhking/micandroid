package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.data.entity.LocationEventData;

@XmlType(name = "",propOrder = {"locationId","data"})
@XmlRootElement
public class LocationEventList {

	private Integer locationId;

	private Collection<LocationEventData> data;

	public LocationEventList() {
	}

	public LocationEventList(Integer locationId,
			Collection<LocationEventData> data) {
		this.locationId = locationId;
		this.data = data;
	}

	@XmlElement
	public Integer getLocationId() {
		return locationId;
	}

	@XmlElement(name = "events",defaultValue = "null")
	public Collection<LocationEventData> getData() {
		return data;
	}

}
