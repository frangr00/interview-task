package com.epages.interview.product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	private Long id;
	private String name;
	private String brand;
	private Double price;
	private Boolean onSale;

	public Product() {
		super();
	}

	public Product(Long id, String name, String brand, double price, boolean onSale) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.onSale = onSale;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public Double getPrice() {
		return price;
	}

	public Boolean isOnSale() {
		return onSale;
	}

}