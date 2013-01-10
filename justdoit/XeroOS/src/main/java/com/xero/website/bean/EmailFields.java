package com.xero.website.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xero.core.bean.BaseEntity;

@Entity
@Table(name = "tb_email_info")
public class EmailFields extends BaseEntity {

	private static final long serialVersionUID = 2823885638453555830L;

	private String companyName;
	
	private String stepName;
	
	private String projectInfor ;

	private String stepOneText;

	private String stepTwoText;

	private String stepThreeText;

	private String stepOneBtnText;

	private String stepThreeNoteTitle;

	private String stepThreeNoteContent;
	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepOneText() {
		return stepOneText;
	}

	public void setStepOneText(String stepOneText) {
		this.stepOneText = stepOneText;
	}

	public String getStepTwoText() {
		return stepTwoText;
	}

	public void setStepTwoText(String stepTwoText) {
		this.stepTwoText = stepTwoText;
	}

	public String getStepThreeText() {
		return stepThreeText;
	}

	public void setStepThreeText(String stepThreeText) {
		this.stepThreeText = stepThreeText;
	}

	public String getStepOneBtnText() {
		return stepOneBtnText;
	}

	public void setStepOneBtnText(String stepOneBtnText) {
		this.stepOneBtnText = stepOneBtnText;
	}

	public String getStepThreeNoteTitle() {
		return stepThreeNoteTitle;
	}

	public void setStepThreeNoteTitle(String stepThreeNoteTitle) {
		this.stepThreeNoteTitle = stepThreeNoteTitle;
	}

	public String getStepThreeNoteContent() {
		return stepThreeNoteContent;
	}

	public void setStepThreeNoteContent(String stepThreeNoteContent) {
		this.stepThreeNoteContent = stepThreeNoteContent;
	}

	public String getProjectInfor() {
		return projectInfor;
	}

	public void setProjectInfor(String projectInfor) {
		this.projectInfor = projectInfor;
	}


}
