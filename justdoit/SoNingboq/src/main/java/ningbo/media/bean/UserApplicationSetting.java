package ningbo.media.bean;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_user_apps")
public class UserApplicationSetting extends BaseEntity{

	private static final long serialVersionUID = -1473894160379023050L;
	
	private Integer userId ;
	
	private Integer applicationId ;
	
	private Boolean isEnable ;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	

}
