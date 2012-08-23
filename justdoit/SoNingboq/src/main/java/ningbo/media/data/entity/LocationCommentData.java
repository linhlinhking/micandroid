package ningbo.media.data.entity;



public class LocationCommentData implements EntityData{

	
	private LocationDetail locationData ;
	
	private CommentData commentData ;


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
