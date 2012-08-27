package ningbo.media.data.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.rest.WsConstants;
import ningbo.media.rest.dto.SystemUserData;

@XmlType(name = "Comment", namespace = WsConstants.NS, propOrder = {
		"commentId", "commentContent", "overAll", "rank1", "rank2", "rank3",
		"createTime", "updateTime","userData","locationData" })
@XmlRootElement(name = "data")
public class CommentData implements EntityData {

	private Integer commentId;

	private String commentContent;

	private int overAll;

	private int rank1;

	private int rank2;

	private int rank3;

	private Date createTime;

	private Date updateTime;
	
	private SystemUserData userData ;
	
	private LocationDetail locationData ;

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

	public int getOverAll() {
		return overAll;
	}

	public void setOverAll(int overAll) {
		this.overAll = overAll;
	}

	public int getRank1() {
		return rank1;
	}

	public void setRank1(int rank1) {
		this.rank1 = rank1;
	}

	public int getRank2() {
		return rank2;
	}

	public void setRank2(int rank2) {
		this.rank2 = rank2;
	}

	public int getRank3() {
		return rank3;
	}

	public void setRank3(int rank3) {
		this.rank3 = rank3;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public SystemUserData getUserData() {
		return userData;
	}

	public void setUserData(SystemUserData userData) {
		this.userData = userData;
	}

	public LocationDetail getLocationData() {
		return locationData;
	}

	public void setLocationData(LocationDetail locationData) {
		this.locationData = locationData;
	}
	

	
}
