package ru.job4j.bomberman;
 
 import java.util.concurrent.locks.ReentrantLock;
 
 /**
  * Game.
  *
  * @author Ayuzyak
  * @version 1.0
  * @since 28.09.2017
  */
 public class Game {
     /**
      * Game board.
      */
     private final ReentrantLock[][] board;
 
     /**
      * Constructor for Game.
      * @param sizeBoard size of board NxN.
      */
     public Game(int sizeBoard) {
         this.board = new ReentrantLock[sizeBoard][sizeBoard];
         for (int i = 0; i < board.length; i++) {
             for (int j = 0; j < board[i].length; j++) {
                 board[i][j] = new ReentrantLock();
             }
         }
     }
 
     /**
      * Start game.
      * @param args from CMDLine.
      */
     public static void main(String[] args) {
         Game game = new Game(10);
         Hero hero = new Hero(game.board);
         Thread startGame = new Thread(hero);
         startGame.start();
     }
 }