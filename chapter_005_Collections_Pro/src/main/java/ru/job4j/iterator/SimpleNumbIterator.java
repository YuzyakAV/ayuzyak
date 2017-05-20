package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Iterator for return prime numbers.
 *
 * @author Ayuzyak
 * @since 20.05.2017
 * @version 1.0
 */
public class SimpleNumbIterator implements Iterator<Integer> {

    /**
     * array of ints.
     */
    private final int[] array;

    /**
     * cursor for array passing.
     */
    private int cursor = 0;

    /**
     * Constructor for iterator.
     * @param array for passing.
     */
    public SimpleNumbIterator(int[] array) {
        this.array = array;
    }

    /**
     * Check next number.
     * @return true if iterator has next prime number.
     */
    @Override
    public boolean hasNext() {
        boolean nextIsSimple = false;
        if (cursor >= array.length) {
            return false;
        }
        int index = cursor;
        while (index < array.length) {
            if (SimpleN.checkSimple(array[index])) {
                nextIsSimple = true;
                index = array.length;
            } else {
                index++;
            }
        }
        return nextIsSimple;
    }

    /**
     * Get next prime number.
     * @return next prime number.
     */
    @Override
    public Integer next() {
        Integer element = null;
        while (cursor < array.length) {
            if (SimpleN.checkSimple(array[cursor])) {
                element = array[cursor];
                break;
            }
            cursor++;
        }
        if (cursor >= array.length) {
            throw new NoSuchSimpleElementException();
        }
        cursor++;
        return element;
    }
}