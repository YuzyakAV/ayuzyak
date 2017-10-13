package ru.job4j.tracker.dbconnector;
 
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;
 
 /**
  * Class for getting connection to database.
  *
  * @author Ayuzyak
  * @version 1.0
  * @since 12.10.2017
  */
 public class Connector {
 
     /**
      * Connection to database.
      */
     private Connection connection;
 
     /**
      * Constructor for Connector.
      *
      * @param connectArgs with names of database, user, password.
      */
     public Connector(ConnectArgs connectArgs) {
         String database = connectArgs.getDatabase();
         String user = connectArgs.getUser();
         String password = connectArgs.getPassword();
         try {
             this.connection = DriverManager.
                     getConnection("jdbc:postgresql://localhost:5432/" + database, user, password);
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
 
     /**
      * Getter for connection.
      *
      * @return Connection.
      */
     public Connection getConnection() {
        return connection;
     }
 +}