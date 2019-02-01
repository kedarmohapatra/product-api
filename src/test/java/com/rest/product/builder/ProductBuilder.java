package com.rest.product.builder;

import com.rest.product.model.ColorSwathe;
import com.rest.product.model.Price;
import com.rest.product.model.Product;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Comparator.comparing;

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

    public static Product createFirstProduct(boolean sortedPrice) {
        Product product = createProduct(PRODUCT_1, PRODUCT_TITLE_1, CATEGORY_1);

        Set<Price> priceList = initializePriceList(sortedPrice);

        priceList.add(createPrice(PRODUCT_1, 10, LocalDate.of(2011, 2, 23)));
        priceList.add(createPrice(PRODUCT_1, 20, LocalDate.of(2000, 3, 23)));
        product.setPricesList(priceList);

        Set<ColorSwathe> colorSwathes = createColorSwathes(PRODUCT_1, "sku3", "sku4");
        product.setColorSwathes(colorSwathes);

        return product;
    }

    public static Product createSecondProduct(boolean sortedPrice) {
        Product product = createProduct(PRODUCT_2, PRODUCT_TITLE_2, CATEGORY_1);

        Set<Price> priceList = initializePriceList(sortedPrice);

        priceList.add(createPrice(PRODUCT_2, 10, LocalDate.of(2011, 2, 23)));
        product.setPricesList(priceList);

        Set<ColorSwathe> colorSwathes = createColorSwathes(PRODUCT_2, "sku1", "sku2");
        product.setColorSwathes(colorSwathes);

        return product;

    }

    public static Product createThirdProduct(boolean sortedPrice) {
        Product product = createProduct(PRODUCT_3, PRODUCT_TITLE_3, CATEGORY_2);

        Set<Price> priceList = initializePriceList(sortedPrice);

        priceList.add(createPrice(PRODUCT_3, 10, LocalDate.of(2011, 2, 23)));
        priceList.add(createPrice(PRODUCT_3, 12, LocalDate.of(2013, 2, 23)));
        priceList.add(createPrice(PRODUCT_3, 10, LocalDate.of(2008, 2, 23)));
        product.setPricesList(priceList);

        Set<ColorSwathe> colorSwathes = createColorSwathes(PRODUCT_3, "sku5", "sku6");
        product.setColorSwathes(colorSwathes);

        return product;

    }

    private static Set<Price> initializePriceList(boolean sortedPrice) {
        Set<Price> priceList;
        if (sortedPrice)
            priceList = new TreeSet<>(comparing(Price::getDate).reversed());
        else
            priceList = new HashSet<>();
        return priceList;
    }

    public static Product createFourthProduct(boolean sortedPrice) {
        Product product = createProduct(PRODUCT_4, PRODUCT_TITLE_4, CATEGORY_2);

        Set<Price> priceList = initializePriceList(sortedPrice);

        priceList.add(createPrice(PRODUCT_4, 15, LocalDate.of(2011, 2, 23)));
        priceList.add(createPrice(PRODUCT_4, 12, LocalDate.of(2013, 2, 23)));
        priceList.add(createPrice(PRODUCT_4, 10, LocalDate.of(2008, 2, 23)));
        product.setPricesList(priceList);

        Set<ColorSwathe> colorSwathes = createColorSwathes(PRODUCT_4, "sku7", "sku8");
        product.setColorSwathes(colorSwathes);

        return product;

    }

    private static Product createProduct(String productId, String productTitle, String category) {
        Product product = new Product();
        product.setProductId(productId);
        product.setTitle(productTitle);
        product.setCategory(category);
        return product;
    }

    private static Price createPrice(String productId, int priceValue, LocalDate date) {
        Price price = new Price();
        price.setDate(date);
        price.setPrice(priceValue);
        price.setProductId(productId);

        return price;
    }

    private static ColorSwathe createColorSwathe(String skuId, String productId, String color) {
        ColorSwathe colorSwathe = new ColorSwathe();
        colorSwathe.setSkuid(skuId);
        colorSwathe.setProductId(productId);
        colorSwathe.setColor(color);

        return colorSwathe;
    }

    private static Set<ColorSwathe> createColorSwathes(String productId, String sku1, String sku2) {
        Set<ColorSwathe> colorSwathes = new HashSet<>();
        colorSwathes.add(createColorSwathe(sku1, productId, RED));
        colorSwathes.add(createColorSwathe(sku2, productId, GREEN));
        return colorSwathes;
    }
}
