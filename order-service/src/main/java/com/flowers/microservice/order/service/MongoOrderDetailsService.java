/**
 * 
 */
package com.flowers.microservice.order.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flowers.microservice.order.repository.OrderJpaRepository;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

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