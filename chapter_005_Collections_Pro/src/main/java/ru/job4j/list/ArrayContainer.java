package ru.job4j.list;

import ru.job4j.generic.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayContainer.
 *
 * @author Ayuzyak
 * @since 31.05.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class ArrayContainer<E> extends SimpleArray<E> implements Iterable<E> {

    /**
     * Array modifier.
     */
    private int arrayMod = 0;

    /**
     * Add element.
     * @param element element for adding.
     */
    @Override
    public void add(E element) {
        super.add(element);
        arrayMod++;
    }

    /**
     * delete element.
     * @param index of element for delete it.
     * @return deleted element.
     */
    @Override
    public E delete(int index) {
        arrayMod++;
        return super.delete(index);
    }

    /**
     * Check array for contain element.
     * @param elem for check.
     * @return true if array contains element.
     */
    public boolean contains(E elem) {
        boolean isE = false;
        for (int i = 0; i < getSize(); i++) {
            if (elem.equals(get(i))) {
                isE = true;
                i = getSize();
            }
        }
        return isE;
    }

    /**
     * Get iterator for passing container.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    /**
     * Inner class implements Iterator.
     */
    private class ArrayIterator implements Iterator<E> {

        /**
         * Cursor for passing.
         */
        private int cursor = 0;

        /**
         * Number for check modification.
         */
        private int iteratorMod = arrayMod;

        /**
         * Check next element.
         * @return true if iterator has next number.
         */
        @Override
        public boolean hasNext() {
            int size = getSize();
            return cursor < size;
        }

        /**
         * Get next element.
         * @return next element.
         */
        @Override
        public E next() {
            checkMod();
            E elem;
            if (hasNext()) {
                elem = get(cursor++);
            } else {
                throw new NoSuchElementException();
            }
            return elem;
        }

        /**
         * Check modifications.
         */
        private void checkMod() {
            if (iteratorMod != arrayMod) {
                throw new ConcurrentModificationException();
            }
        }
    }
}