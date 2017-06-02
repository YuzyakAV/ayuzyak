package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedContainer.
 *
 * @author Ayuzyak
 * @since 01.06.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class LinkedContainer<E> implements SimpleContainer<E> {

    /**
     * Container size.
     */
    private int size = 0;

    /**
     * First node.
     */
    private Knot<E> first;

    /**
     * Second node.
     */
    private Knot<E> last;

    /**
     * Container modifier.
     */
    private int linkMod = 0;

    /**
     * Node for link.
     * @param <E> element.
     */
    private class Knot<E> {
        /**
         * Element.
         */
        private E elem;

        /**
         * Previous node.
         */
        private Knot<E> previous;

        /**
         * Next node.
         */
        private Knot<E> next;

        /**
         * Constructor for Knot.
         * @param prevvious node.
         * @param elem element.
         * @param next node.
         */
        Knot(Knot<E> prevvious, E elem, Knot<E> next) {
            this.previous = prevvious;
            this.elem = elem;
            this.next = next;
        }
    }

    /**
     * Add element to the end of container.
     * @param element for adding.
     */
    @Override
    public void add(E element) {
        final Knot<E> l = this.last;
        final Knot<E> newKnot = new Knot<>(l, element, null);
        this.last = newKnot;
        if (l == null) {
            this.first = newKnot;
        } else {
            l.next = newKnot;
        }
        size++;
        linkMod++;
    }

    /**
     * Method for get element by index.
     * @param index of element.
     * @return element.
     */
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Knot<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.elem;
    }

    /**
     * Remove element by index.
     * @param index for delete element from container.
     * @return deleted element.
     */
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Knot<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        Knot<E> prevKnot = x.previous;
        Knot<E> nextKnot = x.next;
        if (prevKnot == null) {
            this.first = nextKnot;
        } else {
            prevKnot.next = nextKnot;
            x.previous = null;
        }
        if (nextKnot == null) {
            this.last = prevKnot;
        } else {
            nextKnot.previous = prevKnot;
            x.next = null;
        }
        size--;
        linkMod++;
        return x.elem;
    }

    /**
     * Add element by index.
     * @param index for adding.
     * @param elem for adding.
     */
    public void add(int index, E elem) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index != size) {
            Knot<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            Knot<E> prevKnot = x.previous;
            Knot<E> elemKnot = new Knot<>(null, elem, null);
            if (prevKnot == null) {
                this.first = elemKnot;
                elemKnot.next = x;
                x.previous = elemKnot;
            } else {
                prevKnot.next = elemKnot;
                elemKnot.previous = prevKnot;
                elemKnot.next = x;
                x.previous = elemKnot;
            }
            size++;
            linkMod++;
        } else {
            add(elem);
        }
    }

    /**
     * Check container for contaning element.
     * @param elem for check.
     * @return true if contaner contains elem.
     */
    public boolean contains(E elem) {
        boolean isE = false;
        for (E e : this) {
            if (e.equals(elem)) {
                isE = true;
                break;
            }
        }
        return isE;
    }

    /**
     * Getter for container size.
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method for getting iterator.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Iterator for LinkedContainer.
     * @param <E> element.
     */
    private class Itr<E> implements Iterator<E> {

        /**
         * Previous node.
         */
        private Knot<E> lastReturned;

        /**
         * Next node for passing.
         */
        private Knot<E> next = (Knot<E>) first;

        /**
         * Cursor for passing container.
         */
        private int index = 0;

        /**
         * Modifier for checking modifications.
         */
        private int iteratorMod = linkMod;

        /**
         * Check next element.
         * @return true if iterator has next number.
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Get next element.
         * @return next element.
         */
        @Override
        public E next() {
            checkMod();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            index++;
            return lastReturned.elem;
        }

        /**
         * Check modifications.
         */
        private void checkMod() {
            if (iteratorMod != linkMod) {
                throw new ConcurrentModificationException();
            }
        }
    }
}