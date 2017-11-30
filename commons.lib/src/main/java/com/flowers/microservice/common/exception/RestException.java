/**
 * 
 */
package com.flowers.microservice.common.exception;

/**
 * @author cgordon
 *
 */
public class RestException extends ServiceException{

	private static final long serialVersionUID = -7170623165346715771L;

	private String errorCode;
	
	public RestException(){
		
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
