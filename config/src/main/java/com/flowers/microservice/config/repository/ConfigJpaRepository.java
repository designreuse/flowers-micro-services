/**
 * 
 */
package com.flowers.microservice.config.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Repository
@Component
public interface ConfigJpaRepository extends CrudRepository<String, String> {
	

}