package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Max test class.
 * @author ayuzyak.
 * @version 1.
 * @since 14.12.2016.
 */
public class MaxTest {

    /**
     * Max in two.
     */
    @Test
    public void whenSevenFiveThenMaxSeven() {
        Max max = new Max();
        assertThat(max.max(7, 5), is(7));
    }

    /**
     * Max in three.
     */
    @Test
    public void whenSixOneTenThenMaxTen() {
        Max max = new Max();
        assertThat(max.max(6, 1, 10), is(10));
    }
}