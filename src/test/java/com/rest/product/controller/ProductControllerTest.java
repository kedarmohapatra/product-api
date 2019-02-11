package com.rest.product.controller;

import com.rest.product.dto.ProductDto;
import com.rest.product.service.ProductService;
import jl.products.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.rest.product.builder.ProductBuilder.createFirstProduct;
import static com.rest.product.builder.ProductBuilder.createFourthProduct;
import static com.rest.product.util.ProductToDtoConverter.convertToProductDto;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {


    private static final String PERC_DSCOUNT = "ShowPercDscount";
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productController = new ProductController(productService);
    }

    @Test
    public void shouldReturnCorrectlyFormattedPrices(){
        List<Product> productList = asList(createFirstProduct(), createFourthProduct());
        when(productService.getProductsWithPriceDrops()).thenReturn(productList);

        List<ProductDto> productsWithPriceDrop = productController.getProductsWithPriceDrop(PERC_DSCOUNT);

        List<ProductDto> productDtoList = createDtoList(productList);
        assertThat(productsWithPriceDrop, is(equalTo(productDtoList)));
    }

    @Test
    public void shouldReturnEmptyDto(){
        when(productService.getProductsWithPriceDrops()).thenReturn(new ArrayList<>());

        List<ProductDto> productsWithPriceDrop = productController.getProductsWithPriceDrop(PERC_DSCOUNT);

        assertThat(productsWithPriceDrop, is(equalTo(new ArrayList<>())));
    }

    private List<ProductDto> createDtoList(List<Product> productList) {
        return productList
                .stream()
                .map(product -> convertToProductDto(product, PERC_DSCOUNT))
                .collect(toList());
    }

}