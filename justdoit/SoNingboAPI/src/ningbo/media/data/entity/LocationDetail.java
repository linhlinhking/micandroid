package ningbo.media.data.entity;

import javax.xml.bind.annotation.XmlType;

@XmlType(name="location",propOrder={"id","name","name_en","address","address_en","latitude","longitude"})
public class LocationDetail {

	private Integer id ;
	
	private String name ;
	
	private String address; 
	
	private String name_en ;
	
	private String address_en ;
	
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

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getAddress_en() {
		return address_en;
	}

	public void setAddress_en(String address_en) {
		this.address_en = address_en;
	}

	
	
	
}
