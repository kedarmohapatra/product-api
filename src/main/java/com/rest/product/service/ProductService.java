package com.rest.product.service;

import jl.products.model.JlProducts;
import jl.products.model.Price;
import jl.products.model.Product;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;


public class ProductService {

    private RestTemplate restTemplate;
    private String restURI;

    public ProductService(RestTemplate restTemplate, String restURI) {
        this.restTemplate = restTemplate;
        this.restURI = restURI;
    }

    public List<Product> getProductsWithPriceDrops() {
        JlProducts jlProducts;
        try {
            jlProducts = restTemplate.getForObject(restURI, JlProducts.class);
        } catch (RestClientException ex){
            jlProducts = new JlProducts();
        }

        List<Product> reducedProducts = new ArrayList<>();
        if (jlProducts != null) {
            List<Product> allProducts = jlProducts.getProducts();
            if (allProducts != null) {
                reducedProducts = allProducts
                        .stream()
                        .filter(this::isPriceReduced)
                        .collect(toList());
            }
        }
        return reducedProducts;
    }

    private boolean isPriceReduced(Product product) {
        Price price = product.getPrice();
        String now = price.getNow();
        String was = price.getWas();
        boolean isReduced = false;
        if (isNotEmpty(was) && isNotEmpty(now)) {
            isReduced = Float.valueOf(now) < Float.valueOf(was);

        }
        return isReduced;
    }
}
