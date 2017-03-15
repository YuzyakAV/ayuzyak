package ru.job4j.chess;

/**
 * Class ImpossibleMoveException.
 * @author yuzyakav.
 * @since 10.03.2017
 * @version 1.0
 */
public class ImpossibleMoveException extends Exception {
    /**
     * constructor for ImpossibleMoveException.
     * @param msg information about Exception.
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}