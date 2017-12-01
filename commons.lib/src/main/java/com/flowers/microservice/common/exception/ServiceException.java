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
public abstract class ServiceException extends Exception
{
	private static final long serialVersionUID = 7128083766561947970L;

	private String errorCode;

	public ServiceException(String msg) {
		
		super(msg);
	}
	
    public String getErrorCode()
    {
        return errorCode;
    }
}