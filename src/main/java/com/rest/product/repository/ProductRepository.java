package com.rest.product.repository;

import com.rest.product.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import java.util.List;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD;

public interface ProductRepository extends CrudRepository<Product, String>, JpaSpecificationExecutor<Product> {

    @Override
    @EntityGraph(value = "Product.fetchAll", type = LOAD)
    List<Product> findAll(@Nullable Specification<Product> spec);

}
