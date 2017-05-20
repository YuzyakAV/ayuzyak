package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for two-dimensional array.
 *
 * @author Ayuzyak
 * @since 14.05.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class IteratorTwoDimensoinalArray<E> implements Iterator<E> {
    /**
     * Two dimensional array.
     */
    private final E[][] array;

    /**
     * Cursor for passage subarray elements.
     */
    private int cursorX = 0;

    /**
     * Cursor for passage arrays.
     */
    private int cursorY = 0;

    /**
     * Constructor for Iterator.
     * @param array for constructing Iterator.
     */
    public IteratorTwoDimensoinalArray(final E[][] array) {
        this.array = array;
    }

    /**
     * Check next element.
     * @return true if iterator has next element.
     */
    @Override
    public boolean hasNext() {
        return cursorY < array.length;
        return cursorX > array.length;
    }

    /**
     * Get next element.
     * @return next element.
     */
    @Override
    public E next() {
        E element;
        if (cursorY < array.length) {
            element = array[cursorY][cursorX++];
        } else {
            throw new NoSuchElementException();
        }
        if (cursorX >= array[cursorY].length) {
            cursorX = 0;
            cursorY++;
        }
        return element;
    }
}