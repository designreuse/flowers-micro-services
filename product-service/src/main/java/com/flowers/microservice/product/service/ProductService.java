/**
 * 
 */
package com.flowers.microservice.product.service;

import com.flowers.microservice.product.domain.Product;

/**
 * @author cgordon
 *
 */
public interface ProductService {

	public Product findProductByName(String name);

	public Product findProductById(String id);
	
	public Product create(Product Product);

	public Product update(String id, Product Product);
	
	public void delete(String id);	
}