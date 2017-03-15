package ru.job4j.chess;

/**
 * Class Cell.
 * @author yuzyakav.
 * @since 10.03.2017
 * @version 1.0
 */
public class Cell {
    /**
     * numberX - number of strings.
     */
    private int numberX;

    /**
     * numberY - number of columns.
     */
    private int numberY;

    /**
     * Constructor Cell for default.
     */
    public Cell() {
    }

    /**
     * Constructor Cell with arguments.
     * @param numberX - number of strings.
     * @param numberY - number of columns.
     */
    public Cell(int numberX, int numberY) {
        this.numberX = numberX;
        this.numberY = numberY;
    }

    /**
     * Setter for numberX.
     * @param numberX - number of strings.
     */
    public void setNumberX(int numberX) {
        this.numberX = numberX;
    }

    /**
     * Getter for numberX.
     * @return number of strings.
     */
    public int getNumberX() {
        return numberX;
    }

    /**
     * Setter for numberY.
     * @param numberY - number of columns.
     */
    public void setNumberY(int numberY) {
        this.numberY = numberY;
    }

    /**
     * Getter for numberY.
     * @return  number of columns.
     */
    public int getNumberY() {
        return numberY;
    }

    @Override
    public String toString() {
        return numberX + " " + numberY;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Cell cell = (Cell) obj;
        return this.numberX == cell.numberX && this.numberY == cell.numberY;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}