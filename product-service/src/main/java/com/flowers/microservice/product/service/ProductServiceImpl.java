/**
 * 
 */
package com.flowers.microservice.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.flowers.microservice.product.client.ProductServiceClient;
import com.flowers.microservice.product.client.StatisticsServiceClient;
import com.flowers.microservice.product.domain.Currency;
import com.flowers.microservice.product.domain.Product;
import com.flowers.microservice.product.domain.Saving;
import com.flowers.microservice.product.domain.User;
import com.flowers.microservice.product.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StatisticsServiceClient statisticsClient;

	@Autowired
	private ProductServiceClient prodClient;

	@Autowired
	private ProductRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Product findByName(String productName) {
		Assert.hasLength(productName);
		return repository.findByName(productName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product create(User user) {

		Product existing = repository.findByName(user.getUsername());
		Assert.isNull(existing, "account already exists: " + user.getUsername());

		prodClient.createUser(user);

		Saving saving = new Saving();
		saving.setAmount(new BigDecimal(0));
		saving.setCurrency(Currency.getDefault());
		saving.setInterest(new BigDecimal(0));
		saving.setDeposit(false);
		saving.setCapitalization(false);

		Product account = new Product();
		account.setName(user.getUsername());
		account.setLastSeen(new Date());
		account.setSaving(saving);

		repository.save(account);

		log.info("new account has been created: " + account.getName());

		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveChanges(String name, Product update) {

		Product account = repository.findByName(name);
		Assert.notNull(account, "can't find account with name " + name);

		account.setIncomes(update.getIncomes());
		account.setExpenses(update.getExpenses());
		account.setSaving(update.getSaving());
		account.setNote(update.getNote());
		account.setLastSeen(new Date());
		repository.save(account);

		log.debug("account {} changes has been saved", name);

		statisticsClient.updateStatistics(name, account);
	}
}