package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for ArrayContainer.
 *
 * @author Ayuzyak
 * @since 02.06.2017
 * @version 1.0
 */
public class LinkedContainerTest {
    /**
     * test method add() and get().
     * Adding element and get it by index.
     */
    @Test
    public void whenAddElementThenGetIt() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 100; i++) {
            container.add(i);
        }
        final Integer resultElement = container.get(50);
        final Integer checkElement = 50;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test method delete() and contains().
     * Delete element from container, get it and check for contains.
     */
    @Test
    public void whenDeleteElementThenReturnElementAndContainerReturnFalse() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 15; i++) {
            container.add(i);
        }
        final Integer resultElement = container.remove(10);
        final Integer checkElement = 10;
        final boolean resultHasElement = container.contains(10);
        final boolean checkHasElement = false;
        assertThat(resultElement, is(checkElement));
        assertThat(resultHasElement, is(checkHasElement));
    }

    /**
     * test method add() by index and get() by index.
     * Add element by index to container and get it by index.
     */
    @Test
    public void whenAddElementByIndexAndGetByIndexThenReturnThisElement() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 15; i++) {
            container.add(i);
        }
        container.add(5, 150);
        final Integer resultElement = container.get(5);
        final Integer checkElement = 150;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test method contains().
     * If container has not element return false.
     */
    @Test
    public void whenContainerHasNotElementAndInvokeContainsThisElementThenReturnFalse() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 15; i++) {
            container.add(i);
        }
        final boolean result = container.contains(1000);
        final boolean check = false;
        assertThat(result, is(check));
    }

    /**
     * test iterator method hasNext().
     * If iterator has elements then return true.
     */
    @Test
    public void whenIteratorHasElementsThenReturnTrue() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 15; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        final boolean resultElement = iterator.hasNext();
        final boolean checkElement = true;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test iterator method hasNext().
     * If iterator has not elements then return false.
     */
    @Test
    public void whenIteratorHasNotElementsThenReturnFalse() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 3; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        final boolean resultElement = iterator.hasNext();
        final boolean checkElement = false;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test iterator method next().
     * Get element from iterator.
     */
    @Test
    public void whenGetElementFromIteratorThenReturnElement() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 3; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        final Integer resultElement = iterator.next();
        final Integer checkElement = 2;
        assertThat(resultElement, is(checkElement));
    }

    /**
     * test iterator method next().
     * When invoke next() fo iterator that has not elements get NoSuchElementException.
     */
    @Test
    public void whenIteratorHasNotElementsAndInvokeNextThenReturnNoSuchElementException() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 3; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        String resultException = null;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            resultException = "No more elements";
        }
        final String checkException = "No more elements";
        assertThat(resultException, is(checkException));
    }

    /**
     * test for getting ConcurrentModificationException.
     */
    @Test
    public void whenAddElementsAndInvokeNextFromIteratorThenGetConcurrentModificationException() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        for (int i = 0; i < 3; i++) {
            container.add(i);
        }
        Iterator<Integer> iterator = container.iterator();
        iterator.next();
        String resultException = null;
        container.add(100);
        try {
            iterator.next();
        } catch (ConcurrentModificationException e) {
            resultException = "ConcurrentMod";
        }
        final String checkException = "ConcurrentMod";
        assertThat(resultException, is(checkException));
    }
}