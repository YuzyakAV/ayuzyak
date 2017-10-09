package ru.job4j.jdbc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class XMLCreator for getting data from dataase and create XML-file (1.xml).
 * @author Ayuzyak
 * @since 08.10.2017
 * @version 1.0
 */
public class XMLCreator {
    /**
     * Table name.
     */
    private String tableName;

    /**
     * Field name.
     */
    private String fieldName;

    /**
     * Table name getter.
     * @return name of table.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Table name setter.
     * @param tableName for setting.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Field name getter.
     * @return field name.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Field name setter.
     * @param fieldName for setting.
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Getting date from database and create XML-file(1.xml).
     * @param con database connection.
     * @throws SQLException when database has connection trouble.
     */
    public void createXML(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", fieldName, tableName));

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("entries");
            document.appendChild(root);

            while (resultSet.next()) {
                int fieldNumber = resultSet.getInt(1);
                Element entry = document.createElement("entry");
                root.appendChild(entry);
                Element field = document.createElement("field");
                entry.appendChild(field);
                Text text = document.createTextNode(String.valueOf(fieldNumber));
                field.appendChild(text);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File("./1.xml"));
            transformer.transform(source, streamResult);
            System.out.println("Файл 1.xml создан");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}