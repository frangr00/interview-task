package com.epages.interview.product;

import static com.epages.interview.product.ProductTestFixture.given;
import static java.util.Comparator.comparing;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductSearcherTest {

	@InjectMocks
	private ProductSearcherImpl productSearcher;
	@Mock
	private ProductRepository productRepository;

	@Test
	public void givenListOfProducts_whenSearchBrands_thenTheyAreGrupedAndOrdered() {
		List<Product> products = given()
				.randomProductsForBrand(2, "Brand A")
				.randomProductsForBrand(2, "Brand A")
				.randomProductsForBrand(2, "Brand B")
				.buildOrderedByBrandAscPriceDesc();
		given(productRepository.findAllByOrderByBrandAscPriceDesc())
				.willReturn(products);

		Map<String, List<Product>> productsByBrand = productSearcher.findAllGrupedByBrand();

		then(productsByBrand).hasSize(2);
		then(productsByBrand.entrySet()).allSatisfy((entry) -> {
			then(entry.getValue()).isSortedAccordingTo(comparing(Product::getPrice).reversed());
		});
	}
}
