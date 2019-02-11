package com.rest.product.util;

import com.rest.product.dto.ColorDto;
import com.rest.product.dto.ProductDto;
import jl.products.model.ColorSwatch;
import jl.products.model.Product;

import java.util.List;

import static com.rest.product.util.ColorMapUtil.getColorMap;
import static com.rest.product.util.PriceLabelUtil.createPriceLabel;
import static com.rest.product.util.PriceLabelUtil.formatPrice;
import static java.util.stream.Collectors.toList;

public class ProductToDtoConverter {
    public static ProductDto convertToProductDto(Product product, String labelType) {
        ProductDto productDto = new ProductDto();

        productDto.setProductId(product.getProductId());
        productDto.setTitle(product.getTitle());
        productDto.setNowPrice(formatPrice(product.getPrice().getNow()));
        productDto.setPriceLabel(createPriceLabel(product.getPrice(), labelType));
        List<ColorDto> colorSwatches = product.getColorSwatches().stream()
                .map(ProductToDtoConverter::colorSwatheToDto)
                .collect(toList());
        productDto.setColorSwatches(colorSwatches);

        return productDto;
    }

    private static ColorDto colorSwatheToDto(ColorSwatch colorSwath) {
        ColorDto colorDto = new ColorDto();
        String color = colorSwath.getColor();
        colorDto.setColor(color);
        colorDto.setRgbColor(getColorMap().get(color));
        colorDto.setSkuid(colorSwath.getSkuId());
        return colorDto;
    }
}
