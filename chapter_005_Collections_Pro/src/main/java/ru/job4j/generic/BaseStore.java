package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * BaseStore.
 *
 * @author Ayuzyak
 * @since 27.05.2017
 * @version 1.0
 * @param <T> type of elements.
 */
public abstract class BaseStore<T extends Base> implements Store<T> {

    /**
     * List SimpleArray.
     */
    private SimpleArray<T> simpleArray = new SimpleArray<>();

    /**
     * Add element BaseStore.
     * @param element for adding.
     */
    @Override
    public void add(T element) {
        simpleArray.add(element);
    }

    /**
     * Update element in BaseStore by ID.
     * @param id of Base-object.
     * @param element extends Base.
     */
    @Override
    public void update(String id, T element) {
        int index = getIndexById(id);
        simpleArray.update(index, element);
    }

    /**
     * Delete element in BaseStore by ID.
     * @param id of element.
     */
    @Override
    public void delete(String id) {
        int index = getIndexById(id);
        simpleArray.delete(index);
    }

    /**
     * Get index of element by ID.
     * @param id of element.
     * @return index of element.
     */
    private int getIndexById(String id) {
        int index = -1;
        boolean hasID = false;
        for (int i = 0; i < simpleArray.getSize(); i++) {
            if (id != null && simpleArray.get(i).getId().equals(id)) {
                index = i;
                hasID = true;
                i = simpleArray.getSize();
            }
        }
        if (!hasID) {
            throw new NoSuchElementException();
        }
        return index;
    }

    /**
     * Getter for testing this class.
     * @return SimpleArray<T>
     */
    protected SimpleArray<T> getSimpleArray() {
        return simpleArray;
    }
}