package ru.job4j.iterator;

import java.util.NoSuchElementException;

/**
 * Exception for EvenIterator. Throwing when iterator has no even numbers.
 *
 * @author Ayuzyak
 * @since 18.05.2017
 * @version 1.0
 */
public class NoSuchEvenElementException extends NoSuchElementException {
    /**
     * Constructs a <code>NoSuchElementException</code> with <tt>null</tt>
     * as its error message string.
     */
    public NoSuchEvenElementException() {
        super();
    }

    /**
     * Constructs a <code>NoSuchElementException</code>, saving a reference
     * to the error message string <tt>s</tt> for later retrieval by the
     * <tt>getMessage</tt> method.
     *
     * @param   s   the detail message.
     */
    public NoSuchEvenElementException(String s) {
        super(s);
    }
}