package ningbo.media.data.entity;

import java.util.Date;

import ningbo.media.rest.dto.SystemUserData;

public class LocationCommentData implements EntityData{

	private SystemUserData systemUserData ;
	
	private Integer commentId ;
	
	private String commentContent ;
	
	private Date date_time ; 


	public SystemUserData getSystemUserData() {
		return systemUserData;
	}

	public void setSystemUserData(SystemUserData systemUserData) {
		this.systemUserData = systemUserData;
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

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	
	
	
}
