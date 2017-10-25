package ru.job4j.tracker.start;
 
 import java.sql.Connection;
 
 /**
  * Interface UserAction.
  *
  * @author Ayuzyak
  * @version 1.0
  * @since 10.10.2017
  */
 public interface UserAction {
     /**
      * key for actions.
      * @return int key.
      */
     int key();

     /**
      * method for execute action.
      * @param input for enter information.
      * @param tracker of tasks.
      */
     void execute(Input input, Tracker tracker);

     /**
      * method for print information.
      * @return String information.
      */
     String info();
 }