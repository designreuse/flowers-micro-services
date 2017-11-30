/**
 * 
 */
package com.flowers.microservice.common.exception;

/**
 * @author cgordon
 *
 */
public abstract class ServiceException extends Exception
{
	private static final long serialVersionUID = 7128083766561947970L;

	private String errorCode;

    public String getErrorCode()
    {
        return errorCode;
    }
}