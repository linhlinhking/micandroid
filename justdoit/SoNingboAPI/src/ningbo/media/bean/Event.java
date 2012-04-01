package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

/**
 * 
 * Description:活动实体
 * 
 * @author Devon.Ning
 * 
 * @2012-3-30上午11:10:39
 * 
 * @version 1.0
 * 
 * <p>
 * Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 * </p>
 * 
 */

@Entity
@Table(name = "tb_events")
public class Event implements Serializable {

	private static final long serialVersionUID = -2772155327178236095L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	private String title;

	private String subject;

	private String start_date;

	private String start_time;

	private String end_date;

	private String end_time;

	private Integer isPublic;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId")
	private Location location;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private SystemUser systemUser;

	public Event() {
	}

	public Integer getId() {

		return id;

	}

	public void setId(Integer id) {

		this.id = id;

	}

	public String getSubject() {

		return subject;

	}

	public void setSubject(String subject) {

		this.subject = subject;

	}

	public String getTitle() {

		return title;

	}

	public void setTitle(String title) {

		this.title = title;

	}

	public String getStart_date() {

		return start_date;

	}

	public void setStart_date(String start_date) {

		this.start_date = start_date;

	}

	public String getStart_time() {

		return start_time;

	}

	public void setStart_time(String start_time) {

		this.start_time = start_time;

	}

	public String getEnd_date() {

		return end_date;

	}

	public void setEnd_date(String end_date) {

		this.end_date = end_date;

	}

	public String getEnd_time() {

		return end_time;

	}

	public void setEnd_time(String end_time) {

		this.end_time = end_time;

	}

	public Location getLocation() {

		return location;

	}

	public void setLocation(Location location) {

		this.location = location;

	}

	public SystemUser getSystemUser() {

		return systemUser;

	}

	public void setSystemUser(SystemUser systemUser) {

		this.systemUser = systemUser;

	}

	public Integer getIsPublic() {

		return isPublic;

	}

	public void setIsPublic(Integer isPublic) {

		this.isPublic = isPublic;

	}

}
