package com.epages.interview.product;

import static com.epages.interview.product.ProductTestFixture.given;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.epages.interview.JpaConfig;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = JpaConfig.class)	
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void given10Products_whenFindAllByOrderByBrandAscPriceDesc_thenAllIsReturned() {
		given()
				.randomProducts(10)
				.idBd(testEntityManager);

		List<Product> products = productRepository.findAllByOrderByBrandAscPriceDesc();

		then(products).hasSize(10);
	}

	@Test
	public void given10Products_whenFindAllByOrderByBrandAscPriceDesc_thenTheOrderingIsCorrect() {
		given()
				.randomProductsForBrand(10, "Brand A")
				.randomProductsForBrand(10, "Brand B")
				.idBd(testEntityManager);

		List<Product> products = productRepository.findAllByOrderByBrandAscPriceDesc();

		then(products).isSortedAccordingTo(comparing(Product::getBrand)
				.thenComparing(reverseOrder(comparing(Product::getPrice))));
	}
	
	@Test
	public void givenNoProducts_FindAllByOrderByBrandAscPriceDesc_thenReturnEmptyCollection() {
		//Given empty BD

		List<Product> products = productRepository.findAllByOrderByBrandAscPriceDesc();

		then(products).isEmpty();
	}
}
