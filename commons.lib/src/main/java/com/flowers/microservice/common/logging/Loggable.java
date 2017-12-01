/**
 * 
 */
package com.flowers.microservice.common.logging;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 *
 * Annotation for methods, whose execution should be logged.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Loggable {

    /**
     * Log severity level of 'before' and 'after' log statements.
     */
    enum Level {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }

    /**
     * Defines the severity which should be used when logging the method arguments.
     */
    Level level() default Level.FATAL;
}