package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator of iterators.
 *
 * @author Ayuzyak
 * @since 17.05.2017
 * @version 1.0
 */
public class IteratorIterators implements ConvertIterator {

    /**
     * Iterator, which contains other Iterators.
     */
    private Iterator<Iterator<Integer>> allIterators;

    /**
     * Current iterator from alliterators.
     */
    private  Iterator<Integer> factIterator;

    /**
     * Method for convert Iterator<Iterator<Integer>> to Iterator<Integer>.
     * @param it Iterator<Iterator<Integer>>.
     * @return new Iterator<Integer>.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.allIterators = it;
        if (it.hasNext()) {
            this.factIterator = it.next();
        }
        return this;
    }

    /**
     * Check next number.
     * @return true if iterator has next number.
     */
    @Override
    public boolean hasNext() {
        return factIterator.hasNext() || allIterators.hasNext();
    }

    /**
     * Get next number.
     * @return next number.
     */
    @Override
    public Integer next() {
        Integer element = null;
        boolean hasNextElement = false;
        if (factIterator.hasNext()) {
            element = factIterator.next();
            hasNextElement = true;
        } else if (allIterators.hasNext()) {
            while (allIterators.hasNext()) {
                factIterator = allIterators.next();
                if (factIterator.hasNext()) {
                    element = factIterator.next();
                    hasNextElement = true;
                    break;
                } else {
                    hasNextElement = false;
                }
            }
        }
        if (!hasNextElement) {
            throw new NoSuchElementException();
        }
        return element;
    }
}