package com.cisco.pmonitor.dao.expection;

/**
 * dao exception.
 * @author shuaizha
 *
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 4691472726648990964L;

	
	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public DaoException(Throwable cause) {
        super(cause);
    }
}
