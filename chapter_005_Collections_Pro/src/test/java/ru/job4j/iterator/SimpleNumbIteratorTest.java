package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for SimpleNumberIterator.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 19.05.2017
 */
public class SimpleNumbIteratorTest {
    /**
     * test method hasNext().
     * If iterator has next prime number then method should return true.
     */
    @Test
    public void whenIteratorHasNextPrimeNumberThenTrue() {
        final int[] array = {4, 157, 9, 5};
        SimpleNumbIterator iterator = new SimpleNumbIterator(array);
        final boolean resultNext = iterator.hasNext();
        final boolean checkNext = true;
        assertThat(resultNext, is(checkNext));
    }

    /**
     * test method hasNext().
     * If iterator hasn't next prime number then method should return false.
     */
    @Test
    public void whenIteratorHasNotNextPrimeNumberThenFalse() {
        final int[] array = {4, 157, 9, 8};
        SimpleNumbIterator iterator = new SimpleNumbIterator(array);
        iterator.next();
        final boolean resultNext = iterator.hasNext();
        final boolean checkNext = false;
        assertThat(resultNext, is(checkNext));
    }

    /**
     * test method next().
     * Check iterator for return right prime number.
     */
    @Test
    public void whenInvokeNextThenGetRightNumber() {
        final int[] array = {4, 157, 9, 5};
        SimpleNumbIterator iterator = new SimpleNumbIterator(array);
        iterator.next();
        final int resultElement = iterator.next();
        final int checkElement = 5;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test method next().
     * If iterator hasn't prime numbers and invoking next() then getting NoSuchSimpleElementException.
     */
    @Test
    public void whenInvokeNextFromIteratorWithoutPrimeNumbersThenGetNoSuchSimpleElementException() {
        final int[] array = {4, 157, 9, 6};
        SimpleNumbIterator iterator = new SimpleNumbIterator(array);
        iterator.next();
        String resultException = null;
        try {
            iterator.next();
        } catch (NoSuchSimpleElementException e) {
            resultException = "No prime elements";
        }
        final String checkException = "No prime elements";
        assertThat(resultException, is(checkException));
    }
}