package ningbo.media.data.entity;


import ningbo.media.rest.dto.SystemUserData;

public class LocationCommentData implements EntityData{

	private SystemUserData systemUserData ;
	
	private LocationDetail locationData ;
	
	private CommentData commentData ;


	public SystemUserData getSystemUserData() {
		return systemUserData;
	}

	public void setSystemUserData(SystemUserData systemUserData) {
		this.systemUserData = systemUserData;
	}


	public CommentData getCommentData() {
		return commentData;
	}

	public void setCommentData(CommentData commentData) {
		this.commentData = commentData;
	}

	public LocationDetail getLocationData() {
		return locationData;
	}

	public void setLocationData(LocationDetail locationData) {
		this.locationData = locationData;
	}
	
	
}
