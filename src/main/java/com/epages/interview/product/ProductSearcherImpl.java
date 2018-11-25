package com.epages.interview.product;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
class ProductSearcherImpl implements ProductSearcher {

	private ProductRepository productRepository;

	public ProductSearcherImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Map<String, List<Product>> findAllGrupedByBrand() {
		List<Product> products = productRepository.findAllByOrderByBrandAscPriceDesc();

		Map<String, List<Product>> grupedByBrandName = products.stream()
				.collect(groupingBy(Product::getBrand));

		return grupedByBrandName;
	}
}
