/**
 * 
 */
package com.flowers.microservice.logging;

/**
 * @author cgordon
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class LoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingApplication.class, args);
	}
}