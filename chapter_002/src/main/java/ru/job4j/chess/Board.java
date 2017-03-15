package ru.job4j.chess;

/**
 * Class Board.
 * @author yuzyakav.
 * @since 10.03.2017
 * @version 1.0
 */
public class Board {
    /**
     * Array of cells for board.
     */
    private Cell[] cells;

    /**
     * Array of figures for game.
     */
    private Figure[] figures;

    /**
     * Constructor Board.
     */
    public Board() {
        cells = new Cell[64];
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[count] = new Cell();
                cells[count].setNumberX(i);
                cells[count].setNumberY(j);
                count++;
            }
        }
    }

    /**
     * Setter for board.
     * @param figures for set.
     */
    public void setFigures(Figure[] figures) {
        this.figures = figures;
    }

    /**
     * Getter for board.
     * @return array of figures.
     */
    public Figure[] getFigures() {
        return figures;
    }

    /**
     *
     * @param source cell for move figure.
     * @param dist - destination cell for move figure.
     * @return boolean true for legal move and false for illegal.
     * @throws ImpossibleMoveException when move is impossible.
     * @throws OccupiedWayException when way is occupied.
     * @throws FigureNotFoundException when figure isn't founded
     */
    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean figureInCell = false;
        int pos = 0;
        for (; pos < figures.length; pos++) {
            if (figures[pos].position.equals(source)) {
                figureInCell = true;
                break;
            }
        }
        if (!figureInCell) {
            throw new FigureNotFoundException("In the source cell is no figure");
        }
        Cell[] freeWay = figures[pos].way(dist);
        boolean wayIsFree = true;
        for (Figure fig : figures) {
            for (Cell cell : freeWay) {
                if (fig.position.equals(cell)) {
                    wayIsFree = false;
                    break;
                }
            }
            if (!wayIsFree) {
                throw new OccupiedWayException("On the way there are figures");
            }
        }
            figures[pos] = figures[pos].clone(dist);
        return true;
    }
}