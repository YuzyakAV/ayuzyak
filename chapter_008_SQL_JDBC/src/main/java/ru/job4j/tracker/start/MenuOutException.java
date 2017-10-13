package ru.job4j.tracker.start;
 
 /**
  * Class MenuOutException.
  * @author Ayuzyak
  * @since 10.10.2017
  * @version 1.0
  */
 public class MenuOutException extends RuntimeException {
 
     /**
      * constructor for MenuOutException.
      * @param msg information about Exception.
      */
    public MenuOutException(String msg) {
         super(msg);
     }
 }