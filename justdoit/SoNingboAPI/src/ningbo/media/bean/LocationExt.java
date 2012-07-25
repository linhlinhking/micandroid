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
@Table(name = "tb_location_ext")
public class LocationExt implements Serializable{

	private static final long serialVersionUID = 4829178256598578786L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String website;

	private String emailAddress;

	private String qq;

	private String msn;

	private String monStartTime;

	private String monEndTime;

	private boolean isClosedMon;

	private String tueStartTime;

	private String tueEndTime;

	private boolean isClosedTue;

	private String wedStartTime;

	private String wedEndTime;

	private boolean isClosedWed;
	
	private String thurStartTime;

	private String thurEndTime;

	private boolean isClosedThur;
	
	private String friStartTime;

	private String friEndTime;

	private boolean isClosedFri;
	
	private String satStartTime;

	private String satEndTime;

	private boolean isClosedSat;
	
	private String sunStartTime;

	private String sunEndTime;

	private boolean isClosedSun;
	
	@OneToOne
	@JoinColumn(name = "locationId")
	private Location location ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public String getMonStartTime() {
		return monStartTime;
	}

	public void setMonStartTime(String monStartTime) {
		this.monStartTime = monStartTime;
	}

	public String getMonEndTime() {
		return monEndTime;
	}

	public void setMonEndTime(String monEndTime) {
		this.monEndTime = monEndTime;
	}



	public String getTueStartTime() {
		return tueStartTime;
	}

	public void setTueStartTime(String tueStartTime) {
		this.tueStartTime = tueStartTime;
	}

	public String getTueEndTime() {
		return tueEndTime;
	}

	public void setTueEndTime(String tueEndTime) {
		this.tueEndTime = tueEndTime;
	}



	public String getWedStartTime() {
		return wedStartTime;
	}

	public void setWedStartTime(String wedStartTime) {
		this.wedStartTime = wedStartTime;
	}

	public String getWedEndTime() {
		return wedEndTime;
	}

	public void setWedEndTime(String wedEndTime) {
		this.wedEndTime = wedEndTime;
	}



	public String getThurStartTime() {
		return thurStartTime;
	}

	public void setThurStartTime(String thurStartTime) {
		this.thurStartTime = thurStartTime;
	}

	public String getThurEndTime() {
		return thurEndTime;
	}

	public void setThurEndTime(String thurEndTime) {
		this.thurEndTime = thurEndTime;
	}



	public String getFriStartTime() {
		return friStartTime;
	}

	public void setFriStartTime(String friStartTime) {
		this.friStartTime = friStartTime;
	}

	public String getFriEndTime() {
		return friEndTime;
	}

	public void setFriEndTime(String friEndTime) {
		this.friEndTime = friEndTime;
	}



	public String getSatStartTime() {
		return satStartTime;
	}

	public void setSatStartTime(String satStartTime) {
		this.satStartTime = satStartTime;
	}

	public String getSatEndTime() {
		return satEndTime;
	}

	public void setSatEndTime(String satEndTime) {
		this.satEndTime = satEndTime;
	}



	public String getSunStartTime() {
		return sunStartTime;
	}

	public void setSunStartTime(String sunStartTime) {
		this.sunStartTime = sunStartTime;
	}

	public String getSunEndTime() {
		return sunEndTime;
	}

	public void setSunEndTime(String sunEndTime) {
		this.sunEndTime = sunEndTime;
	}



	public boolean isClosedMon() {
		return isClosedMon;
	}

	public void setClosedMon(boolean isClosedMon) {
		this.isClosedMon = isClosedMon;
	}

	public boolean isClosedTue() {
		return isClosedTue;
	}

	public void setClosedTue(boolean isClosedTue) {
		this.isClosedTue = isClosedTue;
	}

	public boolean isClosedWed() {
		return isClosedWed;
	}

	public void setClosedWed(boolean isClosedWed) {
		this.isClosedWed = isClosedWed;
	}

	public boolean isClosedThur() {
		return isClosedThur;
	}

	public void setClosedThur(boolean isClosedThur) {
		this.isClosedThur = isClosedThur;
	}

	public boolean isClosedFri() {
		return isClosedFri;
	}

	public void setClosedFri(boolean isClosedFri) {
		this.isClosedFri = isClosedFri;
	}

	public boolean isClosedSat() {
		return isClosedSat;
	}

	public void setClosedSat(boolean isClosedSat) {
		this.isClosedSat = isClosedSat;
	}

	public boolean isClosedSun() {
		return isClosedSun;
	}

	public void setClosedSun(boolean isClosedSun) {
		this.isClosedSun = isClosedSun;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
