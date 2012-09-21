package com.cisco.pmonitor.dao.dataobject;

import java.io.Serializable;

/**
 * The base data object.
 * @author shuaizha
 * @date 2012-02-09
 */
public class BaseDo implements Serializable {

	private static final long serialVersionUID = -3506167359213588256L;
	
	/**
	 * The id field for database as primary key.
	 */
	private int id;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
