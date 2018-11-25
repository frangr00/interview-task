package com.epages.interview.product;

import java.util.List;
import java.util.Map;

public interface ProductSearcher {

	Map<String, List<Product>> findAllGrupedByBrand();

}
