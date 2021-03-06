package ru.job4j.collectionsFramework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for convert two-dimensional Array to ArrayList and backward and convert list of int arrays to list of integers.
 *
 * @author Ayuzyak
 * @since 30.03.2017
 * @version 1.0
 */
public class ConvertList {
    /**
     * Convert two-dimensional Array to ArrayList.
     * @param array two-dimensional.
     * @return converted ArrayList.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    /**
     * Convert ArrayList to two-dimensional Array.
     * @param list for convert.
     * @param rows - number of rows in two-dimensional Array.
     * @return converted two-dimensional Array.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int columns;
        if (list.size() % rows == 0) {
            columns = list.size() / rows;
        } else {
            columns = (list.size() / rows) + 1;
        }
        Iterator<Integer> iterator = list.iterator();
        int[][] array = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }

    /**
     * Convert list of arrays to one ArrayList.
     * @param list for convert.
     * @return converted ArrayList.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> arrayList = new ArrayList<>();
        for (int[] array : list) {
            for (int i = 0; i < array.length; i++) {
                arrayList.add(array[i]);
            }
        }
        return arrayList;
    }
}