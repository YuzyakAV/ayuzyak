package ru.job4j.jdbc;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Class XMLTransformer for getting data from XML-file (1.xml) and transform it to XML-file (2.xml) by XSLT.
 * @author Ayuzyak
 * @since 08.10.2017
 * @version 1.0
 */
public class XMLTransformer {

    /**
     * Getting data from XML-file (1.xml) and transform it to XML-file (2.xml) by XSLT.
     */
    public void transform() {
        File styleSheet = new File("./styles.xsl");
        File fileSource = new File("./1.xml");
        File fileResult = new File("./2.xml");
        StreamSource styleSource = new StreamSource(styleSheet);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            try {
                transformer.transform(new StreamSource(fileSource), new StreamResult(fileResult));
                System.out.println("Файл 2.xml создан");
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }
}