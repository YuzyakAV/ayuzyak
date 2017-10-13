package ru.job4j.tracker.dbconnector;
 
 import org.w3c.dom.Document;
 import org.w3c.dom.Element;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.w3c.dom.Text;
 
 /**
  * Class for getting arguments for connecting to database.
  *
  * @author Ayuzyak
  * @version 1.0
  * @since 12.10.2017
  */
 public class ConnectArgs {
     /**
      * Database name.
      */
     private String database;
 
     /**
      * User name.
      */
     private String user;
 
     /**
      * Password name.
      */
  private String password;
 
     /**
      * Constructor for ConnectArgs.
      *
      * @param docParser for getting arguments.
      */
     public ConnectArgs(DocParser docParser) {
         Document doc = docParser.getDocument();
         Element root = doc.getDocumentElement();
         NodeList children = root.getElementsByTagName("connect");
         NodeList connectChild = children.item(0).getChildNodes();
         for (int i = 0; i < connectChild.getLength(); i++) {
             Node node = connectChild.item(i);
             if (node instanceof Element) {
                 Element element = (Element) node;
                 if (element.getTagName().equals("database")) {
                     Text text = (Text) element.getFirstChild();
                     database = text.getData().trim();
                 } else if (element.getTagName().equals("user")) {
                     Text text = (Text) element.getFirstChild();
                     user = text.getData().trim();
                 } else if (element.getTagName().equals("password")) {
                     Text text = (Text) element.getFirstChild();
                     password = text.getData().trim();
                 }
             }
         }
     }
 
     /**
      * Getter for database.
      *
      * @return database.
      */
     public String getDatabase() {
         return database;
     }
 
     /**
      * Getter for user.
      *
      * @return user.
      */
     public String getUser() {
         return user;
     }
 
     /**
      * Getter for password.
      *
      * @return password.
      */
    public String getPassword() {
         return password;
     }
 }