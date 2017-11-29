/**
 * 
 */
package com.flowers.microservice.product.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flowers.microservice.product.domain.Product;

@FeignClient(name = "statistics-service")
public interface StatisticsServiceClient {

	@RequestMapping(method = RequestMethod.PUT, value = "/statistics/{productName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void updateStatistics(@PathVariable("accountName") String productName, Product product);

}