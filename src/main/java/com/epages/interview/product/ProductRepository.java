package com.epages.interview.product;

import java.util.List;

import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
interface ProductRepository extends Repository<Product, Long> {

	List<Product> findAllByOrderByBrandAscPriceDesc();

}
