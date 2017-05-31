package ru.job4j.list;

/**
 * SimpleContainer.
 *
 * @author Ayuzyak
 * @since 31.05.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Add element to container.
     * @param element for adding.
     */
    void add(E element);

    /**
     * Get element from container.
     * @param index of element.
     * @return element.
     */
    E get(int index);
}