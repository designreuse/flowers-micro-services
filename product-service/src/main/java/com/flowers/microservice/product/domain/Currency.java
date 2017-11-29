/**
 * 
 */
package com.flowers.microservice.product.domain;

/**
 * @author cgordon
 *
 */
public enum Currency {

	USD, EUR, RUB;

	public static Currency getDefault() {
		return USD;
	}
}