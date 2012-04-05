package ningbo.media.data.entity;

import javax.xml.bind.annotation.XmlType;

@XmlType(name="location",propOrder={"id","name","address","latitude","longitude"})
public class LocationDetail {

	private Integer id ;
	
	private String name ;
	
	private String address; 
	
	private Double latitude ;
	
	private Double longitude ;
	
	public LocationDetail(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	
}
