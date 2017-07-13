package ru.job4j.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for BinTree.
 *
 * @author Ayuzyak
 * @since 13.07.2017
 * @version 1.0
 */
public class BinTreeTest {
    /**
     * test put() and getAllKeys().
     * Adding to tree elements and get them.
     */
    @Test
    public void whenAddElementsToTreeAndGetAllKeysThenReturnList() {
        BinTree<Integer> tree = new BinTree<>();
        tree.put(50);
        tree.put(100);
        tree.put(30);
        tree.put(10);
        tree.put(110);
        tree.put(60);
        List<Integer> resultList = tree.getAllKeys();
        List<Integer> checkList = new ArrayList<>(Arrays.asList(50, 30, 10, 100, 60, 110));
        assertThat(resultList, is(checkList));
    }

    /**
     * test contains().
     * If tree has not element.
     */
    @Test
    public void whenTreeHasNotElementAndInvokeContainsForThisElementThenReturnFalse() {
        BinTree<Integer> tree = new BinTree<>();
        tree.put(50);
        tree.put(100);
        tree.put(30);
        tree.put(10);
        boolean resultContain = tree.contains(120);
        boolean checkContain = false;
        assertThat(resultContain, is(checkContain));
    }

    /**
     * test contains().
     * If tree has same element.
     */
    @Test
    public void whenTreeHasElementAndInvokeContainsForThisElementThenReturnTrue() {
        BinTree<Integer> tree = new BinTree<>();
        tree.put(50);
        tree.put(100);
        tree.put(30);
        tree.put(10);
        boolean resultContain = tree.contains(30);
        boolean checkContain = true;
        assertThat(resultContain, is(checkContain));
    }
}