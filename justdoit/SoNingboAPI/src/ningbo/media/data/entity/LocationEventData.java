package ningbo.media.data.entity;

import ningbo.media.rest.dto.SystemUserData;

public class LocationEventData extends EventData{

	private SystemUserData user ;
	
	public LocationEventData(){}

	public SystemUserData getUser() {
		return user;
	}

	public void setUser(SystemUserData user) {
		this.user = user;
	}
	
	
}
