/**
 * 
 */
package com.flowers.microservice.logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping(path = "/logger")
public class LoggerController {

	private static transient final Logger logger = LoggerFactory.getLogger(LoggerController.class);
	
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public void setLogEntity(@RequestParam(value = "code", required = false) @Valid String code,
			@Valid @RequestParam(value = "message", required = false) String message) {

		logger.info("code: {} , message; {} ", code, message);
	}
}