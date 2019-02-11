package com.rest.product.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class ProductDto {

    private String productId;
    private String title;
    private List<ColorDto> colorSwatches;
    private String nowPrice;
    private String priceLabel;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ColorDto> getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(List<ColorDto> colorSwatches) {
        this.colorSwatches = colorSwatches;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProductDto that = (ProductDto) o;

        return new EqualsBuilder()
                .append(productId, that.productId)
                .append(title, that.title)
                .append(colorSwatches, that.colorSwatches)
                .append(nowPrice, that.nowPrice)
                .append(priceLabel, that.priceLabel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(productId)
                .append(title)
                .append(colorSwatches)
                .append(nowPrice)
                .append(priceLabel)
                .toHashCode();
    }
}
