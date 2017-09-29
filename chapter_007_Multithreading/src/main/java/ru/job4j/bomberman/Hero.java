package ru.job4j.bomberman;
 
 import java.util.concurrent.ThreadLocalRandom;
 import java.util.concurrent.TimeUnit;
 import java.util.concurrent.locks.ReentrantLock;
 
 /**
  * Hero.
  *
  * @author Ayuzyak
  * @version 1.0
  * @since 29.09.2017
  */
 public class Hero implements Runnable {
     /**
      * Game board.
      */
     private final ReentrantLock[][] board;
 
     /**
      * Cell where hero there is.
      */
     private ReentrantLock herroCell;
 
     /**
      * Coordinate of X-axis.
      */
     private int x = 0;
 
     /**
      * Coordinate of Y-axis.
      */
     private int y = 0;
 
     /**
      * Constructor for Hero.
      * @param board Game board.
      */
     public Hero(ReentrantLock[][] board) {
         this.board = board;
         herroCell = board[y][x];
     }
 
     /**
      * Hero actions.
      */
     @Override
     public void run() {
         try {
             herroCell.lock();
             while (!Thread.interrupted()) {
                 TimeUnit.SECONDS.sleep(1);
                 ThreadLocalRandom random = ThreadLocalRandom.current();
                 int move = random.nextInt(0, 4);
                 boolean isMoved = false;
                 while (!isMoved) {
                     isMoved = chooseMove(move % 4);
                     move++;
                     if (!isMoved) {
                         System.out.println("Дальше не пройти, поверну в другую сторону");
                     }
                     TimeUnit.MILLISECONDS.sleep(500);
                 }
                 System.out.println("Координаты героя: x = " + x + "  y = " + y);
             }
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
 
     /**
      * Moving up.
      * @return true if hero is moved.
      */
     public boolean moveUp() {
         ReentrantLock tempLock = null;
         if (y > 0) {
             tempLock = board[y - 1][x];
        }
         boolean isLocked = tryLock(tempLock);
         if (isLocked) {
             y--;
         }
         return isLocked;
     }
 
     /**
      * Moving down.
      * @return true if hero is moved.
      */
     public boolean moveDown() {
         ReentrantLock tempLock = null;
         if (y < board.length - 1) {
             tempLock = board[y + 1][x];
         }
         boolean isLocked = tryLock(tempLock);
         if (isLocked) {
             y++;
         }
         return isLocked;
     }

     /**
      * Moving left.
      * @return true if hero is moved.
      */
     public boolean moveLeft() {
         ReentrantLock tempLock = null;
         if (x > 0) {
             tempLock = board[y][x - 1];
         }
         boolean isLocked = tryLock(tempLock);
         if (isLocked) {
             x--;
         }
         return isLocked;
     }
 
     /**
      * Moving right.
      * @return true if hero is moved.
      */
     public boolean moveRight() {
         ReentrantLock tempLock = null;
         if (x < board.length - 1) {
             tempLock = board[y][x + 1];
         }
         boolean isLocked = tryLock(tempLock);
         if (isLocked) {
             x++;
         }
         return isLocked;
     }
 
     /**
      * Try getting lock.
      * @param tempLock is cell from game board.
      * @return true if trying was successful.
      */
    private boolean tryLock(ReentrantLock tempLock) {
         boolean isLocked = false;
         if (tempLock != null) {
             isLocked = tempLock.tryLock();
         }
         if (isLocked) {
             herroCell.unlock();
             herroCell = tempLock;
             return true;
         } else {
             return false;
         }
     }
 
     /**
      * Choosing the direction to move.
      * @param move integer for choosing.
      * @return true if move is possible.
      */
     private boolean chooseMove(int move) {
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
 }