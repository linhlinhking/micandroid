package com.cisco.pmonitor.service.exception;

/**
 * service exception.
 * @author shuaizha
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -562908088294483183L;

	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public ServiceException(Throwable cause) {
        super(cause);
    }
}
