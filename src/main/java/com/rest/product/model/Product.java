package com.rest.product.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Product.fetchAll",
                attributeNodes = {@NamedAttributeNode("pricesList"), @NamedAttributeNode("colorSwathes")})
})
public class Product {

    @Id
    private String productId;
    private String title;
    private String category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Set<ColorSwathe> colorSwathes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    @OrderBy("date DESC")
    private Set<Price> pricesList;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<ColorSwathe> getColorSwathes() {
        return colorSwathes;
    }

    public void setColorSwathes(Set<ColorSwathe> colorSwathes) {
        this.colorSwathes = colorSwathes;
    }

    public Set<Price> getPricesList() {
        return pricesList;
    }

    public void setPricesList(Set<Price> pricesList) {
        this.pricesList = pricesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(productId, product.productId)
                .append(title, product.title)
                .append(category, product.category)
                .append(colorSwathes, product.colorSwathes)
                .append(pricesList, product.pricesList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(productId)
                .append(title)
                .append(category)
                .append(colorSwathes)
                .append(pricesList)
                .toHashCode();
    }
}
