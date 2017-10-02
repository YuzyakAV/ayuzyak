package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Creature.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 02.10.2017
 */
public abstract class Creature implements Runnable {
    /**
     * Game board.
     */
    private final ReentrantLock[][] board;

    /**
     * Cell where Creature there is.
     */
    private ReentrantLock positionCell;

    /**
     * Coordinate of X-axis.
     */
    private int x;

    /**
     * Coordinate of Y-axis.
     */
    private int y;

    /**
     * Constructor for Creature.
     *
     * @param board Game board.
     * @param x coordinate for X-axis.
     * @param y coordinate for Y-axis.
     */
    public Creature(ReentrantLock[][] board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        positionCell = board[y][x];
    }

    /**
     * Creature actions.
     */
    @Override
    public void run() {

    }

    /**
     * Moving up.
     *
     * @return true if hero is moved.
     */
    public boolean moveUp() {
        ReentrantLock tempLock = null;
        boolean isBorder = false;
        if (y > 0) {
            tempLock = board[y - 1][x];
        } else {
            isBorder = true;
        }
        boolean isLocked = tryLock(tempLock);
        if (isLocked) {
            y--;
        } else {
            if (isBorder) {
                System.out.println(Thread.currentThread().getName() + " вверх не может пройти! Граница поля");
            } else {
                System.out.println(Thread.currentThread().getName() + " вверх не может пройти! Препятствие");
            }
        }
        return isLocked;
    }

    /**
     * Moving down.
     *
     * @return true if hero is moved.
     */
    public boolean moveDown() {
        ReentrantLock tempLock = null;
        boolean isBorder = false;
        if (y < board.length - 1) {
            tempLock = board[y + 1][x];
        } else {
            isBorder = true;
        }
        boolean isLocked = tryLock(tempLock);
        if (isLocked) {
            y++;
        } else {
            if (isBorder) {
                System.out.println(Thread.currentThread().getName() + " вниз не может пройти! Граница поля");
            } else {
                System.out.println(Thread.currentThread().getName() + " вниз не может пройти! Препятствие");
            }
        }
        return isLocked;
    }

    /**
     * Moving left.
     *
     * @return true if hero is moved.
     */
    public boolean moveLeft() {
        ReentrantLock tempLock = null;
        boolean isBorder = false;
        if (x > 0) {
            tempLock = board[y][x - 1];
        } else {
            isBorder = true;
        }
        boolean isLocked = tryLock(tempLock);
        if (isLocked) {
            x--;
        } else {
            if (isBorder) {
                System.out.println(Thread.currentThread().getName() + " влево не может пройти! Граница поля");
            } else {
                System.out.println(Thread.currentThread().getName() + " влево не может пройти! Препятствие");
            }
        }
        return isLocked;
    }

    /**
     * Moving right.
     *
     * @return true if hero is moved.
     */
    public boolean moveRight() {
        ReentrantLock tempLock = null;
        boolean isBorder = false;
        if (x < board.length - 1) {
            tempLock = board[y][x + 1];
        } else {
            isBorder = true;
        }
        boolean isLocked = tryLock(tempLock);
        if (isLocked) {
            x++;
        } else {
            if (isBorder) {
                System.out.println(Thread.currentThread().getName() + " вправо не может пройти! Граница поля");
            } else {
                System.out.println(Thread.currentThread().getName() + " вправо не может пройти! Препятствие");
            }
        }
        return isLocked;
    }

    /**
     * Try getting lock.
     *
     * @param tempLock is cell from game board.
     * @return true if trying was successful.
     */
    private boolean tryLock(ReentrantLock tempLock) {
        boolean isLocked = false;
        if (tempLock != null) {
            isLocked = tempLock.tryLock();
        }
        if (isLocked) {
            positionCell.unlock();
            positionCell = tempLock;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Choosing the direction to move.
     *
     * @param move integer for choosing.
     * @return true if move is possible.
     */
    public boolean chooseMove(int move) {
        switch (move) {
            case 0:
                return moveUp();
            case 1:
                return moveRight();
            case 2:
                return moveDown();
            case 3:
                return moveLeft();
            default:
                return false;
        }
    }

    /**
     * Getter for creature position cell.
     * @return positionCell.
     */
    public ReentrantLock getPositionCell() {
        return positionCell;
    }

    /**
     * Getter for coordinate from X-axis.
     * @return x.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for coordinate from Y-axis.
     * @return y.
     */
    public int getY() {
        return y;
    }
}