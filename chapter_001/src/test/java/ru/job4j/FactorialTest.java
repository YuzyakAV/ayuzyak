package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CounterTest class.
 * @author ayuzyak.
 * @version 1.
 * @since 18.12.2016.
 */
public class FactorialTest {

    /**
     * Calculate factorial of 5
     */
    @Test
    public void whenFactorialIsFiveThenOneHundredAndTwenty() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calculate(5), is(120));
    }

    /**
     * Calculate factorial of 6
     */
    @Test
    public void whenFactorialIsSixThenSevenHundredAndTwenty() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calculate(6), is(720));
    }
}