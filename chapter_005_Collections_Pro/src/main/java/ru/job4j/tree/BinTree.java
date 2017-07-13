package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Class BinTree.
 *
 * @author Ayuzyak
 * @since 10.07.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class BinTree<E extends Comparable> {

    /**
     * Node-root.
     */
    private Node<E> root;

    /**
     * Put key to tree.
     * Put only unique keys.
     * @param key for adding to tree.
     */
    public void put(E key) {
        Node<E> nextNode = root;
        Node<E> prevNode = null;
        while (nextNode != null) {
            int comp = key.compareTo(nextNode.key);
            if (comp < 0) {
                prevNode = nextNode;
                nextNode = nextNode.left;
            } else if (comp > 0) {
                prevNode = nextNode;
                nextNode = nextNode.right;
            } else {
                return;
            }
        }
        Node<E> newNode = new Node<>(key);
        if (prevNode == null) {
            root = newNode;
        } else {
            if (key.compareTo(prevNode.key) < 0) {
                prevNode.left = newNode;
            } else {
                prevNode.right = newNode;
            }
        }
    }

    /**
     * Get all keys of tree.
     * @return list of keys.
     */
    public List<E> getAllKeys() {
        List<E> list = new ArrayList<E>();
        if (root != null) {
            list.add(root.key);
            getRecursiveList(root, list);
        }
        return list;
    }

    /**
     * Addition recursive method for getting all keys.
     * @param node parent.
     * @param list for adding.
     * @return list of keys.
     */
    private List<E> getRecursiveList(Node<E> node, List<E> list) {
        Node<E> rootNode = node;
        if (rootNode.left != null) {
            list.add(rootNode.left.key);
            getRecursiveList(rootNode.left, list);
        }
        if (rootNode.right != null) {
            list.add(rootNode.right.key);
            getRecursiveList(rootNode.right, list);
        }
        return list;
    }

    /**
     * Check tree for containing key.
     * @param key for check.
     * @return true if tree contains key.
     */
    public boolean contains(E key) {
        Node<E> node = root;
        while (node != null) {
            int comp = key.compareTo(node.key);
            if (comp == 0) {
                return true;
            } else {
                if (comp > 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
        }
        return false;
    }

    /**
     * Node for building tree.
     * @param <E> type of key.
     */
    private class Node<E> {
        /**
         * Key for storage.
         */
        private E key;

        /**
         * Reference for left node.
         */
        private Node<E> left;

        /**
         * Reference for right node.
         */
        private Node<E> right;

        /**
         * Constructor for Node.
         * @param key for storage.
         */
        Node(E key) {
            this.key = key;
        }
    }
}