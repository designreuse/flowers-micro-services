/**
 * 
 */
package com.flowers.microservice.database.service;

/**
 * @author cgordon
 *
 */
import com.flowers.microservice.database.repository.OrderJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MongoOrderDetailsService implements UserDetailsService {

	@Autowired
	private OrderJpaRepository repository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Object obj = repository.findOne(username);

		if (obj == null) {
			throw new UsernameNotFoundException(username);
		}

		return (UserDetails)obj;
	}
}