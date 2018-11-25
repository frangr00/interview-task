package com.epages.interview.product.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epages.interview.product.Product;
import com.epages.interview.product.ProductSearcher;

@RestController
@RequestMapping(path = "/api/products")
class ProductController {

	private ProductSearcher productSeracher;

	public ProductController(ProductSearcher productSeracher) {
		super();
		this.productSeracher = productSeracher;
	}

	@GetMapping
	public ResponseEntity<Map<String, List<ProductRepresentation>>> getAllProductsGrupedByBrand() {
		Map<String, List<Product>> productsByBrand = productSeracher.findAllGrupedByBrand();

		Map<String, List<ProductRepresentation>> productsRepresentation = toProductRepresentationsByBrand(productsByBrand);

		return new ResponseEntity<Map<String, List<ProductRepresentation>>>(productsRepresentation, HttpStatus.OK);
	}

	private Map<String, List<ProductRepresentation>> toProductRepresentationsByBrand(Map<String, List<Product>> productsByBrand) {
		Map<String, List<ProductRepresentation>> productsRepresentation = productsByBrand.entrySet()
				.stream()
				.collect(Collectors.toMap(entry -> entry.getKey(),
						entry -> toRepresentations(entry.getValue())));
		return productsRepresentation;
	}

	private List<ProductRepresentation> toRepresentations(List<Product> product) {
		return product.stream()
				.map(ProductRepresentation::new)
				.collect(Collectors.toList());
	}
}
