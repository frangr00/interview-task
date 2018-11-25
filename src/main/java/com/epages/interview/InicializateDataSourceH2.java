package com.epages.interview;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.epages.interview.product.Product;

@Component
@Profile("testData")
class InicializateDataSourceH2 implements CommandLineRunner {

	private EntityManager entityManager;

	public InicializateDataSourceH2(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Transactional
	public void run(String... args) throws Exception {
		List<Product> products = new ArrayList<>();
		products.add(new Product(1L, "Product A", "Brand A", 12.99D, true));
		products.add(new Product(2L, "Product B", "Brand A", 7.99D, false));
		products.add(new Product(3L, "Product C", "Brand B", 14.99D, false));
		products.add(new Product(4L, "Product D", "Brand B", 10.99D, false));
		products.stream().forEach(entityManager::persist);

	}
}
