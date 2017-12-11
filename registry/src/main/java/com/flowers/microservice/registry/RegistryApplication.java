/**
 * 
 */
package com.flowers.microservice.registry;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}
}