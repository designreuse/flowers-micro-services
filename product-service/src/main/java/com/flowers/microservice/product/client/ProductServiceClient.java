/**
 * 
 */
package com.flowers.microservice.product.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flowers.microservice.product.domain.User;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/uaa/products", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void createUser(User user);

}
