package com.rest.product.service;

import jl.products.model.JlProducts;
import jl.products.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.rest.product.builder.ProductBuilder.createFirstProduct;
import static com.rest.product.builder.ProductBuilder.createThirdProduct;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private final String restUri = "restUri";

    @Mock
    private RestTemplate restTemplate;
    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService(restTemplate, restUri);
    }

    @Test
    public void shouldReturnEmptyListIfPriceHasNotDropped(){
        JlProducts jlProducts = new JlProducts();
        when(restTemplate.getForObject(restUri, JlProducts.class)).thenReturn(jlProducts);

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops();

        assertThat(productsWithPriceDrops, is(emptyList()));
    }

    @Test
    public void shouldReturnEmptyListIfExceptionIsThrown(){
        when(restTemplate.getForObject(restUri, JlProducts.class)).thenThrow(RestClientException.class);

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops();

        assertThat(productsWithPriceDrops, is(emptyList()));
    }

    @Test
    public void shouldReturnProductsIfPriceHasDropped(){
        List<Product> products = Arrays.asList(createFirstProduct(), createThirdProduct());
        JlProducts jlProducts = new JlProducts();
        jlProducts.setProducts(products);
        when(restTemplate.getForObject(restUri, JlProducts.class)).thenReturn(jlProducts);

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops();

        assertThat(productsWithPriceDrops.size(), is(equalTo(1)));
        assertThat(productsWithPriceDrops.get(0), is(equalTo(createFirstProduct())));
    }
}