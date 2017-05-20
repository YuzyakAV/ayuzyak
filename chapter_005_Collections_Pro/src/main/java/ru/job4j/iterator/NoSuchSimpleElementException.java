package ru.job4j.iterator;

import java.util.NoSuchElementException;

/**
 * Exception for SimpleNumberIterator. Throwing when iterator has no simple numbers.
 *
 * @author Ayuzyak
 * @since 19.05.2017
 * @version 1.0
 */
public class NoSuchSimpleElementException extends NoSuchElementException {
    /**
     * Constructs a <code>NoSimpleElementException</code> with <tt>null</tt>
     * as its error message string.
     */
    public NoSuchSimpleElementException() {
        super();
    }

    /**
     * Constructs a <code>NoSimpleElementException</code>, saving a reference
     * to the error message string <tt>s</tt> for later retrieval by the
     * <tt>getMessage</tt> method.
     *
     * @param   s   the detail message.
     */
    public NoSuchSimpleElementException(String s) {
        super(s);
    }
}