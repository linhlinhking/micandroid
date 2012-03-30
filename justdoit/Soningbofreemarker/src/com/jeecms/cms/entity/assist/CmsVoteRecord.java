package com.jeecms.cms.entity.assist;

import com.jeecms.cms.entity.assist.base.BaseCmsVoteRecord;



public class CmsVoteRecord extends BaseCmsVoteRecord {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsVoteRecord () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsVoteRecord (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsVoteRecord (
		java.lang.Integer id,
		com.jeecms.cms.entity.assist.CmsVoteTopic votetopic,
		java.util.Date voteTime,
		java.lang.String voteIp,
		java.lang.String voteCookie) {

		super (
			id,
			votetopic,
			voteTime,
			voteIp,
			voteCookie);
	}

/*[CONSTRUCTOR MARKER END]*/


}