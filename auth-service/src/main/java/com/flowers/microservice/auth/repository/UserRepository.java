/**
 * 
 */
package com.flowers.microservice.auth.repository;

/**
 * @author cgordon
 *
 */
import com.flowers.microservice.auth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}