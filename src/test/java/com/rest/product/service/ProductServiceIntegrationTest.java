package com.rest.product.service;

import com.rest.product.model.Price;
import com.rest.product.model.Product;
import com.rest.product.repository.ProductRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.rest.product.builder.ProductBuilder.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@EnableJpaRepositories("com.rest.product.repository")
@EntityScan("com.rest.product.model")
@SpringBootTest
public class ProductServiceIntegrationTest {

    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setup() {
        productService = new ProductService(productRepository);
    }

    @Test
    public void shouldGetProductsWithPriceDrops() {
        int priceChangedTwice = 2;
        initializeData();

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops(CATEGORY_1);

        assertThat(productsWithPriceDrops.size(), is(equalTo(1)));
        assertPriceIsReduced(productsWithPriceDrops.get(0), priceChangedTwice);
    }

    @Test
    public void shouldGetProductsByCategory() {
        int priceChangedThreeTimes = 3;
        productRepository.save(createFirstProduct(false));
        productRepository.save(createSecondProduct(false));
        productRepository.save(createFourthProduct(false));

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops(CATEGORY_2);

        assertThat(productsWithPriceDrops.size(), is(equalTo(1)));
        assertPriceIsReduced(productsWithPriceDrops.get(0), priceChangedThreeTimes);
    }

    @Test
    public void shouldReturnEmptyListIfPriceNotReduced(){
        productRepository.save(createSecondProduct(false));
        productRepository.save(createThirdProduct(false));

        List<Product> productsWithPriceDrops = productService.getProductsWithPriceDrops(CATEGORY_1);

        assertThat(productsWithPriceDrops, is(Collections.emptyList()));
    }


    private void assertPriceIsReduced(Product product, int expectedPriceSize) {
        Set<Price> pricesList = product.getPricesList();

        Iterator<Price> priceIterator = pricesList.iterator();
        Price currentPrice = priceIterator.next();
        Price previousPrice = priceIterator.next();

        assertThat(pricesList.size(),is(equalTo(expectedPriceSize)));
        assertThat(currentPrice.getPrice(),is(Matchers.lessThan(previousPrice.getPrice())));
    }

    private void initializeData() {
        productRepository.save(createFirstProduct(false));
        productRepository.save(createSecondProduct(false));
        productRepository.save(createThirdProduct(false));
    }
}