package ru.job4j.bomberman;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Monster.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 02.10.2017
 */
public class Monster extends Creature {
    /**
     * Constructor for Monster.
     *
     * @param board Game board.
     * @param x coordinate for X-axis.
     * @param y coordinate for Y-axis.
     */
    public Monster(ReentrantLock[][] board, int x, int y) {
        super(board, x, y);
    }

    /**
     * Run monster.
     */
    @Override
    public void run() {
        try {
            getPositionCell().lock();
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int move = random.nextInt(0, 4);
                boolean isMoved = false;
                while (!isMoved) {
                    isMoved = chooseMove(move % 4);
                    move++;
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                System.out.println("Координаты " + Thread.currentThread().getName()
                        + ": x = " + getX() + "  y = " + getY());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}