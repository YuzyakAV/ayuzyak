package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for MySimpleSet.
 *
 * @author Ayuzyak
 * @since 05.06.2017
 * @version 1.0
 */
public class MySimpleSetTest {
    /**
     * test method add() and next() by Iterator.
     * Adding element and get it
     */
    @Test
    public void whenAddElementThenGetIt() {
        MySimpleSet<Integer> set = new MySimpleSet<>();
        final Integer first = 10;
        final Integer second = 50;
        set.add(first);
        set.add(second);
        Iterator<Integer> iterator = set.iterator();
        iterator.next();
        final Integer resultElement = iterator.next();
        final Integer checkElement;
        final int indexFirst = first.hashCode() % set.getTabSize();
        final int indexSecond = second.hashCode() % set.getTabSize();
        if (indexFirst > indexSecond) {
            checkElement = first;
        } else {
            checkElement = second;
        }
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test adding three same elements.
     * When invoke two times next() from iterator then should throws NoSuchElementException,
     * because Set contains only unique elements.
     */
    @Test
    public void whenAddThreeSameElementsAndInvokeNextThreeTimesThenGetNoSuchElementException() {
        MySimpleSet<Integer> set = new MySimpleSet<>();
        set.add(100);
        set.add(100);
        set.add(100);
        Iterator<Integer> iterator = set.iterator();
        String resultException = null;
        iterator.next();
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            resultException = "Unique";
        }
        final String checkException = "Unique";
        assertThat(resultException, is(checkException));
    }

    /**
     * test growTab().
     * If adding 13 elements tabSize should be eaquals 32.
     */
    @Test
    public void whenAddTwelveElementsThenTabSizeShouldBeEqualsThirtyTwo() {
        MySimpleSet<Integer> set = new MySimpleSet<>();
        for (int i = 0; i < 13; i++) {
            set.add(i);
        }
        final int resultTabSize = set.getTabSize();
        final int checkTabSize = 32;
        assertThat(resultTabSize, is(checkTabSize));
    }

    /**
     * test Iterator's method hasNext() when iterator hasn't elements.
     */
    @Test
    public void whenAddOneElementAndInvokeTwoTimesHasNextThenSecondCaseReturnFalse() {
        MySimpleSet<Integer> set = new MySimpleSet<>();
        set.add(3);
        Iterator<Integer> iterator = set.iterator();
        iterator.next();
        final boolean resultHasNext = iterator.hasNext();
        final boolean checkHasNext = false;
        assertThat(resultHasNext, is(checkHasNext));
    }

    /**
     * test Iterator's method hasNext() when iterator has elements.
     */
    @Test
    public void whenAddOneElementAndInvokeHasNextThenReturnFalse() {
        MySimpleSet<Integer> set = new MySimpleSet<>();
        set.add(3);
        Iterator<Integer> iterator = set.iterator();
        final boolean resultHasNext = iterator.hasNext();
        final boolean checkHasNext = true;
        assertThat(resultHasNext, is(checkHasNext));
    }
}