package com.flowers.microservice.common.exception;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 *
 * Status class for all passing status information around the application etc..
 */
public class Status {

	private String code;
	private String message;

	public Status(){};

	public Status(String ce, String msg){
		code = ce;
		message = msg;
	};

	public String getCode() {
		return code==null? "" : code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message==null? "" : message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}