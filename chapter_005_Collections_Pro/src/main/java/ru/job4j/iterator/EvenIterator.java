package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Iterator for return even numbers.
 *
 * @author Ayuzyak
 * @since 16.05.2017
 * @version 1.0
 */
public class EvenIterator implements Iterator<Integer> {

    /**
     * Array of ints.
     */
    private final int[] array;

    /**
     * Cursor for passing array.
     */
    private int cursor = 0;

    /**
     * Constructor for iterator.
     * @param array for passing.
     */
    public EvenIterator(int[] array) {
        this.array = array;
    }

    /**
     * Check next element.
     * @return true if iterator has next even number.
     */
    @Override
    public boolean hasNext() {
        boolean isEven = false;
        if (cursor >= array.length) {
            return false;
        }
        int evenIndex = cursor;
        while (evenIndex < array.length) {
            if (array[evenIndex] % 2 == 0) {
                isEven = true;
                evenIndex = array.length;
            } else {
                evenIndex++;
            }
        }
        return isEven;
    }

    /**
     * Get next even number.
     * @return next even number.
     */
    @Override
    public Integer next() {
        Integer element = null;
        while (cursor < array.length) {
            if (array[cursor] % 2 == 0) {
                element = array[cursor];
                break;
            }
            cursor++;
        }
        if (cursor >= array.length) {
            throw new NoSuchEvenElementException();
        }
        cursor++;
        return element;
    }
}