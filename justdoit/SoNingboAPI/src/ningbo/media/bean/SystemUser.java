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

@Entity
@Table(name = "users")
@XmlRootElement
public class SystemUser implements Serializable {

	private static final long serialVersionUID = -4367047739963786995L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//用户名
	private String username; 
	//密码
	private String password;
	//真实姓名
	private String name_cn;
	//电子邮箱
	private String email;
	//是否已经通过验证（新注册的用户上否已经激活）
	private boolean status;
	//用户头像
	private String photo_path;
	//性别
	private boolean gender;
	//出生日期
	private Date birthday;
	//是否是管理员
	@Column(name="manager")
	private Boolean isManager;
	
	@Column(name = "userKey")
	private String userKey ;
	
	//注册日期
	@Column(name="date_time")
	private Date datetime;
	
	@Column(name="last_modify_time")
	private Date lastModifyTime ;
	

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "systemUsers")
	private List<ModuleFile> moduleFiles ;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "systemUser")
	private List<Comment> comments ;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "in_favorite_user", joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns=@JoinColumn(name = "locationId"))
	private List<Location> locations ;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "systemUsers")
	private List<TempUser> tempUsers ;
	
	public SystemUser(){}
	

	@XmlTransient
	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	@XmlTransient
	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	@XmlTransient
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@XmlTransient
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	@XmlTransient
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	public Date getLastModifyTime() {
		return lastModifyTime;
	}


	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
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


	public String toString(){
		return "SystemUser[username:"+this.username+" ->password:"+this.password+"]" ;
	}

	


	@XmlTransient
	public List<TempUser> getTempUsers() {
		return tempUsers;
	}


	public void setTempUsers(List<TempUser> tempUsers) {
		this.tempUsers = tempUsers;
	}


	@XmlTransient
	public List<Location> getLocations() {
		return locations;
	}


	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}


	
}
