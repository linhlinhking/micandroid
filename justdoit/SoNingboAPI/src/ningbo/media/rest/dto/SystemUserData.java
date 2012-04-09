package ningbo.media.rest.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;
import ningbo.media.rest.base.WSResult;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlType(name = "SystemUser",namespace = WsConstants.NS)
public class SystemUserData extends WSResult {

	private Integer id;
	
	private String username; 
	
	private String name_cn;
	
	private String email;
	
	private boolean status;
	
	private String photo_path;
	
	private boolean gender;
	
	private Date birthday;
	
	private Boolean isManager;
	
	private Date datetime;
	
	private Date lastModifyTime ;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

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

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}
	
	
	
}
