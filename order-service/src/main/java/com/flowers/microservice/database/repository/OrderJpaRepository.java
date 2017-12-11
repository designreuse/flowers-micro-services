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

import com.flowers.microservice.database.model.Order;

@Repository
@Component
public interface OrderJpaRepository extends CrudRepository<Object, String> {

	public default Order update(Order order){
		
		return save(order);
	}
}