package ru.job4j.collectionsFramework;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for ConvertList.
 *
 * @author Ayuzyak
 * @since 30.03.2016
 * @version 1.0
 */
public class ConvertListTest {
    /**
     * Test convert two-dimensional Array to ArrayList.
     */
    @Test
    public void whenConvertArrayToList() {
        final int[][] srcArray = {
                {1, 2},
                {3, 4, 5, 6},
                {7, 8, 9}};
        ConvertList convertList = new ConvertList();
        List<Integer> resultList = convertList.toList(srcArray);
        List<Integer> checkList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            checkList.add(i);
        }
        assertThat(resultList, is(checkList));
    }

    /**
     * Test convert ArrayList to two-dimensional Array.
     */
    @Test
    public void whenConvertListToArray() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            list.add(i);
        }
        final int[][] resultArray = convertList.toArray(list, 3);
        final int[][] checkArray = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}};

        assertThat(resultArray, is(checkArray));
    }

    /**
     * Test convert list of arrays to one ArrayList.
     */
    @Test
    public void whenConvertListOfArraysToOneList() {
        final int[] firstArray = {1, 2, 3};
        final int[] secondArray = {4, 5, 6};
        final int[] thirdArray = {7, 8, 9};
        List<int[]> listOfArrays = new ArrayList<>();
        listOfArrays.add(firstArray);
        listOfArrays.add(secondArray);
        listOfArrays.add(thirdArray);
        ConvertList convertList = new ConvertList();
        List<Integer> resultList = convertList.convert(listOfArrays);
        List<Integer> checkList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            checkList.add(i);
        }
        assertThat(resultList, is(checkList));
    }
}