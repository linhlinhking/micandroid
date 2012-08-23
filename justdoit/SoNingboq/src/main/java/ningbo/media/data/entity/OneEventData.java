package ningbo.media.data.entity;

import ningbo.media.rest.dto.SystemUserData;



public class OneEventData extends EventData {

	private LocationDetail location ;
	
	private SystemUserData user ;

	
	public LocationDetail getLocation() {
		return location;
	}

	public void setLocation(LocationDetail location) {
		this.location = location;
	}

	public SystemUserData getUser() {
		return user;
	}

	public void setUser(SystemUserData user) {
		this.user = user;
	}
	
	
	
}
