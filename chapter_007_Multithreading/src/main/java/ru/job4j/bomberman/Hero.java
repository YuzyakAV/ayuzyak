package ru.job4j.bomberman;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hero.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 02.10.2017
 */
public class Hero extends Creature {
    /**
     * Constructor for Hero.
     *
     * @param board Game board.
     * @param x coordinate for X-axis.
     * @param y coordinate for Y-axis.
     */
    public Hero(ReentrantLock[][] board, int x, int y) {
        super(board, x, y);
    }

    /**
     * Hero move.
     * In start hero moving to right.
     */
    private int move = 1;

    /**
     * Run hero.
     */
    @Override
    public void run() {
        getPositionCell().lock();

        JFrame frame = new JFrame("Hero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(400, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new GridBagLayout());
        frame.setOpacity(0.0f);
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener() {
            /**
             * Invoked when a key has been typed.
             * @param e key event.
             */
            @Override
            public void keyTyped(KeyEvent e) {

            }

            /**
             * Invoked when a key has been pressed.
             * @param e key event.
             */
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        move = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        move = 1;
                        break;
                    case KeyEvent.VK_DOWN:
                        move = 2;
                        break;
                    case KeyEvent.VK_LEFT:
                        move = 3;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                    default:
                        break;
                }
            }

            /**
             * Invoked when a key has been released.
             * @param e key event.
             */
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);

                boolean isMoved = false;
                while (!isMoved) {
                    isMoved = chooseMove(move % 4);
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