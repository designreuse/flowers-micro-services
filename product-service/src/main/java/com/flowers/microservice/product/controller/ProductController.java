/**
 * 
 */
package com.flowers.microservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.flowers.microservice.product.domain.Product;
import com.flowers.microservice.product.service.ProductService;
import javax.validation.Valid;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping(path = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(path = "/find", method = RequestMethod.GET)
	public Product getProductById(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name) {
		return name!=null? productService.findProductByName(name) : productService.findProductById(id);
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.PUT)
	public void deleteProduct(@PathVariable String id) {
		productService.delete(id);
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public Product createProduct(@Valid @RequestBody Product product) {
		return productService.create(product);
	}

	@RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
	public Product updateProduct(@Valid @PathVariable String id, @Valid @RequestBody Product product) {
		return productService.update(id, product);
	}

}