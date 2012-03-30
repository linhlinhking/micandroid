package ningbo.media.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "locations")
@XmlRootElement
public class Location implements Serializable {

	private static final long serialVersionUID = 5398023012055824282L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name_en;

	private String name_cn;

	private String address_en;

	private String address_cn;

	private String telephone;

	private Double longitude;

	private Double latitude;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "location")
	private List<Event> events ;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "locations_category", joinColumns = @JoinColumn(name = "location_id"),inverseJoinColumns=@JoinColumn(name = "category2_id"))
	private List<SecondCategory> secondCategorys;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "location")
	private List<ImageFile> imageFiles ;

	
	public Location() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public String getAddress_en() {
		return address_en;
	}

	public void setAddress_en(String address_en) {
		this.address_en = address_en;
	}

	public String getAddress_cn() {
		return address_cn;
	}

	public void setAddress_cn(String address_cn) {
		this.address_cn = address_cn;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	

	@XmlTransient
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@XmlTransient
	public List<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}

	public void setSecondCategorys(List<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}

	@XmlTransient
	public List<ImageFile> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(List<ImageFile> imageFiles) {
		this.imageFiles = imageFiles;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name_en=" + name_en + ", name_cn="
				+ name_cn + ", address_en=" + address_en + ", address_cn="
				+ address_cn + ", telephone=" + telephone + ", longitude="
				+ longitude + ", latitude=" + latitude + "]";
	}

}
