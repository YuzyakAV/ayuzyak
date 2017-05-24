package ru.job4j.generic;

/**
 * SimpleArray.
 *
 * @author Ayuzyak
 * @since 23.05.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class SimpleArray<E> {

    /**
     * Array of objects.
     */
    private Object[] arr;

    /**
     * Number of array elements.
     */
    private int size = 0;

    /**
     * Load capacity.
     */
    private int capacity;

    /**
     * Class constructor.
     */
    public SimpleArray() {
        capacity = 10;
        this.arr = new Object[capacity];
    }

    /**
     * method for increase array's capacity.
     * @return true if array is increased.
     */
    private boolean increaseCapacity() {
        if (size > capacity * 0.7) {
            Object[] newArr = new Object[capacity * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            this.arr = newArr;
            capacity *= 2;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adding element to array.
     * @param element element for adding.
     */
    public void add(E element) {
        increaseCapacity();
        arr[size++] = element;
    }

    /**
     * Deleting element from array.
     * @param index of element for delete it.
     * @return deleted element.
     */
    public E delete(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E delElement = (E) arr[index];
        System.arraycopy(arr, index + 1, arr, index, this.size - index - 1);
        arr[--size] = null;
        return delElement;
    }

    /**
     * Getting element from array.
     * @param index of element for getting.
     * @return element.
     */
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) arr[index];
    }

    /**
     * Update reference by index to new element.
     * @param index for updating.
     * @param element for setting.
     */
    public void update(int index, E element) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        arr[index] = element;
    }

    /**
     * Getter for size.
     * @return size.
     */
    public int getSize() {
        return size;
    }
}