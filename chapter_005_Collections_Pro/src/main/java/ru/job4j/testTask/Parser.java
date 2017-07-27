package ru.job4j.testTask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Parser for XML-file with orders.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 25.07.2017
 */
public class Parser {
    /**
     * Scan and parse XML-file of orderBook.
     * @param path of XML-file.
     * @return map with name of orderBook as key and map with ID and order as value.
     */
    public Map<String, HashMap<Integer, Order>> scan(String path) {
        Map<String, HashMap<Integer, Order>> bookList = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.charAt(1) == 'A') {
                    String[] array = parseAdd(s);
                    int id = Integer.valueOf(array[4]);
                    HashMap<Integer, Order> map = bookList.get(array[0]);
                    if (map == null) {
                        map = new HashMap<>();
                        bookList.put(array[0], map);
                    }
                    map.put(id, new Order(array[0], array[1], Float.valueOf(array[2]), Integer.valueOf(array[3]),
                            Integer.valueOf(array[4])));
                } else if (s.charAt(1) == 'D') {
                    String[] array = parseDelete(s);
                    int id = Integer.valueOf(array[1]);
                    bookList.get(array[0]).remove(id);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    /**
     * Parse orders for add.
     * @param s for parsing.
     * @return array of strings.
     */
    public String[] parseAdd(String s) {
        char[] arr = s.toCharArray();
        String[] array = new String[5];
        boolean flag = false;
        int start = 15;
        int index = 0;
        for (int end = start; end < arr.length - 2; end++) {
            if (arr[end] == '\"') {
                if (flag) {
                    array[index++] = s.substring(start + 1, end);
                    flag = false;
                } else {
                    flag = true;
                }
                start = end;
            }
        }
        return array;
    }

    /**
     * Parse orders for delete.
     * @param s for parsing.
     * @return array of strings.
     */
    public String[] parseDelete(String s) {
        char[] arr = s.toCharArray();
        String[] array = new String[2];
        boolean flag = false;
        int start = 17;
        int index = 0;
        for (int end = start; end < arr.length - 2; end++) {
            if (arr[end] == '\"') {
                if (flag) {
                    array[index++] = s.substring(start + 1, end);
                    flag = false;
                } else {
                    flag = true;
                }
                start = end;
            }
        }
        return array;
    }

    /**
     * Main method.
     * @param args from comandline.
     * @throws InterruptedException for IO.
     */
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Parser parser = new Parser();
        Map<String, HashMap<Integer, Order>> books = parser.scan("D:\\orders.xml");
        Map<String, OrderBook> map = new TreeMap<>();
        for (Map.Entry<String, HashMap<Integer, Order>> m : books.entrySet()) {
            String name = m.getKey();
            OrderBook book = new OrderBook(name, m.getValue());
            map.put(name, book);
            book.addOrdersToBooks();
        }
        for (Map.Entry<String, OrderBook> m : map.entrySet()) {
            System.out.println(String.format("%s\t\t\t\t\t\t\t\t%s", System.lineSeparator(), m.getKey().toUpperCase()));
            m.getValue().showAndFillLists();
        }
        for (Map.Entry<String, OrderBook> m : map.entrySet()) {
            m.getValue().matchBIDtoASK();
            m.getValue().showAfterDeal();
        }
        System.out.println("Work time - " + (System.currentTimeMillis() - start) + " ms");
    }
}