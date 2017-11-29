/**
 * 
 */
package com.flowers.microservice.product.service;

import com.flowers.microservice.product.domain.Product;
import com.flowers.microservice.product.domain.User;

/**
 * @author cgordon
 *
 */
public interface ProductService {

	/**
	 * Finds account by given name
	 *
	 * @param accountName
	 * @return found account
	 */
	Product findByName(String accountName);

	/**
	 * Checks if account with the same name already exists
	 * Invokes Auth Service user creation
	 * Creates new account with default parameters
	 *
	 * @param user
	 * @return created account
	 */
	Product create(User user);

	/**
	 * Validates and applies incoming account updates
	 * Invokes Statistics Service update
	 *
	 * @param name
	 * @param update
	 */
	void saveChanges(String name, Product update);
}