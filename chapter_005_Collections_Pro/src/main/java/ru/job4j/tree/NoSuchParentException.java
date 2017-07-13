package ru.job4j.tree;

/**
 * Class NoSuchParentException.
 *
 * @author Ayuzyak
 * @since 11.07.2017
 * @version 1.0
 */
public class NoSuchParentException extends RuntimeException {
    /**
     * toString.
     * @return string.
     */
    @Override
    public String toString() {
        return "Tree has not this parent";
    }
}