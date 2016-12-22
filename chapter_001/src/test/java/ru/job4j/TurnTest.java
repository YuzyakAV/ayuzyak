package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author ayuzyak.
 * @version 1.
 * @since 21.12.2016.
 */
public class TurnTest {

    /**
     * Reverse array test.
     */
    @Test
    public void reverseArrayTest() {
        Turn turn = new Turn();
        assertThat(turn.back(new int[]{1, 2, 3, 4, 5, 6, 7}), is(new int[]{7, 6, 5, 4, 3, 2, 1}));
    }
}