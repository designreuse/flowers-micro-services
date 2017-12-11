/**
 * 
 */
package com.flowers.microservice.database.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.flowers.microservice.database.model.Order;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Repository
@Component
public interface OrderJpaRepository extends CrudRepository<Object, String> {

	public default Order update(Order order){
		
		return save(order);
	}
}