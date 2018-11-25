package com.epages.interview.product.rest;

import com.epages.interview.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

class ProductRepresentation {

	ProductRepresentation(Product product) {
		super();
		id = product.getId();
		name = product.getName();
		brand = product.getBrand();
		price = product.getPrice();
		onSale = product.isOnSale();
	}

	private Long id;
	private String name;
	@JsonIgnore
	private String brand;
	private double price;
	@JsonIgnore
	private boolean onSale;

	@JsonProperty("event")
	@JsonInclude(Include.NON_NULL)
	public String isOnSale() {
		return onSale ? "ON SALE" : null;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

}
