package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ningbo.media.bean.enums.FriendType;


/**
 * Description:
 * @author Devon.Ning
 * @2012-3-30下午1:43:33
 * @version 1.0
 * Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 */
@Entity
@Table(name = "in_friends")
public class Friends implements Serializable{

	private static final long serialVersionUID = -590296924917498669L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private String userId ;
	
	private String followId ;
	
	private FriendType isFollowed ; 
	
	private String remark ;
	
	public Friends(){}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public FriendType getIsFollowed() {
		return isFollowed;
	}

	public void setIsFollowed(FriendType isFollowed) {
		this.isFollowed = isFollowed;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getFollowId() {
		return followId;
	}


	public void setFollowId(String followId) {
		this.followId = followId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
