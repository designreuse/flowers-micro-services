/**
 * 
 */
package com.flowers.microservice.registry.controller;

import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping(path = "/registry")
public class RegistryController {

	
	@RequestMapping(path = "/find/{id}", method = RequestMethod.POST)
	public String getRegistryEntity(@PathVariable @Valid String id) {

		return "";
	}
}