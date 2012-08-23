package ningbo.media.data.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.data.entity.LocationEventData;

@XmlType(name = "", propOrder = { "locationMd5", "name_en", "name_cn",
		"latitude", "longitude", "data" })
@XmlRootElement
public class LocationEventList {

	private String locationMd5;

	private String name_en;

	private String name_cn;

	private Double latitude;

	private Double longitude;

	private Collection<LocationEventData> data;

	public LocationEventList() {

	}

	public LocationEventList(String locationMd5,
			Collection<LocationEventData> data) {
		this.locationMd5 = locationMd5;
		this.data = data;
	}

	public LocationEventList(String locationMd5, String name_en,
			String name_cn, Double latitude, Double longitude,
			Collection<LocationEventData> data) {
		this.locationMd5 = locationMd5;
		this.name_en = name_en;
		this.name_cn = name_cn;
		this.latitude = latitude;
		this.longitude = longitude;
		this.data = data;
	}

	@XmlElement
	public String getName_en() {
		return name_en;
	}

	@XmlElement
	public String getName_cn() {
		return name_cn;
	}

	@XmlElement
	public Double getLatitude() {
		return latitude;
	}

	@XmlElement
	public Double getLongitude() {
		return longitude;
	}

	@XmlElement
	public String getLocationMd5() {
		return locationMd5;
	}

	@XmlElement(name = "events", defaultValue = "null")
	public Collection<LocationEventData> getData() {
		return data;
	}

	public void setLocationMd5(String locationMd5) {
		this.locationMd5 = locationMd5;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setData(Collection<LocationEventData> data) {
		this.data = data;
	}
	
	

}
