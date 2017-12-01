/**
 * 
 */
package com.flowers.microservice.common.logging;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.annotation.Nonnull;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 *
 * collection of methods to help logging functionality.
 */
public class LoggingHelper{

	/**
	 * 
	 * @param t
	 * @return
	 */
	public static String getStackTrace (@Nonnull Throwable t)
	{
		StringWriter stringWriter = new StringWriter();
		PrintWriter  printWriter  = new PrintWriter(stringWriter);
		t.printStackTrace(printWriter);
		printWriter.close();    
		try {
			stringWriter.close();
		}
		catch (IOException e) {
			debug("Method Call to invoke dynamic: ", LoggingHelper.getStackTrace(e));	    	
		}
		return stringWriter.toString();
	}

	/**
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	private static String debug(String param1, String param2) {
		return debug(param1, param2);
	}

	/** This returns a logger object based on the class/type parameter passed
	 * @param <code>Class</code> the class to associate with appender
	 * @return <code>Logger</code> logger class to be used for logging 
	 */	
	public static Logger getLogger(Class<?> klass){

		Logger logger = (Logger)LogManager.getLogger(klass);

		return logger;
	} 

}
