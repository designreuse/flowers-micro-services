/**
 * 
 */
package com.flowers.microservice.compute.controller;

import java.time.Instant;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.flowers.microservice.compute.calculate.CalculateFacade;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping("/compute")
public class ComputeController {

    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/tax/{ordernum}", method = RequestMethod.GET)
	public Double computeOrderTax(@PathVariable final String ordernum) {
		return CalculateFacade.calculateOrderTax(ordernum);
	}
	
    @HystrixCommand(fallbackMethod = "fallback")    
	@RequestMapping(value = "/shipping/{ordernum}", method = RequestMethod.GET)
	public Double computeOrderShipping(@PathVariable final String ordernum) {
		return CalculateFacade.calculateOrderShipping(ordernum);
	}
	
    @HystrixCommand(fallbackMethod = "fallbackDate")    
	@RequestMapping(value = "/deliverydate/{ordernum}", method = RequestMethod.GET)
	public String computeOrderDeliveryDate(@PathVariable final String ordernum) {
		return CalculateFacade.calculateOrderDeliveryDate(ordernum);
	}
	
    @HystrixCommand(fallbackMethod = "fallback")	
	@RequestMapping(value = "/total/{ordernum}", method = RequestMethod.GET)
	public Double computeOrderTotal(@PathVariable final String ordernum) {
		return CalculateFacade.calculateOrderTotal(ordernum);
	}
	
    public Double fallback() {
        return new Double(0.0);
    }

    public String fallbackDate() {
        return Instant.now().toString();
    }
    
}


