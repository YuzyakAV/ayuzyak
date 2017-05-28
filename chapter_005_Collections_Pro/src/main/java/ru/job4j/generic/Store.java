package ru.job4j.generic;

/**
 * Store Interface.
 *
 * @author Ayuzyak
 * @since 28.05.2017
 * @version 1.0
 * @param <T> type of elements.
 */
public interface Store<T extends Base> {
    /**
     * Adding element.
     * @param element for adding.
     */
    void add(T element);

    /**
     * set new element to ID.
     * @param id of Base-object.
     * @param element extends Base.
     */
    void update(String id, T element);

    /**
     * delete element by ID.
     * @param id of elements.
     */
    void delete(String id);
}