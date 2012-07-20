package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_person_userprofile")
public class PersonUserProfile implements Serializable {

	private static final long serialVersionUID = 5917573295354557980L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToOne
	@JoinColumn(name = "userId")
	private SystemUser systemUser;
	
	
	// private Integer weight ;

	// private String interest ;

	// private String expertise ;

	// private String cardId ;

	// private String companyName ;

	// private String companyAddress ;

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

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}


}
