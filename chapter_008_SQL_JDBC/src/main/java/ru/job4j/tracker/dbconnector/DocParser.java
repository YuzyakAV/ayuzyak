package ru.job4j.tracker.dbconnector;
 
 import org.w3c.dom.Document;
 import org.xml.sax.SAXException;
 
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import java.io.File;
 import java.io.IOException;
 
 /**
  * Class for getting document to parse.
  *
  * @author Ayuzyak
  * @version 1.0
  * @since 13.10.2017
  */
 public class DocParser {
     /**
      * Document for parsing.
      */
     private Document document;
 
     /**
      * Constructor for DocParser.
      *
      * @param path to file.
      * @throws ParserConfigurationException when has serious configuration error.
      * @throws IOException                  Signals that an I/O exception of some sort has occurred.
      * @throws SAXException                 Encapsulate a general SAX error or warning.
      */
     public DocParser(String path) throws ParserConfigurationException, IOException, SAXException {
         DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = builderFactory.newDocumentBuilder();
         File file = new File(path);
         this.document = builder.parse(file);
     }
 
     /**
      * Getter for document.
      *
      * @return document.
      */
     public Document getDocument() {
         return document;
     }
 }