package com.rest.product.controller;

import com.rest.product.dto.ProductDto;
import com.rest.product.service.ProductService;
import jl.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rest.product.util.ProductToDtoConverter.convertToProductDto;
import static java.util.stream.Collectors.toList;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/productswithpricedrops")
    public List<ProductDto> getProductsWithPriceDrop(@RequestParam(value = "labelType", defaultValue = "ShowWasNow") String labelType) {

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops();

        List<ProductDto> productDtos = productsWithPriceDrops
                .stream()
                .map(product -> convertToProductDto(product, labelType))
                .collect(toList());

        return productDtos;
    }
}
