package ru.job4j.chess;

/**
 * Class OccupiedWayException.
 * @author yuzyakav.
 * @since 10.03.2017
 * @version 1.0
 */
public class OccupiedWayException extends Exception {
    /**
     * constructor for ImpossibleMoveException.
     * @param msg information about Exception.
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}