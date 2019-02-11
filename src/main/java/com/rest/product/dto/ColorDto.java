package com.rest.product.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ColorDto {
    private String color;
    private String rgbColor;
    private String skuid;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(String rgbColor) {
        this.rgbColor = rgbColor;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ColorDto colorDto = (ColorDto) o;

        return new EqualsBuilder()
                .append(color, colorDto.color)
                .append(rgbColor, colorDto.rgbColor)
                .append(skuid, colorDto.skuid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(color)
                .append(rgbColor)
                .append(skuid)
                .toHashCode();
    }
}
