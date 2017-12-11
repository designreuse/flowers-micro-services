/**
 * 
 */
package com.flowers.microservice.database.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flowers.microservice.database.model.Product;
import com.flowers.microservice.database.service.DatabaseService;

@RestController
@RequestMapping("/data/product")
public class DatabaseController {

	@Autowired
	private DatabaseService dataService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Product createProduct(@Valid @RequestBody final Product product) {
		return dataService.createProduct(product);
	}
	
	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable final String id) {
		return dataService.findProductById(id);
	}
	
	@RequestMapping(value = "/all}", method = RequestMethod.GET)
	public List<Product> getAllProduct() {
		return dataService.findAllProductList();
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public Product updateProduct(@PathVariable final String id, @Valid @RequestBody final Product product) {
		return dataService.updateProduct(id, product);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void deleteProduct(@PathVariable final String id) {
		dataService.deleteProduct(id);
	}
}


