package ningbo.media.admin.vo;

import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;

public class LocationEditVO {

	private String fieldName ;
	
	private String oldValue ;
	
	private String newValue ;
	
	private Location location ;
	
	private SystemUser systemUser ;
	
	
	public LocationEditVO(){}
	
	

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
	
	
	
	
}
