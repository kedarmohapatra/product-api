package com.rest.product.service;

import com.rest.product.model.Price;
import com.rest.product.model.Product;
import com.rest.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

import static com.rest.product.repository.ProductSpecifications.hasPriceChangedOrdered;
import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsWithPriceDrops(final String category) {
        List<Product> productsWithChangedPrice = productRepository.findAll(hasPriceChangedOrdered(category));

        List<Product> productsWithReducedPrice = productsWithChangedPrice.stream()
                .filter(this::isPriceReduced)
                .collect(toList());

        return productsWithReducedPrice;
    }

    private boolean isPriceReduced(Product product) {
        Iterator<Price> priceIterator = product.getPricesList().iterator();
        Price currentPrice = priceIterator.next();
        Price previousPrice = priceIterator.next();
        return currentPrice.getPrice() < previousPrice.getPrice();
    }
}
