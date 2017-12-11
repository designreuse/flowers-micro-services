/**
 * 
 */
package com.flowers.microservice.compute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flowers.microservice.compute.repository.FlowersProductJpaRepository;

@Service
public class MongoDataDetailsService implements UserDetailsService {

	@Autowired
	private FlowersProductJpaRepository repository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Object obj = repository.findOne(username);

		if (obj == null) {
			throw new UsernameNotFoundException(username);
		}

		return (UserDetails)obj;
	}
}