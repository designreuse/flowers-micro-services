/**
 * 
 */
package com.flowers.microservice.auth.service;

/**
 * @author cgordon
 *
 */
import com.flowers.microservice.auth.domain.User;
import com.flowers.microservice.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findOne(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}
}