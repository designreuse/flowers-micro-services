package com.flowers.microservice.common.logging;

import java.io.IOException;

import javax.annotation.Resource;
import com.flowers.microservice.common.constants.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 * 
 * utility to config logging functionality. Logging detail implementation is abstracted 
 * by using slf4J.
 * 
 */
@Configuration
@ComponentScan({ Constants.BASE_LOCATION_SERVICES })
@PropertySource(value="logback.properties", ignoreResourceNotFound=true)
public class LoggingConfig {

	@Resource @Lazy private ApplicationContext appcontext;
	
    @Value("#{config['spring.logback.email.password']?:'log3mailpassw0rd'}")
	private String logbackErrorMailPassword;
	
	@Value("#{config['spring.logback.email.email']?:'flowersuser@somehost.com'}")
	private String supportEmail;
	
	@Value("#{config['spring.profiles.active']?:'active'}")
	private String env;
	
	@Value("#{config['log.dir']?:'/opt/flowers/logs'}")
	private String logDir;
	
	@Value("#{config['log.name']?:'errorlogging'}")
	private String logName;	
	
	
	public LoggingConfig() throws IOException{
		//configureLogback();
	}
		
}
