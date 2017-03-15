package ru.job4j.chess;

/**
 * Class Bishop.
 * x - strings.
 * y - columns.
 * @author yuzyakav.
 * @since 10.03.2017
 * @version 1.0
 */
public class Bishop extends Figure {
    /**
     * Constructor Bishop.
     * @param position - starting position.
     */
    Bishop(Cell position) {
        super(position);
    }

    @Override
    Cell[] way(Cell dist) throws ImpossibleMoveException {
        int posX = this.position.getNumberX();
        int posY = this.position.getNumberY();
        int distX = dist.getNumberX();
        int distY = dist.getNumberY();

        //possible moves with max length
        Cell[] upLeft = new Cell[posX > posY ? posY : posX];
        Cell[] upRight = new Cell[posX > 7 - posY ? 7 - posY : posX];
        Cell[] downLeft = new Cell[7 - posX > posY ? posY : 7 - posX];
        Cell[] downRight = new Cell[7 - posX > 7 - posY ? 7 - posY : 7 - posX];

        boolean distIsUpLeft = false;
        boolean distIsUpRight = false;
        boolean distIsDownLeft = false;
        boolean distIsDownRight = false;

        //possible move to upLeft
        for (int x = posX - 1, y = posY - 1, j = 0; j < upLeft.length; x--, y--, j++) {
            upLeft[j] = new Cell();
            upLeft[j].setNumberX(x);
            upLeft[j].setNumberY(y);
            if (x == distX && y == distY) {
                distIsUpLeft = true;
            }
        }

        //possible move to upRight
        for (int x = posX - 1, y = posY + 1, j = 0; j < upRight.length; x--, y++, j++) {
            upRight[j] = new Cell();
            upRight[j].setNumberX(x);
            upRight[j].setNumberY(y);
            if (x == distX && y == distY) {
                distIsUpRight = true;
            }
        }

        //possible move to DownLeft
        for (int x = posX + 1, y = posY - 1, j = 0; j < downLeft.length; x++, y--, j++) {
            downLeft[j] = new Cell();
            downLeft[j].setNumberX(x);
            downLeft[j].setNumberY(y);
            if (x == distX && y == distY) {
                distIsDownLeft = true;
            }
        }

        //possible move to DownRight
        for (int x = posX + 1, y = posY + 1, j = 0; j < downRight.length; x++, y++, j++) {
            downRight[j] = new Cell();
            downRight[j].setNumberX(x);
            downRight[j].setNumberY(y);
            if (x == distX && y == distY) {
                distIsDownRight = true;
            }
        }

        Cell[] way;
        if (distIsUpLeft) {
            way = new Cell[posY - distY];
            for (int i = 0; i < way.length; i++) {
                way[i] = new Cell();
                way[i].setNumberX(--posX);
                way[i].setNumberY(--posY);
            }
        } else if (distIsUpRight) {
            way = new Cell[posX - distX];
            for (int i = 0; i < way.length; i++) {
                way[i] = new Cell();
                way[i].setNumberX(--posX);
                way[i].setNumberY(++posY);
            }
        } else if (distIsDownLeft) {
            way = new Cell[posY - distY];
            for (int i = 0; i < way.length; i++) {
                way[i] = new Cell();
                way[i].setNumberX(++posX);
                way[i].setNumberY(--posY);
            }
        } else if (distIsDownRight) {
            way = new Cell[distX - posX];
            for (int i = 0; i < way.length; i++) {
                way[i] = new Cell();
                way[i].setNumberX(++posX);
                way[i].setNumberY(++posY);
            }
        } else {
            throw new ImpossibleMoveException("The rook can't go so");
        }
        return way;
    }

    @Override
    Figure clone(Cell dist) {
        return new Bishop(dist);
    }
}