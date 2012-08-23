package ningbo.media.data.entity;


public class UserCommentData implements EntityData{

	private LocationDetail locationDetail ;
	
	private CommentData commentData ;


	public LocationDetail getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(LocationDetail locationDetail) {
		this.locationDetail = locationDetail;
	}

	public CommentData getCommentData() {
		return commentData;
	}

	public void setCommentData(CommentData commentData) {
		this.commentData = commentData;
	}

	
	
	
	
}
