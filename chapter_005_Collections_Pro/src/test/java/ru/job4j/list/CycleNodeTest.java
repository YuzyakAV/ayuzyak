package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for CycleNode.
 *
 * @author Ayuzyak
 * @since 02.06.2017
 * @version 1.0
 */
public class CycleNodeTest {
    /**
     * test method hasCycle().
     * If list of Nodes has cycle.
     */
    @Test
    public void whenListOfNodesHasCycleThenReturnTrue() {
        Node<Integer> firstNode = new Node<>(1);
        Node<Integer> secondNode = new Node<>(2);
        Node<Integer> thirdNode = new Node<>(3);
        Node<Integer> fourNode = new Node<>(4);

        firstNode.setNext(secondNode);
        secondNode.setNext(thirdNode);
        thirdNode.setNext(fourNode);
        fourNode.setNext(firstNode);

        CycleNode cycleNode = new CycleNode();
        final boolean resultCycle = cycleNode.hasCycle(firstNode);
        final boolean checkCycle = true;
        assertThat(resultCycle, is(checkCycle));
    }

    /**
     * test method hasCycle().
     * If list of Nodes hasn't cycle.
     */
    @Test
    public void whenListOfNodesHasNotCycleThenReturnFalse() {
        Node<Integer> firstNode = new Node<>(1);
        Node<Integer> secondNode = new Node<>(2);
        Node<Integer> thirdNode = new Node<>(3);
        Node<Integer> fourNode = new Node<>(4);

        firstNode.setNext(secondNode);
        secondNode.setNext(thirdNode);
        thirdNode.setNext(fourNode);

        CycleNode cycleNode = new CycleNode();
        final boolean resultCycle = cycleNode.hasCycle(firstNode);
        final boolean checkCycle = false;
        assertThat(resultCycle, is(checkCycle));
    }
}