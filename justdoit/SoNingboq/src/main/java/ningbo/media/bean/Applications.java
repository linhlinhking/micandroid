package ningbo.media.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_applications")
public class Applications extends BaseEntity{

	private static final long serialVersionUID = 7520205802198717733L;
	
	@Column(name="name_cn")
	private String applicationNameCn ; //应用程序中文
	
	@Column(name="name_en")
	private String applicationNameEn ; //应用程序英文
	
	@Column(name="app_icon")
	private String applicationIcon ; //应用程序图标
	
	private Boolean isEnable ; //是否启用
	
	@Column(name="description_cn")
	private String descriptionCn ;
	
	@Column(name="description_en")
	private String descriptionEn ;
	
	
	private Boolean allowUser ;
	
	private Boolean allowCompanyUser ;


	public String getApplicationNameCn() {
		return applicationNameCn;
	}

	public void setApplicationNameCn(String applicationNameCn) {
		this.applicationNameCn = applicationNameCn;
	}

	public String getApplicationNameEn() {
		return applicationNameEn;
	}

	public void setApplicationNameEn(String applicationNameEn) {
		this.applicationNameEn = applicationNameEn;
	}

	public String getApplicationIcon() {
		return applicationIcon;
	}

	public void setApplicationIcon(String applicationIcon) {
		this.applicationIcon = applicationIcon;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getDescriptionCn() {
		return descriptionCn;
	}

	public void setDescriptionCn(String descriptionCn) {
		this.descriptionCn = descriptionCn;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public Boolean getAllowUser() {
		return allowUser;
	}

	public void setAllowUser(Boolean allowUser) {
		this.allowUser = allowUser;
	}

	public Boolean getAllowCompanyUser() {
		return allowCompanyUser;
	}

	public void setAllowCompanyUser(Boolean allowCompanyUser) {
		this.allowCompanyUser = allowCompanyUser;
	}
	
	
	
}
