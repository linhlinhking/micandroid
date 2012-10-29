package ningbo.media.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="tb_location_edit")
@XmlRootElement
public class LocationEdit extends BaseEntity{

	private static final long serialVersionUID = -4279805702213014733L;
	
	private String fieldName ;
	
	private String oldValue ;
	
	private String newValue ;
	
	private String locationMd5 ;
	
	private String userMd5 ;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getLocationMd5() {
		return locationMd5;
	}

	public void setLocationMd5(String locationMd5) {
		this.locationMd5 = locationMd5;
	}

	public String getUserMd5() {
		return userMd5;
	}

	public void setUserMd5(String userMd5) {
		this.userMd5 = userMd5;
	}

	
}
