package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Array operations test class.
 * @author ayuzyak.
 * @version 1.
 * @since 21.12.2016.
 */
public class ArrayOperationsTest {

    /**
     * Bubble sorting test.
     */
    @Test
    public void bubbleSortTest() {
        ArrayOperations operations = new ArrayOperations();
        assertThat(operations.bubble(new int[]{3, 6, 1, 9, 2, 4, 8, 5, 7}), is(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    /**
     * Turn array test.
     */
    @Test
    public void turnArrayTest() {
        ArrayOperations operations = new ArrayOperations();
        assertThat(
                operations.turnArray(
                    new int[][]{{1, 2, 3},{4, 5, 6},{7, 8, 9}}
                ),
                is(new int[][]{{7, 4, 1},{8, 5, 2},{9, 6, 3}})
        );
    }

    /**
     * Duplicates test.
     */
    @Test
    public void removeDuplicatesFromArrayTest() {
        ArrayOperations operations = new ArrayOperations();
        assertThat(
                operations.removeDuplicates(new String[]{"������","������","����","����"}),
                is(new String[]{"������","����"})
        );
    }

    /**
     * Duplicates test advanced.
     */
    @Test
    public void removeDuplicatesFromArrayAdvancedTest() {
        ArrayOperations operations = new ArrayOperations();
        assertThat(
                operations.removeDuplicates(
                        new String[]{"Java","JavaZero","Java","JavaOne","Java","JavaZero","JavaTwo","JavaOne"}
                ),
                is(new String[]{"Java", "JavaZero", "JavaOne", "JavaTwo"})
        );
    }
}