/**
 * 
 */
package com.flowers.microservice.database.service;

import java.util.List;

import com.flowers.microservice.database.model.Product;

/**
 * @author cgordon
 *
 */

public interface DatabaseService {
	
	Product createProduct(Product product);
	
	Product findProductById(String productid);

	List<Product> findAllProductList();
	
	Product updateProduct(String productid, Product product);
	
	void deleteProduct(String productid);
}
