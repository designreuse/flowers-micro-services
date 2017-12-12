/**
 * 
 */
package com.flowers.microservice.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@SpringBootApplication
@EnableCircuitBreaker
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OrderApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}