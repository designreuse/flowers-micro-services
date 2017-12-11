package com.flowers.microservice.account.repository;

import com.flowers.microservice.account.domain.Account;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

	default public Account findByName(String name){
		
		List<Account> accounts = (List<Account>) findAll();
		
		return accounts.stream().filter(account -> account.getName().equals(name)).findFirst().orElseGet(Account::new);
	};

}
