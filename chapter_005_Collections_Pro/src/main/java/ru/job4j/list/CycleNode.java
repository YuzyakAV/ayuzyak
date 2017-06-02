package ru.job4j.list;

/**
 * Class for check cycle.
 *
 * @author Ayuzyak
 * @since 31.05.2017
 * @version 1.0
 */
public class CycleNode {

    /**
     * Check cycle.
     * @param first Node.
     * @return true if list of node has cycle.
     */
    public boolean hasCycle(Node first) {
        Node tortoise = first;
        Node hare = first;
        do {
            if (hare == null || hare.getNext() == null) {
                return false;
            }
            tortoise = tortoise.getNext();
            hare = hare.getNext().getNext();
        } while (!tortoise.equals(hare));
        return true;
    }
}

/**
 * Node.
 * @param <T> element.
 */
class Node<T> {
    /**
     * Value that contains Node.
     */
    private T value;

    /**
     * Next Node.
     */
    private Node<T> next;

    /**
     * Constructor for Node.
     * @param value for Node.
     */
    Node(T value) {
        this.value = value;
    }

    /**
     * Setter for next().
     * @param next for setting.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Getter for next.
     * @return next.
     */
    public Node<T> getNext() {
        return next;
    }
}