package ru.job4j.chess;

/**
 * Class Figure.
 * @author yuzyakav
 * @since 10.03.2017
 * @version 1.0
 */
public abstract class Figure {

    /**
     * Starting position.
     */
    final Cell position;

    /**
     * Constructor for Figure.
     * @param position for start.
     */
    Figure(Cell position) {
        this.position = position;
    }

    /**
     * Method for return possible move of figure.
     * @param dist - cell for destination.
     * @return array of cell.
     * @throws ImpossibleMoveException when move is impossible.
     */
    abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * Method for clone figure.
     * @param dist - destination cell for clone.
     * @return new figure.
     */
    abstract Figure clone(Cell dist);
}