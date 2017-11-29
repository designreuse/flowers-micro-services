/**
 * 
 */
package com.flowers.microservice.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flowers.microservice.product.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

	Product findByName(String name);
}
