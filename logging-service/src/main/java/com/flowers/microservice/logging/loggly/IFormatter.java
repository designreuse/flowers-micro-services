/**
 * 
 */
package com.flowers.microservice.logging.loggly;

/**
 * @author cgordon
 *
 */
public interface IFormatter {
    /**
     * Formats log events as a string
     * @param priority log priority level
     * @param tag correlating string
     * @param message message to be logged
     * @param t throwable (or null)
     * @return the string
     */
    String format(int priority, String tag, String message, Throwable t);
}