package ningbo.media.rest.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlType(name = "SystemUser", namespace = WsConstants.NS, propOrder = {
		"nickName", "md5Value", "username", "name_cn", "name_en", "email",
		"status", "photo_path", "gender", "birthday", "datetime",
		"lastModifyTime", "website", "userType", "intro", "remark",
		"following", "followed", "followingStatus", "userProfileData" })
@XmlRootElement(name = "data")
public class SystemUserData {

	private String nickName;

	private String md5Value;

	private String username;

	private String name_cn;

	private String name_en;

	private String email;

	private boolean status;

	private String photo_path;

	private String gender;

	private Date birthday;

	private Date datetime;

	private Date lastModifyTime;

	private String website;

	private String userType;

	private String intro;

	private String remark;

	private long following;

	private long followed;

	private boolean followingStatus;

	private UserProfileData userProfileData;

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
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


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UserProfileData getUserProfileData() {
		return userProfileData;
	}

	public void setUserProfileData(UserProfileData userProfileData) {
		this.userProfileData = userProfileData;
	}

	public long getFollowing() {
		return following;
	}

	public void setFollowing(long following) {
		this.following = following;
	}

	public long getFollowed() {
		return followed;
	}

	public void setFollowed(long followed) {
		this.followed = followed;
	}


	public boolean isFollowingStatus() {
		return followingStatus;
	}

	public void setFollowingStatus(boolean followingStatus) {
		this.followingStatus = followingStatus;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
