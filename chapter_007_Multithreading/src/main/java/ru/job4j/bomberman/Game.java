package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Game.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 02.10.2017
 */
public class Game {
    /**
     * Game board.
     */
    private final ReentrantLock[][] board;

    /**
     * Constructor for empty board.
     * Size of board 10 x 10.
     */
    public Game() {
        this.board = new ReentrantLock[10][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Constructor with difficulty.
     * @param difficulty integer number from 1 to 3.
     */
    public Game(int difficulty) {
        this();
        switch (difficulty) {
            case 1:
                startAllThreadsAndLockAllBlocks(setStage1());
                break;
            case 2:
                startAllThreadsAndLockAllBlocks(setStage2());
                break;
            case 3:
                startAllThreadsAndLockAllBlocks(setStage3());
                break;
            default:
                System.out.println("На данный момент разработано 3 уровня");
                break;
        }
    }

    /**
     * Setting first stage.
     * 9 - hero
     * 1 - block
     * 2 - monster
     *
     * @return array of creatures.
     */
    private int[][] setStage1() {
        int[][] model = {
                {9, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return model;
    }

    /**
     * Setting second stage.
     * 9 - hero
     * 1 - block
     * 2 - monster
     *
     * @return array of creatures.
     */
    private int[][] setStage2() {
        int[][] model = {
                {9, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 2, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 2, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 2, 0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return model;
    }

    /**
     * Setting third stage.
     * 9 - hero
     * 1 - block
     * 2 - monster
     *
     * @return array of creatures.
     */
    private int[][] setStage3() {
        int[][] model = {
                {9, 0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 2, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 2, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 2, 0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 2, 0, 0}
        };
        return model;
    }

    /**
     * Starting all threads and creation blocks from model.
     * @param arr model.
     */
    private void startAllThreadsAndLockAllBlocks(int[][] arr) {
        List<Thread> threads = new ArrayList<>();
        int monsterCount = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 9) {
                    threads.add(new Thread(new Hero(this.board, j, i), "Hero"));
                } else if (arr[i][j] == 2) {
                    threads.add(new Thread(new Monster(this.board, j, i), "Monster#" + monsterCount++));
                } else if (arr[i][j] == 1) {
                    board[i][j].lock();
                }
            }
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
    }

    /**
     * Start game.
     *
     * @param args from CMDLine.
     */
    public static void main(String[] args) {
        Game game = new Game(1);
    }
}