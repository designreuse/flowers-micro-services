/**
 * 
 */
package com.flowers.microservice.common.exception;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 *
 */
public class RestException extends ServiceException{

	private static final long serialVersionUID = -7170623165346715771L;

	private String errorCode;
	
	/**
	 * @param msg
	 */
	public RestException(String msg) {
		super(msg);
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
