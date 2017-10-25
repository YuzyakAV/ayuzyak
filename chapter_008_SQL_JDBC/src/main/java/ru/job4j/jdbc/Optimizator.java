package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class Optimizator.
 * Create new Table in database, getting data from this table and create 1.xml file in current directory.
 * Then parse 1.xml by XSLT and create 2.xml.
 * Then parse 2.xml and sum all integer fields.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 20.10.2017
 */
public class Optimizator {
    /**
     * Number of rows for inserting.
     */
    private int rowNumber;

    /**
     * Database URL.
     */
    private String url = "";

    /**
     * Constructor for Optimizator.
     */
    public Optimizator() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Укажите адрес базы данных SQLite либо введите пустую строку "
                    + "(будет выбрана база по умолчанию)");
            this.url = scanner.nextLine();
            System.out.println("Введите число записей для вставки в базу данных");
            this.rowNumber = scanner.nextInt();
        }
    }

    /**
     * Start application.
     * @return sum of all rows.
     */
    public long start() {
        long sum = 0;
        SQLTableCreator tableCreator = new SQLTableCreator();
        if (this.url.equals("")) {
            tableCreator.setUrl();
        } else {
            tableCreator.setUrl(this.url);
        }
        tableCreator.setMaxCount(this.rowNumber);
        XMLCreator xmlCreator = new XMLCreator();
        xmlCreator.setTableName("test");
        xmlCreator.setFieldName("field");
        XMLTransformer transformer = new XMLTransformer();
        XMLSumParser sumParser = new XMLSumParser();
        try (Connection con = tableCreator.getConnection()) {
            tableCreator.insertRows(con);
            xmlCreator.createXML(con);
            transformer.transform();
            sum = sumParser.parseAndSum();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    /**
     * PSVM.
     * @param args from CMD-Line.
     */
    public static void main(String[] args) {
        Optimizator optimizator = new Optimizator();
        long start = System.currentTimeMillis();
        System.out.println("Арифметическая сумма всех записей равна - " + optimizator.start());
        System.out.println("Время выполнения программы - " + (System.currentTimeMillis() - start) / 1000 + " сек");
    }
}