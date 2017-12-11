/**
 * 
 */
package com.flowers.microservice.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@SpringBootApplication
@EnableCircuitBreaker
@EnableTurbineStream
public class LoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingApplication.class, args);
	}
}