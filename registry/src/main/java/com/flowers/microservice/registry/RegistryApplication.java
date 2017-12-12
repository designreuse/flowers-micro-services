/**
 * 
 */
package com.flowers.microservice.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableCircuitBreaker
@EnableDiscoveryClient
public class RegistryApplication extends SpringBootServletInitializer {

	public RegistryApplication(){
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RegistryApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}
}