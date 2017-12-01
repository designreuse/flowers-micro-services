package com.flowers.microservice.common.exception;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import feign.RequestLine;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 *
 */
public class FeignServiceExceptionErrorDecoder implements ErrorDecoder
{
    private static final Logger logger = LoggerFactory.getLogger(FeignServiceExceptionErrorDecoder.class);

    private Class<?> apiClass;
    Map<String, ThrownServiceExceptionDetails> exceptionsThrown = new HashMap<>();

    public FeignServiceExceptionErrorDecoder(Class<?> apiClass) throws Exception
    {
        this.apiClass = apiClass;
        for (Method method : apiClass.getMethods()) {
            if (method.getAnnotation(RequestLine.class) != null) {
                for (Class<?> clazz : method.getExceptionTypes()) {
                    if (ServiceException.class.isAssignableFrom(clazz)) {
                        if (Modifier.isAbstract(clazz.getModifiers())) {
                            extractServiceExceptionInfoFromSubClasses(clazz);
                        } else {
                            extractServiceExceptionInfo(clazz);
                        }
                    } else {
                        logger.info("Exception '{}' declared thrown on interface '{}' doesn't inherit from "
                        		+ "ServiceException, it will be skipped.", clazz.getName(), apiClass.getName());
                    }
                }
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
	private void extractServiceExceptionInfo(Class<?> clazz)
            throws Exception
    {
        ServiceException thrownException = null;
        Constructor<?> emptyConstructor = null;
        Constructor<?> messageConstructor = null;

        for (Constructor<?> constructor : clazz.getConstructors()) {
            Class<?>[] parameters = constructor.getParameterTypes();
            if (parameters.length == 0) {
                emptyConstructor = constructor;
                thrownException = (ServiceException) constructor.newInstance();
            } else if (parameters.length == 1 && parameters[0].isAssignableFrom(String.class)) {
                messageConstructor = constructor;
                thrownException = (ServiceException) constructor.newInstance(new String());
            }
        }

        if (thrownException != null) {
            exceptionsThrown.put(thrownException.getErrorCode(),
                    new ThrownServiceExceptionDetails()
                        .withClazz((Class<? extends ServiceException>) clazz)
                        .withEmptyConstructor((Constructor<? extends ServiceException>) emptyConstructor)
                        .withMessageConstructor((Constructor<? extends ServiceException>) messageConstructor));
        } else {
            logger.warn("Couldn't instantiate the exception '{}' for the interface '{}', it needs an empty or String "+
                         "only *public* constructor.", clazz.getName(), apiClass.getName());
        }
    }    
    
    private void extractServiceExceptionInfoFromSubClasses(Class<?> clazz)
            throws Exception
    {
        Set<Class<?>> subClasses = getAllSubClasses(clazz);
        for (Class<?> subClass : subClasses) {
            extractServiceExceptionInfo(subClass);
        }
    }

    private Set<Class<?>> getAllSubClasses(Class<?> clazz) throws ClassNotFoundException
    {
        ClassPathScanningCandidateComponentProvider provider = 
                new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(clazz));

        Set<BeanDefinition> components = provider.findCandidateComponents("your/base/package/here");

        Set<Class<?>> subClasses = new HashSet<>();
        for (BeanDefinition component : components) {
            subClasses.add(Class.forName(component.getBeanClassName()));
        }
        return subClasses;
    }  
    
    @Override
    public Exception decode(String methodKey, Response response)
    {
        JacksonDecoder jacksonDecoder = new JacksonDecoder();
        RestException restException = null;
        try {
            restException = (RestException) jacksonDecoder.decode(response, RestException.class);
            if (restException != null && exceptionsThrown.containsKey(restException.getErrorCode())) {
                return getExceptionByReflection(restException);
            }
        } catch (IOException e) {
            // Fail silently here, irrelevant as a new exception will be thrown anyway
        } catch (Exception e) {
            logger.error("Error instantiating the exception to be thrown for the interface '{}'", 
                         apiClass.getName(), e);
        }
        return defaultDecode(methodKey, response, restException); //fallback not presented here
    }

    private ServiceException getExceptionByReflection(RestException restException)
            throws Exception
    {
        ServiceException exceptionToBeThrown = null;
        ThrownServiceExceptionDetails exceptionDetails = exceptionsThrown.get(restException.getErrorCode());
        if (exceptionDetails.hasMessageConstructor()) {
            exceptionToBeThrown = exceptionDetails.getMessageConstructor().newInstance(restException.getMessage());
        } else {
            exceptionToBeThrown = exceptionDetails.getEmptyConstructor().newInstance();
        }
        return exceptionToBeThrown;
    }

    public Exception defaultDecode(String methodKey, Response response, RestException restException)
    {
        return decode(methodKey, response); 
    }
    
}    