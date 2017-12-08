/**
 * 
 */
package com.flowers.microservice.database.repository;

/**
 * @author cgordon
 *
 */
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ProductJpaRepository extends CrudRepository<Object, String> {
	
	

}