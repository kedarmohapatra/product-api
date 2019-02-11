package com.rest.product.util;

import com.rest.product.dto.ColorDto;
import com.rest.product.dto.ProductDto;
import org.junit.Test;

import java.util.List;

import static com.rest.product.builder.ProductBuilder.createFirstProduct;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProductToDtoConverterTest {

    @Test
    public void shouldConvertToProductDto(){
        ProductDto productDto = ProductToDtoConverter.convertToProductDto(createFirstProduct(), "ShowWasNow");

        ProductDto expectedDto = getExpectedDto();
        assertThat(productDto, is(equalTo(expectedDto)));
    }

    private ProductDto getExpectedDto() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId("1");
        productDto.setTitle("product 1");
        productDto.setNowPrice("£10");
        productDto.setPriceLabel("Was £20, now £10");
        productDto.setColorSwatches(getColorSwathes());
        return productDto;
    }

    private List<ColorDto> getColorSwathes() {
        ColorDto colorDto1 = new ColorDto();
        colorDto1.setSkuid("sku3");
        colorDto1.setRgbColor("#FF0000");
        colorDto1.setColor("red");

        ColorDto colorDto2 = new ColorDto();
        colorDto2.setSkuid("sku4");
        colorDto2.setRgbColor("#339900");
        colorDto2.setColor("green");

        return asList(colorDto1,colorDto2);
    }

}