package ru.job4;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CounterTest class.
 * @author ayuzyak.
 * @version 1.
 * @since 18.12.2016.
 */
public class CounterTest {

    /**
     * Summ of even numbers from 1 to 4
     */
    @Test
    public void whenEvenOneToFourThenSix() {
        Counter counter = new Counter();
        assertThat(counter.add(1,4), is(6));
    }

    /**
     * Summ of even numbers from 1 to 10
     */
    @Test
    public void whenEvenOneToTenThenThirty() {
        Counter counter = new Counter();
        assertThat(counter.add(1,10), is(30));
    }
}