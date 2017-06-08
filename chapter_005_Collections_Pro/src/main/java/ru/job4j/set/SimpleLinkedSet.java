package ru.job4j.set;

import ru.job4j.list.LinkedContainer;

import java.util.Iterator;

/**
 * Class SimpleLinkedSet.
 *
 * @author Ayuzyak
 * @since 05.06.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class SimpleLinkedSet<E> implements SimpleSet<E> {
    /**
     * SimpleContainer for storage elements.
     */
    private LinkedContainer<E> container = new LinkedContainer<E>();

    /**
     * Add element to set.
     * @param elem for adding.
     */
    @Override
    public void add(E elem) {
        if (elem != null && !container.contains(elem)) {
            container.add(elem);
        }
    }

    /**
     * Getting iterator.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}