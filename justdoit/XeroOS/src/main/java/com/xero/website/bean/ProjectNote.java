package com.xero.website.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name = "tb_project_note")
public class ProjectNote extends BaseEntity {

	private static final long serialVersionUID = -5767672521591170321L;

	private Integer projectId;

	private boolean showCustomer;

	private String content;

	private Date landmarkDate;

	private Integer userId;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public boolean isShowCustomer() {
		return showCustomer;
	}

	public void setShowCustomer(boolean showCustomer) {
		this.showCustomer = showCustomer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getLandmarkDate() {
		return landmarkDate;
	}

	public void setLandmarkDate(Date landmarkDate) {
		this.landmarkDate = landmarkDate;
	}

}
