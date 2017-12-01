/**
 * 
 */
package com.flowers.microservice.common.exception;

import java.lang.reflect.Constructor;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 *
 */
public class ThrownServiceExceptionDetails
{
    private Class<? extends ServiceException> clazz;
    private Constructor<? extends ServiceException> emptyConstructor;
    private Constructor<? extends ServiceException> messageConstructor;

	/**
	 * @return the clazz
	 */
	public Class<? extends ServiceException> getClazz() {
		return clazz;
	}
	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<? extends ServiceException> clazz) {
		this.clazz = clazz;
	}
	/**
	 * @return the emptyConstructor
	 */
	public Constructor<? extends ServiceException> getEmptyConstructor() {
		return emptyConstructor;
	}
	/**
	 * @param emptyConstructor the emptyConstructor to set
	 */
	public void setEmptyConstructor(Constructor<? extends ServiceException> emptyConstructor) {
		this.emptyConstructor = emptyConstructor;
	}
	/**
	 * @return the messageConstructor
	 */
	public Constructor<? extends ServiceException> getMessageConstructor() {
		return messageConstructor;
	}
	/**
	 * @param messageConstructor the messageConstructor to set
	 */
	public void setMessageConstructor(Constructor<? extends ServiceException> messageConstructor) {
		this.messageConstructor = messageConstructor;
	}
	
	/**
	 * 
	 * @param clazz2
	 * @return
	 */
	public ThrownServiceExceptionDetails withClazz(Class<? extends ServiceException> clazz2) {

		clazz = clazz2;
		return this;
	}

	/**
	 * 
	 * @param clazz2
	 * @return
	 */
	public ThrownServiceExceptionDetails withEmptyConstructor(Constructor<? extends ServiceException> clazz2) {

		this.emptyConstructor  = clazz2;
		return this;
	}

	/**
	 * 
	 * @param clazz2
	 * @return
	 */
	public ThrownServiceExceptionDetails withMessageConstructor(Constructor<? extends ServiceException> clazz2) {

		this.messageConstructor = clazz2;
		return this;
	}
	
	boolean hasMessageConstructor(){
		
		return !(this.messageConstructor==null);
	}
	
}