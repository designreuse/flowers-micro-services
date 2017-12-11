/**
 * 
 */
package com.flowers.microservice.database.service;

import java.util.List;

import com.flowers.microservice.database.model.Order;

/**
 * @author cgordon
 *
 */

public interface OrderService {
	
	Order createOrder(Order order);
	
	Order findOrderById(String orderid);

	List<Order> findAllOrderList();
	
	Order updateOrder(String orderid, Order order);
	
	void deleteOrder(String orderid);
}
