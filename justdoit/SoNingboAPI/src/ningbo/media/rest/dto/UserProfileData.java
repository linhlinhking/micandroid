package ningbo.media.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;


@XmlType(name = "UserProfileData", namespace = WsConstants.NS, propOrder = {
		"id", "birthday", "stature", "blood", "constellation", "age",
		"qq", "cellPhone", "msn", "phone", "homeArea",
		"likeMusic", "likeBooks", "likeMovies", "likeGames"})
@XmlRootElement(name = "userprofile")
public class UserProfileData {
	
	private Integer id;

	private String birthday;

	private Integer stature;

	private String blood;

	private String constellation;

	private Integer age;

	private String qq;

	private String cellPhone;

	private String msn;

	private String phone;

	private String homeArea;

	private String likeMusic;

	private String likeBooks;

	private String likeMovies;

	private String likeGames;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomeArea() {
		return homeArea;
	}

	public void setHomeArea(String homeArea) {
		this.homeArea = homeArea;
	}

	public String getLikeMusic() {
		return likeMusic;
	}

	public void setLikeMusic(String likeMusic) {
		this.likeMusic = likeMusic;
	}

	public String getLikeBooks() {
		return likeBooks;
	}

	public void setLikeBooks(String likeBooks) {
		this.likeBooks = likeBooks;
	}

	public String getLikeMovies() {
		return likeMovies;
	}

	public void setLikeMovies(String likeMovies) {
		this.likeMovies = likeMovies;
	}

	public String getLikeGames() {
		return likeGames;
	}

	public void setLikeGames(String likeGames) {
		this.likeGames = likeGames;
	}
	
	

}
