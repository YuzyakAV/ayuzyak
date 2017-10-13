package ru.job4j.tracker.start;
 
 import org.xml.sax.SAXException;
 import ru.job4j.tracker.dbconnector.ConnectArgs;
 import ru.job4j.tracker.dbconnector.Connector;
 import ru.job4j.tracker.dbconnector.DocParser;
 import ru.job4j.tracker.dbconnector.SQLTableCreator;
 
 import javax.xml.parsers.ParserConfigurationException;
 import java.io.IOException;
 import java.sql.Connection;
 import java.util.ArrayList;
 
 /**
  * Class StartUI.
  * @author Ayuzyak
  * @since 10.10.2017
  * @version 1.0
  */
 public class StartUI {
     /**
      * range of actions.
      */
     private ArrayList<Integer> range;
 
     /**
      * variable of input.
      */
     private Input input;
 
     /**
      * constructor for MenuTracker.
      * @param input for enter information.
      */
     public StartUI(Input input) {
         this.input = input;
     }
 
     /**
      * initialize input.
      */
     public void init() {
         String path = "chapter_008_SQL_JDBC/src/main/java/ru/job4j/tracker/dbconnector/tracker.xml";
         DocParser parser = null;
         try {
             parser = new DocParser(path);
         } catch (ParserConfigurationException | IOException | SAXException e) {
             e.printStackTrace();
         }
         ConnectArgs connectArgs = new ConnectArgs(parser);
         Connector connector = new Connector(connectArgs);
         Connection connection = connector.getConnection();
         SQLTableCreator tableCreator = new SQLTableCreator(parser);
         tableCreator.createTables(connection);
         tableCreator.populateTables(connection);
 
         MenuTracker menu = new MenuTracker(this.input, connection);
         menu.fillActions();
         UserAction exitAction = new BaseAction("Exit.") {
             /**
              * key for choose.
              * @return int key.
              */
             public int key() {
                 return 8;
             }
 
             /**
              * method for execute showing comments.
              * @param input for enter information.
              * @param connection to database.
              */
             public void execute(Input input, Connection connection) {
                 System.out.println("Good bye");
             }
         };
         menu.addAction(exitAction);
         range = menu.getRangeActions();
         int choice;
        System.out.println("Welcome to tracker!");
             do {
                 menu.show();
                choice = input.ask("Enter number of action: \n", range);
                 menu.select(choice);
             } while (choice != 8);
    }
 
     /**
      * PSVM.
      * @param args - args.
      */
     public static void main(String[] args) {
         Input input = new ValidateInput();
         new StartUI(input).init();
     }
 } 