/**
 * 
 */
package com.flowers.microservice.monitoring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class MonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringApplication.class, args);
	}
}