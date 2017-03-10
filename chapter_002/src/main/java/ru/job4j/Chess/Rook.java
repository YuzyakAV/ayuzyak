package ru.job4j.chess;

/**
 * Class Rook.
 * @author yuzyakav
 * @since 10.03.2017
 * @version 1.0
 */
public class Rook extends Figure {
    /**
     * Constructor Rook.
     * @param position - starting position.
     */
    public Rook(Cell position) {
        super(position);
    }

    @Override
    Cell[] way(Cell dist) throws ImpossibleMoveException {
        int posX = this.position.getNumberX();
        int posY = this.position.getNumberY();
        int distX = dist.getNumberX();
        int distY = dist.getNumberY();

        //possible moves
        Cell[] up = new Cell[posX];
        Cell[] down = new Cell[7 - posX];
        Cell[] left = new Cell[posY];
        Cell[] right = new Cell[7 - posY];

        boolean distIsUp = false;
        boolean distIsDown = false;
        boolean distIsLeft = false;
        boolean distIsRight = false;

        //possible move to up
        for (int i = posX - 1, j = 0; i >= 0; i--, j++) {
            up[j] = new Cell();
            up[j].setNumberX(i);
            up[j].setNumberY(posY);
            if (i == distX && posY == distY) {
                distIsUp = true;
            }
        }

        //possible move to down
        for (int i = posX + 1, j = 0; i <= 7; i++, j++) {
            down[j] = new Cell();
            down[j].setNumberX(i);
            down[j].setNumberY(posY);
            if (i == distX && posY == distY) {
                distIsDown = true;
            }
        }

        //possible move to left
        for (int i = posY - 1, j = 0; i >= 0; i--, j++) {
            left[j] = new Cell();
            left[j].setNumberX(posX);
            left[j].setNumberY(i);
            if (posX == distX && i == distY) {
                distIsLeft = true;
            }
        }

        //possible move to right
        for (int i = posY + 1, j = 0; i <= 7; i++, j++) {
            right[j] = new Cell();
            right[j].setNumberX(posX);
            right[j].setNumberY(i);
            if (posX == distX && i == distY) {
                distIsRight = true;
            }
        }

        Cell[] way;
        if (distIsUp) {
            way = new Cell[posX - distX];
            for (int i = 0; i < way.length; i++) {
                way[i] = new Cell();
                way[i].setNumberX(--posX);
                way[i].setNumberY(posY);
            }
        } else if (distIsDown) {
            way = new Cell[distX - posX];
            for (int i = 0; i < way.length; i++) {
                way[i] = new Cell();
                way[i].setNumberX(++posX);
                way[i].setNumberY(posY);
            }
        } else if (distIsLeft) {
            way = new Cell[posY - distY];
            for (int i = 0; i < way.length; i++) {
                way[i] = new Cell();
                way[i].setNumberX(posX);
                way[i].setNumberY(--posY);
            }
        } else if (distIsRight) {
            way = new Cell[distY - posY];
            for (int i = 0; i < way.length; i++) {
                way[i] = new Cell();
                way[i].setNumberX(posX);
                way[i].setNumberY(++posY);
            }
        } else {
            throw new ImpossibleMoveException("The rook can't go so");
        }
        return way;
    }

    @Override
    Figure clone(Cell dist) {
        return new Rook(dist);
    }
}