package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for SimpleN.
 *
 * @author Ayuzyak
 * @since 18.05.2017
 * @version 1.0
 */
public class SimpleNTest {

    /**
     * test method checkSimple(int n).
     * If n is simple then return true.
     */
    @Test
    public void whenNumberIsSimpleThenReturnTrue() {
        final int n = 13;
        final boolean resultSimple = SimpleN.checkSimple(n);
        final boolean checkSimple = true;
        assertThat(resultSimple, is(checkSimple));
    }

    /**
     * test method checkSimple(int n).
     * If n isn't simple then return false.
     */
    @Test
    public void whenNumberIsNotSimpleThenReturnFalse() {
        final int n = 16;
        final boolean resultSimple = SimpleN.checkSimple(n);
        final boolean checkSimple = false;
        assertThat(resultSimple, is(checkSimple));
    }
}