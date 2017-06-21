package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for MyMap.
 *
 * @author Ayuzyak
 * @since 21.06.2017
 * @version 1.0
 */
public class MyMapTest {
    /**
     * test method add() and get().
     * Adding element and get it value.
     */
    @Test
    public void whenAddElementThenGetItValue() {
        MyMap<Integer, String> map = new MyMap<>();
        map.insert(1, "Elem");
        final String resultValue = map.get(1);
        final String checkValue = "Elem";
        assertThat(resultValue, is(checkValue));
    }

    /**
     * test map for uniqueness.
     * If add three same keys and invoke two times next() from iterator then should return NOSuchElementException.
     */
    @Test
    public void whenAddThreeSameElementsAndInvokeTwoTimesNextThenGetNoSuchElementException() {
        MyMap<Integer, String> map = new MyMap<>();
        map.insert(1, "Elem1");
        map.insert(1, "Elem2");
        map.insert(1, "Elem3");
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        String resultException = null;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            resultException = "Unique";
        }
        final String checkException = "Unique";
        assertThat(resultException, is(checkException));
    }

    /**
     * test contain().
     */
    @Test
    public void whenAddElementAndInvokeContainsThisElementThenReturnTrue() {
        MyMap<Integer, String> map = new MyMap<>();
        map.insert(1, "Elem1");
        map.insert(2, "Elem2");
        final boolean resultOfContains = map.containsKey(1);
        final boolean checkOfContains = true;
        assertThat(resultOfContains, is(checkOfContains));
    }

    /**
     * test delete().
     * If remove key and then invoke contains() then it should return false.
     */
    @Test
    public void whenRemoveElementAndInvokeContainsThisElementThenReturnFalse() {
        MyMap<Integer, String> map = new MyMap<>();
        map.insert(1, "Elem1");
        map.insert(2, "Elem2");
        map.delete(1);
        final boolean resultOfContains = map.containsKey(1);
        final boolean checkOfContains = false;
        assertThat(resultOfContains, is(checkOfContains));
    }

    /**
     * test Iterator's method hasNext() when iterator hasn't elements.
     */
    @Test
    public void whenAddOneElementAndInvokeTwoTimesHasNextThenSecondCaseReturnFalse() {
        MyMap<Integer, String> map = new MyMap<>();
        map.insert(10, "Value");
        Iterator<Integer> iterator = map.iterator();
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
        MyMap<Integer, String> map = new MyMap<>();
        map.insert(10, "Value");
        Iterator<Integer> iterator = map.iterator();
        final boolean resultHasNext = iterator.hasNext();
        final boolean checkHasNext = true;
        assertThat(resultHasNext, is(checkHasNext));
    }
}