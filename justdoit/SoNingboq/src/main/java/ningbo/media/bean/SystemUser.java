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
@Table(name = "users")
@XmlRootElement
public class SystemUser implements Serializable {

	private static final long serialVersionUID = -4367047739963786995L;
	

	public static final String PASSWORD = "password" ;
	public static final String SECURITY_EMAIL = "securityEmail" ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//用户名
	private String username; 
	
	private String password;
	
	private String name_cn;
	
	private String name_en ;
	
	private String nickName ;
	
	private String email;
	
	private boolean status;
	
	private String photo_path;
	
	private String gender;
	
	private String intro ;
	
	@Column(name = "userKey")
	private String userKey ;
	
	@Column(length = 32,name="user_type")
	private String userType ;
	
	//注册日期
	@Column(name="date_time")
	private Date datetime;
	
	@Column(name="last_modify_time")
	private Date lastModifyTime ;
	
	@Column(name = "md5_value")
	private String md5Value ;
	
	private String website ;
	
	private String qq ;
	
	private String msn ;
	
	private boolean employeeIs ;
	
	@Column(name="security_email")
	private String securityEmail ;

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
	

	@JsonIgnore
	@XmlTransient
	public String getUserKey() {
		return userKey;
	}

	
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	@JsonIgnore
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

	@JsonIgnore
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
	
	


	
	public Date getLastModifyTime() {
		return lastModifyTime;
	}


	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	@JsonIgnore
	@XmlTransient
	public List<ModuleFile> getModuleFiles() {
		return moduleFiles;
	}


	public void setModuleFiles(List<ModuleFile> moduleFiles) {
		this.moduleFiles = moduleFiles;
	}
	
	@JsonIgnore
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

	

	@JsonIgnore
	@XmlTransient
	public List<TempUser> getTempUsers() {
		return tempUsers;
	}


	public void setTempUsers(List<TempUser> tempUsers) {
		this.tempUsers = tempUsers;
	}

	
	@JsonIgnore
	@XmlTransient
	public List<Location> getLocations() {
		return locations;
	}


	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}


	public String getMd5Value() {
		return md5Value;
	}


	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}


	public String getMsn() {
		return msn;
	}


	public void setMsn(String msn) {
		this.msn = msn;
	}


	public String getName_en() {
		return name_en;
	}


	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	@JsonIgnore
	public String getUserType() {
		return userType;
	}
	
	

	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getIntro() {
		return intro;
	}


	public void setIntro(String intro) {
		this.intro = intro;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonIgnore
	public String getSecurityEmail() {
		return securityEmail;
	}


	public void setSecurityEmail(String securityEmail) {
		this.securityEmail = securityEmail;
	}


	public boolean isEmployeeIs() {
		return employeeIs;
	}


	public void setEmployeeIs(boolean employeeIs) {
		this.employeeIs = employeeIs;
	}


	
}
