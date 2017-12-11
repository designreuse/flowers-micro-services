/**
 * 
 */
package com.flowers.microservice.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@SpringBootApplication
@EnableCircuitBreaker
@EnableConfigServer
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}
}