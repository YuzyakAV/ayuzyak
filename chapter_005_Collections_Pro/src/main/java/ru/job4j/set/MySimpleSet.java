package ru.job4j.set;

import ru.job4j.list.LinkedContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class MySimpleSet
 *
 * @author Ayuzyak
 * @since 03.06.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class MySimpleSet<E> implements SimpleSet<E> {

    /**
     * Array of LinedContainers.
     */
    private LinkedContainer<E>[] tab;

    /**
     * Size of tab.
     */
    private int tabSize;

    /**
     * Number of set's elements.
     */
    private int factSize;

    /**
     * Constructor for MySimpleSet.
     */
    public MySimpleSet() {
        tabSize = 16;
        this.tab = new LinkedContainer[tabSize];
    }

    /**
     * Add element to set.
     * Check load factor and add element.
     * @param e element for adding.
     */
    @Override
    public void add(E e) {
        growTab();
        int hashElem = e.hashCode();
        int index = hashElem % tabSize;
        if (tab[index] == null) {
            tab[index] = new LinkedContainer<E>();
        }
        if (!tab[index].contains(e)) {
            tab[index].add(e);
        }
        factSize++;
    }

    /**
     * Iterator for set.
     * @return iterator.
     */
    @Override
    public Iterator iterator() {
        return new Itr();
    }

    /**
     * Increase capacity of tab and rehash all elements.
     */
    private void growTab() {
        if (factSize >= 0.75 * tabSize) {
            LinkedContainer<E>[] copyTab = tab;
            tabSize = tabSize * 2;
            factSize = 0;
            tab = new LinkedContainer[tabSize];
            for (int i = 0; i < copyTab.length; i++) {
                if (copyTab[i] != null) {
                    for (int j = 0; j < copyTab[i].getSize(); j++) {
                        E elem = copyTab[i].get(j);
                        this.add(elem);
                    }
                }
            }
        }
    }

    /**
     * Iterator for passing all elements of set.
     * @param <E> type of elements.
     */
    private class Itr<E> implements Iterator<E> {

        /**
         * Cursor for passing.
         */
        private int indexItr = 0;

        /**
         * List of all LinkedContainer's iterators.
         */
        private ArrayList<Iterator<E>> listItr = new ArrayList<>();

        /**
         * Current iterator.
         */
        private Iterator<E> factIter;

        /**
         * Constructor for iterator.
         */
        Itr() {
            for (int i = 0; i < tabSize; i++) {
                if (tab[i] != null) {
                    Iterator iterator = tab[i].iterator();
                    listItr.add(iterator);
                }
            }
            if (listItr.size() > 0) {
                factIter = listItr.get(indexItr);
            }
        }

        /**
         * Check next element.
         * @return true if iterator has next number.
         */
        @Override
        public boolean hasNext() {
            boolean isNext = true;
            if (indexItr == listItr.size() - 1) {
                isNext = factIter.hasNext();
            }
            return isNext;
        }

        /**
         * Get next element.
         * @return next element.
         */
        @Override
        public E next() {

            E element = null;
            boolean isNext = false;
            while (indexItr < listItr.size()) {
                if (factIter != null && factIter.hasNext()) {
                    element = factIter.next();
                    isNext = true;
                    break;
                } else {
                    if (indexItr < listItr.size() - 1) {
                        factIter = listItr.get(++indexItr);
                    } else {
                        indexItr++;
                    }
                }
            }
            if (!isNext) {
                throw new NoSuchElementException();
            }
            return element;
        }
    }

    /**
     * Getter for factSize.
     * @return factSize.
     */
    public int getFactSize() {
        return factSize;
    }

    /**
     * Getter for tabSize.
     * @return tabSize.
     */
    public int getTabSize() {
        return tabSize;
    }
}