package com.rest.product.config;

import com.rest.product.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${jl.product.rest.url}")
    private String restURI;

    @Bean
    public ProductService productService(RestTemplate restTemplate){
        return new ProductService(restTemplate, restURI);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
