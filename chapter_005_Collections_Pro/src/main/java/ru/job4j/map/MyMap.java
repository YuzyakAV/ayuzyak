package ru.job4j.map;

import ru.job4j.list.ArrayContainer;

import java.util.Iterator;

/**
 * Class MyMap.
 *
 * @author Ayuzyak
 * @since 21.06.2017
 * @version 1.0
 * @param <K> type of Keys.
 * @param <V> type of Values.
 */
public class MyMap<K, V> implements Iterable<K> {
    /**
     * Array of nodes.
     */
    private Node[] table;

    /**
     * Size of table.
     */
    private int tabSize;

    /**
     * Number of elements that contains map.
     */
    private int factSize;

    /**
     * Map constructor.
     */
    public MyMap() {
        this.tabSize = 16;
        this.table = new Node[tabSize];
    }

    /**
     * Adding elements to map.
     * @param key for adding.
     * @param value for adding.
     * @return previous key value
     */
    public V insert(K key, V value) {
        grow();
        int index = hash(key);
        if (table[index] != null) {
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    V prevValue = node.value;
                    node.value = value;
                    factSize++;
                    return prevValue;
                }
                if (node.next == null) {
                    Node<K, V> newNode = new Node<>(key, value, null);
                    node.next = newNode;
                    break;
                }
                node = node.next;
            }
        } else {
            table[index] = new Node<>(key, value, null);
        }
        factSize++;
        return null;
    }

    /**
     * Getting value of key. If map hasn't key return null.
     * @param key for getting value.
     * @return value of key.
     */
    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
        }
        return null;
    }

    /**
     * Deleting key from map.
     * @param key for delete.
     * @return true if key deleted.
     */
    public boolean delete(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Node<K, V> node = table[index];
            if (node.key.equals(key)) {
                if (node.next != null) {
                    table[index] = node.next;
                } else {
                    table[index] = null;
                }
                factSize--;
                return true;
            }
            Node<K, V> prevNode = node;
            while (node != null) {
                if (node.key.equals(key)) {
                    if (node.next != null) {
                        prevNode.next = node.next;
                        node.next = null;
                    } else {
                        prevNode.next = null;
                    }
                    factSize--;
                    return true;
                }
                prevNode = node;
                node = node.next;
            }
        }
        return false;
    }

    /**
     * Check map for containing key.
     * @param key for check.
     * @return true if map contains key.
     */
    public boolean containsKey(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Node<K, V> node = table[index];
            while (node != null) {
                if (node.key.equals(key)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    /**
     * Value for assignment index to element.
     * @param key for calculate.
     * @return int value.
     */
    private int hash(K key) {
        return key.hashCode() % tabSize;
    }

    /**
     * Increase capacity of table and rehash all elements.
     */
    private void grow() {
        if (factSize >= 0.75 * tabSize) {
            Node<K, V>[] copyTable = table;
            tabSize *= 2;
            factSize = 0;
            table = new Node[tabSize];
            for (int i = 0; i < copyTable.length; i++) {
                if (copyTable[i] != null) {
                    Node<K, V> node = copyTable[i];
                    while (node != null) {
                        insert(node.key, node.value);
                        node = node.next;
                    }
                }
            }
        }
    }

    /**
     * Getting iterator for passing map.
     * @return iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new Itr();
    }

    /**
     * Class Node. Contains key and value.
     * @param <K> - type of key.
     * @param <V> - type of value.
     */
    private class Node<K, V> {
        /**
         * Unique key.
         */
        private final K key;

        /**
         * Value of key.
         */
        private V value;

        /**
         * Reference to next element (node).
         */
        private Node next;

        /**
         * Node constructor.
         * @param key - key for adding ti map.
         * @param value - value of key's.
         * @param next - newt value.
         */
        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Iterator for passing all elements of set.
     * @param <K> type of elements.
     */
    private class Itr<K> implements Iterator<K> {
        /**
         * All elements container.
         */
        private final ArrayContainer<K> keyList = new ArrayContainer<K>();

        /**
         * Container Iterator.
         */
        private Iterator<K> iterator;

        /**
         * Iterator constructor.
         */
        Itr() {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    Node<K, V> node = table[i];
                    while (node != null) {
                        keyList.add(node.key);
                        node = node.next;
                    }
                }
            }
            iterator = keyList.iterator();
        }

        /**
         * Check next element.
         * @return true if iterator has next number.
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         * Get next element.
         * @return next element.
         */
        @Override
        public K next() {
            return iterator.next();
        }
    }
}