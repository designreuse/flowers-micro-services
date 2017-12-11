/**
 * 
 */
package com.flowers.microservice.compute.service;

import java.util.List;

import com.flowers.microservice.compute.model.Product;

/**
 * @author cgordon
 *
 */

public interface ComputeService {
	
	Product createProduct(Product product);
	
	Product findProductById(String productid);

	List<Product> findAllProductList();
	
	Product updateProduct(Product product);
	
	void deleteProduct(String productid);
}
