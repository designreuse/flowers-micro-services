/**
 * 
 */
package com.flowers.microservice.common.security;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nonnull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.flowers.microservice.common.constants.Constants;

/**
 * @author cgordon
 * @created 12/02/2017
 * @version 1.0
 *
 */
public class Roles {
	
	/**
	 * 
	 * @param role role of requester
	 * @return boolean flag if user has permission to requested resource
	 */
	public boolean hasRole(String role) {
		
		boolean hasRole = false;

		Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) 
				  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		  
		  for (@Nonnull GrantedAuthority authority : authorities) {
		     hasRole = authority.getAuthority().equals(role);
			     if (hasRole) {
				  break;
			     }
		  }
		  return hasRole;
	}	
	
	/**
	 * 
	 * @return <code>Collection</code>
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(Constants.ROLE_VIEW)); 
		authorities.add(new SimpleGrantedAuthority(Constants.ROLE_DELETE));
		authorities.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));
		
		return authorities;
	}
}