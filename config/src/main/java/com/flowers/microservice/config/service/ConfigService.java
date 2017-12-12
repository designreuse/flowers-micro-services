/**
 * 
 */
package com.flowers.microservice.config.service;


/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface ConfigService {
	
	String getConfig(String key);
	
	void setConfig(String key, String value);

}
