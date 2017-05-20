package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for IteratorIterators.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 19.05.2017
 */
public class IteratorIteratorsTest {

    /**
     * test method convert(Iterator<Iterator<Integer>> it).
     * If invoke convret() to Iterator<Iterator<Integer>> and return Iterator<Integer> then compare elements that it
     * contains.
     */
    @Test
    public void whenIteratorOfIteratorsconvertToIterator() {
        final List<Integer> firstList = new ArrayList<>(Arrays.asList(1, 2, 3));
        final int[] secondList = {3, 5, 7};
        final List<Integer> thirdList = new ArrayList<>(Arrays.asList(6, 8, 9));
        Iterator<Integer> it1 = firstList.iterator();
        EvenIterator it2 = new EvenIterator(secondList);
        Iterator<Integer> it3 = thirdList.iterator();
        List<Iterator<Integer>> iteratorList = new ArrayList<>(Arrays.asList(it1, it2, it3));

        Iterator<Iterator<Integer>> mainIterator = iteratorList.iterator();
        IteratorIterators iteratorIterators = new IteratorIterators();
        Iterator<Integer> resultIterator = iteratorIterators.convert(mainIterator);
        ArrayList<Integer> resultList = new ArrayList<>();
        while (resultIterator.hasNext()) {
            resultList.add(resultIterator.next());
        }

        ArrayList<Integer> checkList = new ArrayList<>(Arrays.asList(1, 2, 3, 6, 8, 9));
        assertThat(resultList, is(checkList));
    }

    /**
     * test method hasNext().
     * If iterator has next number then method should return true.
     */
    @Test
    public void whenIteratorHasNextNumberThenTrue() {
        final List<Integer> firstList = new ArrayList<>(Arrays.asList(1, 2, 3));
        final int[] secondList = {3, 5, 7};
        final List<Integer> thirdList = new ArrayList<>(Arrays.asList(6, 8, 9));
        Iterator<Integer> it1 = firstList.iterator();
        EvenIterator it2 = new EvenIterator(secondList);
        Iterator<Integer> it3 = thirdList.iterator();
        List<Iterator<Integer>> iteratorList = new ArrayList<>(Arrays.asList(it1, it2, it3));

        Iterator<Iterator<Integer>> mainIterator = iteratorList.iterator();
        IteratorIterators iteratorIterators = new IteratorIterators();
        Iterator<Integer> resultIterator = iteratorIterators.convert(mainIterator);

        resultIterator.next();
        resultIterator.next();
        final boolean resultNext = resultIterator.hasNext();
        final boolean checkNext = true;
        assertThat(resultNext, is(checkNext));
    }

    /**
     * test method hasNext().
     * If iterator hasn't next number then method should return false.
     */
    @Test
    public void whenIteratorHasNotNextNumberThenFalse() {
        final List<Integer> firstList = new ArrayList<>(Arrays.asList(1, 2, 3));
        final int[] secondList = {3, 5, 7};
        final List<Integer> thirdList = new ArrayList<>(Arrays.asList(6, 8, 9));
        Iterator<Integer> it1 = firstList.iterator();
        EvenIterator it2 = new EvenIterator(secondList);
        Iterator<Integer> it3 = thirdList.iterator();
        List<Iterator<Integer>> iteratorList = new ArrayList<>(Arrays.asList(it1, it2, it3));

        Iterator<Iterator<Integer>> mainIterator = iteratorList.iterator();
        IteratorIterators iteratorIterators = new IteratorIterators();
        Iterator<Integer> resultIterator = iteratorIterators.convert(mainIterator);

        for (int i = 0; i < 6; i++) {
            resultIterator.next();
        }
        final boolean resultNext = resultIterator.hasNext();
        final boolean checkNext = false;
        assertThat(resultNext, is(checkNext));
    }

    /**
     * test method next().
     * Check iterator for return right number.
     */
    @Test
    public void whenInvokeNextThenGetRightNumber() {
        final List<Integer> firstList = new ArrayList<>(Arrays.asList(1, 2, 3));
        final int[] secondList = {3, 5, 7};
        final List<Integer> thirdList = new ArrayList<>(Arrays.asList(6, 8, 9));
        Iterator<Integer> it1 = firstList.iterator();
        EvenIterator it2 = new EvenIterator(secondList);
        Iterator<Integer> it3 = thirdList.iterator();
        List<Iterator<Integer>> iteratorList = new ArrayList<>(Arrays.asList(it1, it2, it3));

        Iterator<Iterator<Integer>> mainIterator = iteratorList.iterator();
        IteratorIterators iteratorIterators = new IteratorIterators();
        Iterator<Integer> resultIterator = iteratorIterators.convert(mainIterator);

        for (int i = 0; i < 4; i++) {
            resultIterator.next();
        }
        final int resultElement = resultIterator.next();
        final int checkElement = 8;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test method next().
     * If iterator hasn't numbers and invoking next() then getting NoSuchElementException.
     */
    @Test
    public void whenInvokeNextFromEmptyIteratorThenGetNoSuchElementException() {
        final List<Integer> firstList = new ArrayList<>(Arrays.asList(1, 2, 3));
        final int[] secondList = {3, 5, 7};
        final List<Integer> thirdList = new ArrayList<>(Arrays.asList(6, 8, 9));
        Iterator<Integer> it1 = firstList.iterator();
        EvenIterator it2 = new EvenIterator(secondList);
        Iterator<Integer> it3 = thirdList.iterator();
        List<Iterator<Integer>> iteratorList = new ArrayList<>(Arrays.asList(it1, it2, it3));

        Iterator<Iterator<Integer>> mainIterator = iteratorList.iterator();
        IteratorIterators iteratorIterators = new IteratorIterators();
        Iterator<Integer> resultIterator = iteratorIterators.convert(mainIterator);

        for (int i = 0; i < 6; i++) {
            resultIterator.next();
        }
        String resultException = null;
        try {
            resultIterator.next();
        } catch (NoSuchElementException e) {
            resultException = "No more elements";
        }
        final String checkException = "No more elements";
        assertThat(resultException, is(checkException));
    }
}