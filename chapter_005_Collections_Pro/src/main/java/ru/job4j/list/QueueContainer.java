package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * QueueContainer.
 * Add() for adding to end of queue.
 * FIFO.
 *
 * @author Ayuzyak
 * @since 01.06.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class QueueContainer<E> extends LinkedContainer<E> {

    /**
     * Getting element from queue and delete it.
     * @return element.
     */
    public E poll() {
        if (getSize() == 0) {
            throw new NoSuchElementException();
        }
        E elem = remove(0);
        return elem;
    }
}