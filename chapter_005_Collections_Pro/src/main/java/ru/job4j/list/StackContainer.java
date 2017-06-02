package ru.job4j.list;

import java.util.EmptyStackException;

/**
 * StackContainer.
 * LIFO.
 *
 * @author Ayuzyak
 * @since 01.06.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class StackContainer<E> extends LinkedContainer<E> {

    /**
     * Add element to Stack.
     * @param elem for addition.
     */
    public void push(E elem) {
        add(0, elem);
    }

    /**
     * Get element from stack and remove it.
     * @return element.
     */
    public E pop() {
        if (getSize() == 0) {
            throw new EmptyStackException();
        }
        E elem = remove(0);
        return elem;
    }
}