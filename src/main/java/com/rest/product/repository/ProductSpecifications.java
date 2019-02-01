package com.rest.product.repository;

import com.rest.product.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> hasPriceChangedOrdered(final String category) {
        return (Specification<Product>) (root, query, builder) ->
                builder.and(
                        builder.greaterThanOrEqualTo(builder.size(root.get("pricesList")), 2),
                        builder.equal(root.get("category"), category));
    }
}
