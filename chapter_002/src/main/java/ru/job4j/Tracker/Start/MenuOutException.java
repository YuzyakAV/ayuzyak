package ru.job4j.tracker.start;

/**
 * Class MenuOutException.
 * @author yuzyakav
 * @since 07.03.2017
 * @version 1.0
 */
public class MenuOutException extends RuntimeException {

    /**
     * constructor for MenuOutException.
     * @param msg information about Exception.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}