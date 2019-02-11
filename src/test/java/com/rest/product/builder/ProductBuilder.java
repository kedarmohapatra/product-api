package com.rest.product.builder;

import jl.products.model.ColorSwatch;
import jl.products.model.Price;
import jl.products.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {
    private static final String PRODUCT_1 = "1";
    private static final String PRODUCT_2 = "2";
    private static final String PRODUCT_3 = "3";
    private static final String PRODUCT_4 = "4";
    private static final String PRODUCT_TITLE_1 = "product 1";
    private static final String PRODUCT_TITLE_2 = "product 2";
    private static final String PRODUCT_TITLE_3 = "product 3";
    private static final String PRODUCT_TITLE_4 = "product 4";
    private static final String RED = "red";
    private static final String GREEN = "green";

    public static final String CATEGORY_1 = "category 1";
    public static final String CATEGORY_2 = "category 2";

    public static Product createFirstProduct() {
        Product product = createProduct(PRODUCT_1, PRODUCT_TITLE_1, CATEGORY_1);
        product.setPrice(createPrice("10", "20", "", ""));

        List<ColorSwatch> colorSwathes = createColorSwathes(PRODUCT_1, "sku3", "sku4");
        product.setColorSwatches(colorSwathes);

        return product;
    }

    public static Product createSecondProduct() {
        Product product = createProduct(PRODUCT_2, PRODUCT_TITLE_2, CATEGORY_1);
        product.setPrice(createPrice("10", "", "", ""));

        List<ColorSwatch> colorSwathes = createColorSwathes(PRODUCT_1, "sku3", "sku4");
        product.setColorSwatches(colorSwathes);

        return product;

    }

    public static Product createThirdProduct() {
        Product product = createProduct(PRODUCT_3, PRODUCT_TITLE_3, CATEGORY_2);
        product.setPrice(createPrice("12", "10", "12", ""));

        List<ColorSwatch> colorSwathes = createColorSwathes(PRODUCT_1, "sku3", "sku4");
        product.setColorSwatches(colorSwathes);

        return product;

    }

    public static Product createFourthProduct() {
        Product product = createProduct(PRODUCT_4, PRODUCT_TITLE_4, CATEGORY_2);
        product.setPrice(createPrice("12", "15", "10", ""));


        List<ColorSwatch> colorSwathes = createColorSwathes(PRODUCT_1, "sku3", "sku4");
        product.setColorSwatches(colorSwathes);

        return product;

    }

    private static Product createProduct(String productId, String productTitle, String category) {
        Product product = new Product();
        product.setProductId(productId);
        product.setTitle(productTitle);
        return product;
    }

    private static Price createPrice(String now, String was, String then1, String then2) {
        Price price = new Price();
        price.setNow(now);
        price.setWas(was);
        price.setThen1(then1);
        price.setThen2(then2);

        return price;
    }

    private static ColorSwatch createColorSwathe(String skuId, String productId, String color) {
        ColorSwatch colorSwathe = new ColorSwatch();
        colorSwathe.setSkuId(skuId);
        colorSwathe.setColor(color);

        return colorSwathe;
    }

    private static List<ColorSwatch> createColorSwathes(String productId, String sku1, String sku2) {
        List<ColorSwatch> colorSwathes = new ArrayList<>();
        colorSwathes.add(createColorSwathe(sku1, productId, RED));
        colorSwathes.add(createColorSwathe(sku2, productId, GREEN));
        return colorSwathes;
    }
}
