package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * test for IteratorTwoDimensionalArray.
 *
 * @author Ayuzyak
 * @since 14.05.2017
 * @version 1.0
 */
public class IteratorTwoDimensoinalArrayTest {

    /**
     * test method hasNext().
     * If iterator hasn't next element then method should return false.
     */
    @Test
    public void whenIteratorHasNotNextElementThenFalse() {
        final String[][] array = {
                {"1", "2"},
                {"3", "4"}};
        IteratorTwoDimensoinalArray<String> iterator = new IteratorTwoDimensoinalArray<>(array);
        for (int i = 0; i < 4; i++) {
            iterator.next();
        }
        final boolean resultNext = iterator.hasNext();
        final boolean checkNext = false;
        assertThat(resultNext, is(checkNext));
    }

    /**
     * test method next().
     * Check iterator for return right element.
     */
    @Test
    public void whenInvokeNextThenGetRightElement() {
        final String[][] array = {
                {"1", "2"},
                {"3", "4"}};
        IteratorTwoDimensoinalArray<String> iterator = new IteratorTwoDimensoinalArray<>(array);
        for (int i = 0; i < 2; i++) {
            iterator.next();
        }
        final String resultElement = iterator.next();
        final String checkElement = "3";
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test method next().
     * If iterator hasn't elements and invoking next() then getting NoSuchElementException.
     */
    @Test
    public void whenInvokeNextFromEmptyIteratorThenGetNoSuchElementException() {
        final String[][] array = {
                {"1", "2"},
                {"3", "4"}};
        IteratorTwoDimensoinalArray<String> iterator = new IteratorTwoDimensoinalArray<>(array);
        for (int i = 0; i < 4; i++) {
            iterator.next();
        }
        String resultException = null;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            resultException = "No elements";
        }
        final String checkException = "No elements";
        assertThat(resultException, is(checkException));
    }
}