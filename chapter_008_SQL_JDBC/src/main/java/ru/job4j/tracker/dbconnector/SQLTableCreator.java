package ru.job4j.tracker.dbconnector;
 
 import org.w3c.dom.Document;
 import org.w3c.dom.Element;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.w3c.dom.Text;
 
 import java.sql.Connection;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 
 /**
  * Class for create tables and populate them.
  * @author Ayuzyak
  * @version 1.0
  * @since 13.10.2017
  */
 public class SQLTableCreator {
     /**
      * Command to create Tasks table.
      */
     private String tasksCreate;
 
     /**
      * Command to create Comments Table.
      */
     private String commentsCreate;
 
     /**
      * Document to parse.
      */
     private Document document;
 
     /**
      * Constructor for SQLTableCreator.
      * @param docParser to getting Document.
      */
     public SQLTableCreator(DocParser docParser) {
         document = docParser.getDocument();
         Element root = document.getDocumentElement();
         NodeList children = root.getElementsByTagName("create");
         NodeList connectChild = children.item(0).getChildNodes();
         for (int i = 0; i < connectChild.getLength(); i++) {
             Node node = connectChild.item(i);
             if (node instanceof Element) {
                 Element element = (Element) node;
                 if (element.getTagName().equals("tasks")) {
                     Text text = (Text) element.getFirstChild();
                     tasksCreate = text.getData().trim();
                 } else if (element.getTagName().equals("comments")) {
                     Text text = (Text) element.getFirstChild();
                     commentsCreate = text.getData().trim();
                 }
             }
         }
     }
 
     /**
      * Create tables Tasks and Comments.
      * @param connection to database.
      */
     public void createTables(Connection connection) {
         try {
             Statement statement = connection.createStatement();
             statement.executeUpdate(tasksCreate);
             statement.executeUpdate(commentsCreate);
         } catch (SQLException e) {
             for (Throwable t : e) {
                 t.printStackTrace();
             }
         }
     }
 
     /**
      * Populate tables Tasks and Comments.
      * @param connection to database.
      */
     public void populateTables(Connection connection) {
         try {
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM tasks");
             int countRows = 0;
             if (resultSet.next()) {
                 countRows = resultSet.getInt(1);
             }
             if (countRows > 0) {
                 System.out.println("Database already contains data");
            } else {
                 Element root = document.getDocumentElement();
                 NodeList children = root.getElementsByTagName("populate");
                 NodeList connectChild = children.item(0).getChildNodes();
                 for (int i = 0; i < connectChild.getLength(); i++) {
                     Node node = connectChild.item(i);
                     if (node instanceof Element) {
                         Element element = (Element) node;
                         if (element.getTagName().equals("tasks")) {
                             Text text = (Text) element.getFirstChild();
                             insertRow(text.getData().trim(), connection);
                         } else if (element.getTagName().equals("comments")) {
                             Text text = (Text) element.getFirstChild();
                             insertRow(text.getData().trim(), connection);
                         }
                     }
                 }
                 System.out.println("Database has been populated");
             }
         } catch (SQLException e) {
             for (Throwable t : e) {
                 t.printStackTrace();
             }
         }
     }
 
     /**
      * Insert rows to database.
      * @param command to execute.
      * @param connection to database.
      */
     private void insertRow(String command, Connection connection) {
         try {
             Statement statement = connection.createStatement();
             statement.executeUpdate(command);
         } catch (SQLException e) {
             for (Throwable t : e) {
                 t.printStackTrace();
             }
         }
     }
 }