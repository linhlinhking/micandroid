package com.jeecms.cms.entity.assist.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_vote_record table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_vote_record"
 */

public abstract class BaseCmsVoteRecord  implements Serializable {

	public static String REF = "CmsVoteRecord";
	public static String PROP_VOTE_TIME = "voteTime";
	public static String PROP_VOTETOPIC = "votetopic";
	public static String PROP_VOTE_COOKIE = "voteCookie";
	public static String PROP_USER = "user";
	public static String PROP_VOTE_IP = "voteIp";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsVoteRecord () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsVoteRecord (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsVoteRecord (
		java.lang.Integer id,
		com.jeecms.cms.entity.assist.CmsVoteTopic votetopic,
		java.util.Date voteTime,
		java.lang.String voteIp,
		java.lang.String voteCookie) {

		this.setId(id);
		this.setVotetopic(votetopic);
		this.setVoteTime(voteTime);
		this.setVoteIp(voteIp);
		this.setVoteCookie(voteCookie);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date voteTime;
	private java.lang.String voteIp;
	private java.lang.String voteCookie;

	// many to one
	private com.jeecms.cms.entity.main.CmsUser user;
	private com.jeecms.cms.entity.assist.CmsVoteTopic votetopic;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="voterecored_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: vote_time
	 */
	public java.util.Date getVoteTime () {
		return voteTime;
	}

	/**
	 * Set the value related to the column: vote_time
	 * @param voteTime the vote_time value
	 */
	public void setVoteTime (java.util.Date voteTime) {
		this.voteTime = voteTime;
	}


	/**
	 * Return the value associated with the column: vote_ip
	 */
	public java.lang.String getVoteIp () {
		return voteIp;
	}

	/**
	 * Set the value related to the column: vote_ip
	 * @param voteIp the vote_ip value
	 */
	public void setVoteIp (java.lang.String voteIp) {
		this.voteIp = voteIp;
	}


	/**
	 * Return the value associated with the column: vote_cookie
	 */
	public java.lang.String getVoteCookie () {
		return voteCookie;
	}

	/**
	 * Set the value related to the column: vote_cookie
	 * @param voteCookie the vote_cookie value
	 */
	public void setVoteCookie (java.lang.String voteCookie) {
		this.voteCookie = voteCookie;
	}


	/**
	 * Return the value associated with the column: user_id
	 */
	public com.jeecms.cms.entity.main.CmsUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: user_id
	 * @param user the user_id value
	 */
	public void setUser (com.jeecms.cms.entity.main.CmsUser user) {
		this.user = user;
	}


	/**
	 * Return the value associated with the column: votetopic_id
	 */
	public com.jeecms.cms.entity.assist.CmsVoteTopic getVotetopic () {
		return votetopic;
	}

	/**
	 * Set the value related to the column: votetopic_id
	 * @param votetopic the votetopic_id value
	 */
	public void setVotetopic (com.jeecms.cms.entity.assist.CmsVoteTopic votetopic) {
		this.votetopic = votetopic;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.assist.CmsVoteRecord)) return false;
		else {
			com.jeecms.cms.entity.assist.CmsVoteRecord cmsVoteRecord = (com.jeecms.cms.entity.assist.CmsVoteRecord) obj;
			if (null == this.getId() || null == cmsVoteRecord.getId()) return false;
			else return (this.getId().equals(cmsVoteRecord.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}