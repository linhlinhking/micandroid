package ningbo.media.bean;

import java.io.Serializable;
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
import javax.persistence.Table;


@Entity
@Table(name = "users_temp")
public class TempUser implements Serializable{
	
	private static final long serialVersionUID = 15423913474764901L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	@Column(name = "temp_name")
	private String tempName ;
	
	@Column(name = "device_id")
	private String deviceId;
	
	private boolean isUserd ;
	
	@Column(name = "temp_key")
	private String tempKey ;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "in_favorite_tempuser", joinColumns = @JoinColumn(name = "tempId"),inverseJoinColumns=@JoinColumn(name = "locationId"))
	private List<Location> locations ;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "ac_users_tempuser", joinColumns = @JoinColumn(name = "tempId"),inverseJoinColumns=@JoinColumn(name = "userId"))
	private List<SystemUser> systemUsers ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTempKey() {
		return tempKey;
	}

	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public boolean isUserd() {
		return isUserd;
	}

	public void setUserd(boolean isUserd) {
		this.isUserd = isUserd;
	}


	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(List<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}

	
	
}
