/**
 * 
 */
package com.flowers.microservice.config.service;

import org.springframework.stereotype.Service;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Service
public class ConfigServiceImpl implements ConfigService {

	public String getConfig(String key){
		
		return "12345";
	}
	
	public void setConfig(String key, String value){
		
	}

}
	
