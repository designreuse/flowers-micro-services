/**
 * 
 */
package com.flowers.microservice.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}