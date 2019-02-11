package com.rest.product.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan({"com.rest.product.controller","com.rest.product.service"})
@Import(AppConfig.class)
@SpringBootApplication
public class ProductsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApiApplication.class, args);
    }
}
