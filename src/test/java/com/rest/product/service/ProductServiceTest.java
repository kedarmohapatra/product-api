package com.rest.product.service;

import com.rest.product.model.Product;
import com.rest.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rest.product.builder.ProductBuilder.*;
import static com.rest.product.repository.ProductSpecifications.hasPriceChangedOrdered;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService(productRepository);
    }

    @Test
    public void shouldReturnEmptyListIfPriceHasNotDropped(){
        when(productRepository.findAll(any(Specification.class))).thenReturn(new ArrayList<>());

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops(CATEGORY_1);

        assertThat(productsWithPriceDrops, is(emptyList()));
    }

    @Test
    public void shouldReturnProductsIfPriceHasDropped(){
        List<Product> products = Arrays.asList(createFirstProduct(true), createThirdProduct(true));
        when(productRepository.findAll(any(Specification.class))).thenReturn(products);

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops(CATEGORY_1);

        assertThat(productsWithPriceDrops.size(), is(equalTo(1)));
    }
}