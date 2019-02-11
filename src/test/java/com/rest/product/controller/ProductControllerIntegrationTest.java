package com.rest.product.controller;

import com.rest.product.config.ProductsApiApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductsApiApplication.class)
@WebAppConfiguration
public class ProductControllerIntegrationTest {

    private static final String API_ROOT = "http://localhost:8080/productswithpricedrops";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldGetProductsWithPriceReduction() throws Exception {
        mockMvc.perform(get(API_ROOT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].productId", is("3421340")))
                .andExpect(jsonPath("$[0].title", is("Phase Eight Beatrix Floral Printed Dress, Cream/Red")))
                .andExpect(jsonPath("$[0].nowPrice", is("£99")))
                .andExpect(jsonPath("$[0].priceLabel", is("Was £140, now £99")));
    }

    @Test
    public void shouldGetProductsWithPercentDiscountLabel() throws Exception {
        mockMvc.perform(get(API_ROOT+"?labelType=ShowPercDscount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].priceLabel", is("29% off - now £99")));
    }

    @Test
    public void shouldGetProductsWithWasThenNowLabel() throws Exception {
        mockMvc.perform(get(API_ROOT+"?labelType=ShowWasThenNow"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[1].priceLabel", is("Was £89, then £149, now £74")));
    }

}