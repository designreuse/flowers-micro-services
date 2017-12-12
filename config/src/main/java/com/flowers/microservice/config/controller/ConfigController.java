/**
 * 
 */
package com.flowers.microservice.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.flowers.microservice.config.service.ConfigService;

/**
 * @author cgordon
 *
 */
@RestController
@RequestMapping("/")
public class ConfigController {

	@Autowired
	private ConfigService configService;


	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public String getConfig(@PathVariable final String key) {
		return configService.getConfig(key);
	}
	
}
