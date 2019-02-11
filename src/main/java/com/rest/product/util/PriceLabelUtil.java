package com.rest.product.util;

import jl.products.model.Price;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class PriceLabelUtil {


    public static String createPriceLabel(Price price, String labelType) {
        String label;

        switch (labelType) {
            case "ShowWasNow":
                label = createShowWasNow(price);
                break;
            case "ShowWasThenNow":
                label = createShowWasThenNow(price);
                break;
            case "ShowPercDscount":
                label = createShowPercentageDiscount(price);
                break;
            default:
                label = createShowWasNow(price);
                break;
        }
        return label;
    }

    private static String createShowPercentageDiscount(Price price) {
        String nowPrice = price.getNow();
        float nowPriceFloat = Float.parseFloat(nowPrice);
        float wasPriceFloat = Float.parseFloat(price.getWas());

        float discountPercentage = (wasPriceFloat - nowPriceFloat) * 100 / wasPriceFloat;

        String nowPriceFormatted = formatPrice(nowPrice);

        return String.format("%d%% off - now %s", Math.round(discountPercentage), nowPriceFormatted);
    }

    private static String createShowWasThenNow(Price price) {
        String nowPrice = formatPrice(price.getNow());
        String wasPrice = formatPrice(price.getWas());
        String then2Price = formatPrice(price.getThen2());
        String then1Price = formatPrice(price.getThen1());
        String prevPrice = isNotBlank(then2Price) ? then2Price : then1Price;

        String label = "";
        if (isEmpty(prevPrice)) {
            label = String.format("Was %s, now %s", wasPrice, nowPrice);
        } else {
            label = String.format("Was %s, then %s, now %s", prevPrice, wasPrice, nowPrice);
        }

        return label;
    }

    private static String createShowWasNow(Price price) {
        String nowPrice = formatPrice(price.getNow());
        String previousPrice = formatPrice(price.getWas());
        return String.format("Was %s, now %s", previousPrice, nowPrice);
    }

    public static String formatPrice(String priceString) {
        String priceFormat = "";
        if (isNotBlank(priceString)) {
            float price = Float.valueOf(priceString);
            priceFormat = "£";
            if (Math.round(price) == price && price < 10) {
                priceFormat = priceFormat + String.format("%.2f", price);
            } else if (Math.round(price) == price) {
                priceFormat = priceFormat + Math.round(price);
            } else {
                priceFormat = priceFormat + String.format("%s", price);
            }
        }
        return priceFormat;
    }
}
