package com.rest.product.util;

import jl.products.model.Price;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PriceLabelUtilTest {

    @Test
    public void shouldCreatePriceLabelForWasNow() {
        Price price = new Price();
        price.setWas("9");
        price.setNow("5");
        String showWasNow = PriceLabelUtil.createPriceLabel(price, "ShowWasNow");

        assertThat(showWasNow, is("Was £9.00, now £5.00"));
    }

    @Test
    public void shouldCreatePriceLabelForWasThenNow() {
        Price price = new Price();
        price.setWas("9");
        price.setNow("5");
        price.setThen2("10");
        String showWasThenNow = PriceLabelUtil.createPriceLabel(price, "ShowWasThenNow");

        assertThat(showWasThenNow, is("Was £10, then £9.00, now £5.00"));
    }

    @Test
    public void shouldCreatePriceLabelForWasThenNowIncludesThen1IfThen2NotPresent() {
        Price price = new Price();
        price.setWas("9");
        price.setNow("5");
        price.setThen1("10");
        String showWasThenNow = PriceLabelUtil.createPriceLabel(price, "ShowWasThenNow");

        assertThat(showWasThenNow, is("Was £10, then £9.00, now £5.00"));
    }

    @Test
    public void shouldCreatePriceLabelForWasThenNowShowsWasNowIfThenPricesNotPresent() {
        Price price = new Price();
        price.setWas("9");
        price.setNow("5");
        String showWasNow = PriceLabelUtil.createPriceLabel(price, "ShowWasThenNow");

        assertThat(showWasNow, is("Was £9.00, now £5.00"));
    }

    @Test
    public void shouldCreatePriceLabelForPrecentDiscount() {
        Price price = new Price();
        price.setWas("9");
        price.setNow("5");
        String showPercDscount = PriceLabelUtil.createPriceLabel(price, "ShowPercDscount");

        assertThat(showPercDscount, is("44% off - now £5.00"));
    }

    @Test
    public void shouldShowWasNowForInvalidLabel() {
        Price price = new Price();
        price.setWas("9");
        price.setNow("5");
        String showWasNow = PriceLabelUtil.createPriceLabel(price, "not valid");

        assertThat(showWasNow, is("Was £9.00, now £5.00"));
    }

    @Test
    public void shouldAddDecimalIfPriceLessThan10() {
        String price = PriceLabelUtil.formatPrice("2");

        assertThat(price, is("£2.00"));
    }

    @Test
    public void shouldNotAddDecimalIfPriceGreaterThan10() {
        String price = PriceLabelUtil.formatPrice("10");

        assertThat(price, is("£10"));
    }

    @Test
    public void shouldNotRemoveDecimalIfPriceGreaterThan10() {
        String price = PriceLabelUtil.formatPrice("10.234");

        assertThat(price, is("£10.234"));
    }
}