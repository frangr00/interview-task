package com.epages.interview.product;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class ProductTestFixture {

	private List<Product> products;

	private ProductTestFixture() {
		super();
		products = new ArrayList<>();
	}

	public static ProductTestFixture given() {
		return new ProductTestFixture();
	}

	public void idBd(TestEntityManager testEntityManager) {
		products.stream()
				.forEach(product -> testEntityManager.persist(product));
		testEntityManager.flush();
	}

	public List<Product> buildOrderedByBrandAscPriceDesc() {
		Collections.sort(products, comparing(Product::getBrand)
				.thenComparing(reverseOrder(comparing(Product::getPrice))));
		return products;
	}

	public ProductTestFixture randomProducts(int numProducts) {
		products.addAll(IntStream.range(0, numProducts)
				.mapToObj(i -> new Product(new Long(i), randomString(), randomString(), randomPrice(), true))
				.collect(toList()));
		return this;
	}

	public ProductTestFixture randomProductsForBrand(int numProducts, String brand) {
		products.addAll(IntStream.range(0, numProducts)
				.mapToObj(i -> new Product(new Long(products.size() + 1 + i), randomString(), brand, randomPrice(), true))
				.collect(toList()));
		return this;
	}

	private Double randomPrice() {
		double leftLimit = 1D;
		double rightLimit = 100D;
		return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}

	private String randomString() {
		return RandomStringUtils.randomAlphabetic(10);
	}

	public ProductTestFixture product(long id, String name, String brand, double price, boolean onSale) {
		products.add(new Product(id, name, brand, price, onSale));
		return this;
	}

}
