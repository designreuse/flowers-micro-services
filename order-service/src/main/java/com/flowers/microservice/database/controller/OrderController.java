/**
 * 
 */
package com.flowers.microservice.database.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.flowers.microservice.database.model.Order;
import com.flowers.microservice.database.service.OrderService;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/create}", method = RequestMethod.POST)
	public Order createOrder(@Valid @RequestBody final Order order) {
		return orderService.createOrder(order);
	}
	
	@RequestMapping(value = "/read/{orderid}", method = RequestMethod.GET)
	public Order getOrder(@PathVariable final String orderid) {
		return orderService.findOrderById(orderid);
	}
	
	@RequestMapping(value = "/all}", method = RequestMethod.GET)
	public List<Order> getAllOrders() {
		return orderService.findAllOrderList();
	}
	
	@RequestMapping(value = "/update/{orderid}", method = RequestMethod.POST)
	public Order updateOrder(@PathVariable final String orderid, @Valid @RequestBody final Order order) {
		return orderService.updateOrder(orderid, order);
	}
	
	@RequestMapping(value = "/delete/{orderid}", method = RequestMethod.PUT)
	public void deleteOrder(@PathVariable final String orderid) {
		orderService.deleteOrder(orderid);
	}
}


