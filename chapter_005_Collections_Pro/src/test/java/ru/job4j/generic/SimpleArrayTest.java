package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for SimpleArray.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 23.05.2017
 */
public class SimpleArrayTest {
    /**
     * SimpleArray.
     */
    private SimpleArray<String> simpleArray;

    /**
     * Method for initialize simpleArray.
     */
    @Before
    public void prepareSimpleArray() {
        simpleArray = new SimpleArray<>();
        simpleArray.add("1");
        simpleArray.add("2");
        simpleArray.add("3");
        simpleArray.add("4");
        simpleArray.add("5");
    }

    /**
     * test method for add(), get(), getSize().
     * Adding 6 elements for check capacity array and getting all elements.
     */
    @Test
    public void whenAddElementsThenCapacityIncreasedAndGetElementsForCheck() {
        for (int i = 6; i < 12; i++) {
            simpleArray.add(String.valueOf(i));
        }
        ArrayList<String> resultlist = new ArrayList<>();
        for (int i = 0; i < simpleArray.getSize(); i++) {
            resultlist.add(simpleArray.get(i));
        }
        ArrayList<String> checkList = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11"));
        assertThat(resultlist, is(checkList));
    }

    /**
     * test method for delete().
     * Delete elements from array and getting all elements.
     */
    @Test
    public void whenDeleteElementsThenGetElementsForCheck() {
        simpleArray.delete(2);
        simpleArray.delete(3);
        ArrayList<String> resultlist = new ArrayList<>();
        for (int i = 0; i < simpleArray.getSize(); i++) {
            resultlist.add(simpleArray.get(i));
        }
        ArrayList<String> checkList = new ArrayList<>(Arrays.asList("1", "2", "4"));
        assertThat(resultlist, is(checkList));
    }

    /**
     * test method for update().
     * Update elements from array and getting all elements.
     */
    @Test
    public void whenUpdateElementsThenGetElementsForCheck() {
        simpleArray.update(2, "Up");
        simpleArray.update(3, "date");
        ArrayList<String> resultlist = new ArrayList<>();
        for (int i = 0; i < simpleArray.getSize(); i++) {
            resultlist.add(simpleArray.get(i));
        }
        ArrayList<String> checkList = new ArrayList<>(Arrays.asList("1", "2", "Up", "date", "5"));
        assertThat(resultlist, is(checkList));
    }
}