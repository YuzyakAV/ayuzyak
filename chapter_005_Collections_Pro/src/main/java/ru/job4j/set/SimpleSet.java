package ru.job4j.set;

/**
 * Interface SimpleSet.
 *
 * @author Ayuzyak
 * @since 06.06.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public interface SimpleSet<E> extends Iterable<E> {
    /**
     * Add element to set.
     * @param elem for adding.
     */
    void add(E elem);
}