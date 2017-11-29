/**
 * 
 */
package com.flowers.microservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.access.prepost.PreAuthorize;
	import org.springframework.web.bind.annotation.*;

import com.flowers.microservice.product.domain.Product;
import com.flowers.microservice.product.domain.User;
import com.flowers.microservice.product.service.ProductService;

import javax.validation.Valid;
	import java.security.Principal;

	@RestController
	public class ProductController {

		@Autowired
		private ProductService commerceService;

		@PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
		@RequestMapping(path = "/{name}", method = RequestMethod.GET)
		public Product getAccountByName(@PathVariable String name) {
			return commerceService.findByName(name);
		}

		@RequestMapping(path = "/current", method = RequestMethod.GET)
		public Product getCurrentAccount(Principal principal) {
			return commerceService.findByName(principal.getName());
		}

		@RequestMapping(path = "/current", method = RequestMethod.PUT)
		public void saveCurrentAccount(Principal principal, @Valid @RequestBody Product account) {
			commerceService.saveChanges(principal.getName(), account);
		}

		@RequestMapping(path = "/", method = RequestMethod.POST)
		public Product createNewAccount(@Valid @RequestBody User user) {
			return commerceService.create(user);
		}
	}