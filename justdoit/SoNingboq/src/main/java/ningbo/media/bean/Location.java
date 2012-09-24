package ningbo.media.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "locations")
@XmlRootElement
public class Location implements Serializable {

	private static final long serialVersionUID = 5398023012055824282L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name_en",nullable=false)
	private String name_en;

	@Column(name="name_cn",nullable=false)
	private String name_cn;

	@Column(name="name_py")
	private String name_py ;
	
	@Column(name="md5_value")
	private String md5Value ;
	
	@Column(name="address_en")
	private String address_en;

	@Column(name="address_cn")
	private String address_cn;
	
	@Column(name="telephone")
	private String telephone;

	@Column(name="longitude")
	private Double longitude;

	@Column(name="latitude")
	private Double latitude;
	
	@Column(name = "tags_cn")
	private String tags_cn ;
	
	@Column(name="tags_en")
	private String tags_en ;
	
	@Column(name="photo_path")
	private String photo_path;
	
	@Column(name="description_en")
	private String description_en ;
	
	@Column(name="description_cn")
	private String description_cn ;
	
	private boolean isAppoval ;
	
	@Column(length=128)
	private String phoneNumberOne ;
	
	@Column(length=128)
	private String phoneNumberTwo ;
	
	@Column(length=128)
	private String phoneNumberThree ;
	
	private Date createDateTime ;
	
	private Date updateDateTime ;
	
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "locations_category", joinColumns = @JoinColumn(name = "location_id"),inverseJoinColumns=@JoinColumn(name = "category2_id"))
	private List<SecondCategory> secondCategorys;
	
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "location")
	private List<Comment> comments ;
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "locations")
	private List<ModuleFile> moduleFiles ;
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "locations")
	private List<SystemUser> systemUsers ;
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "locations")
	private List<TempUser> tempUsers ;
	
	
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
	public List<SecondCategory> getSecondCategorys() {
		return secondCategorys;
	}

	public void setSecondCategorys(List<SecondCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
	}

	@XmlTransient
	public List<ModuleFile> getModuleFiles() {
		return moduleFiles;
	}

	public void setModuleFiles(List<ModuleFile> moduleFiles) {
		this.moduleFiles = moduleFiles;
	}
	
	@XmlTransient
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	@XmlTransient
	public List<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(List<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}
	
	@XmlTransient
	public List<TempUser> getTempUsers() {
		return tempUsers;
	}

	public void setTempUsers(List<TempUser> tempUsers) {
		this.tempUsers = tempUsers;
	}


	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public String getName_py() {
		return name_py;
	}

	public void setName_py(String name_py) {
		this.name_py = name_py;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}


	public void setDescription_cn(String description_cn) {
		this.description_cn = description_cn;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getDescription_cn() {
		return description_cn;
	}

	public String getTags_cn() {
		return tags_cn;
	}

	public void setTags_cn(String tags_cn) {
		this.tags_cn = tags_cn;
	}

	public String getTags_en() {
		return tags_en;
	}

	public void setTags_en(String tags_en) {
		this.tags_en = tags_en;
	}

	public boolean isAppoval() {
		return isAppoval;
	}

	public void setAppoval(boolean isAppoval) {
		this.isAppoval = isAppoval;
	}

	public String getPhoneNumberOne() {
		return phoneNumberOne;
	}

	public void setPhoneNumberOne(String phoneNumberOne) {
		this.phoneNumberOne = phoneNumberOne;
	}

	public String getPhoneNumberTwo() {
		return phoneNumberTwo;
	}

	public void setPhoneNumberTwo(String phoneNumberTwo) {
		this.phoneNumberTwo = phoneNumberTwo;
	}

	public String getPhoneNumberThree() {
		return phoneNumberThree;
	}

	public void setPhoneNumberThree(String phoneNumberThree) {
		this.phoneNumberThree = phoneNumberThree;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	

	
}
