package ningbo.media.data.entity;


public class UserCommentData implements EntityData{

	private LocationDetail locationDetail ;
	
	private Integer commentId ;
	
	private String commentContent ;

	

	public LocationDetail getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(LocationDetail locationDetail) {
		this.locationDetail = locationDetail;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	
	
	
}
