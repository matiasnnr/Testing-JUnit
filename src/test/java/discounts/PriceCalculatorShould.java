package discounts;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PriceCalculatorShould {

    @Test
    public void total_zero_when_there_are_prices() {

        PriceCalculator priceCalculator = new PriceCalculator();

        assertThat(priceCalculator.getTotal(), is(0.0));
    }

    @Test
    public void total_is_the_sum_of_prices() {

        PriceCalculator priceCalculator = new PriceCalculator();

        priceCalculator.addPrice(10.2);
        priceCalculator.addPrice(15.5);

        assertThat(priceCalculator.getTotal(), is(25.7));
    }

    @Test
    public void apply_discount_to_prices() {

        PriceCalculator priceCalculator = new PriceCalculator();

        priceCalculator.addPrice(100);
        priceCalculator.addPrice(50);
        priceCalculator.addPrice(50);

        priceCalculator.setDiscount(25);

        assertThat(priceCalculator.getTotal(), is(150.0));
    }
}