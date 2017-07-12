package ru.job4j.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class SimpleTree.
 *
 * @author Ayuzyak
 * @since 10.07.2017
 * @version 1.0
 * @param <E> type of elements.
 */
public class SimpleTree<E> {
    /**
     * Root of tree.
     */
    private Leaf<E> root;

    /**
     * Inner static class Leaf.
     * @param <E> type of key.
     */
    public static class Leaf<E> {
        /**
         * Key.
         */
        private E key;

        /**
         * ArrayList of children Leaf's.
         */
        private ArrayList<Leaf<E>> children = new ArrayList<>();

        /**
         * Constructor for Leaf.
         * @param key for storage.
         */
        public Leaf(E key) {
            this.key = key;
        }

        /**
         * toString.
         * @return key.toString.
         */
        @Override
        public String toString() {
            return key.toString();
        }

        /**
         * Comparing two leafs for equals.
         * @param o other object.
         * @return true if leafs are same.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Leaf<?> leaf = (Leaf<?>) o;
            return Objects.equals(key, leaf.key);
        }

        /**
         * Get int hash code for leaf.
         * @return int hash code.
         */
        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    /**
     * Add child-leaf with key to parent-leaf.
     * @param parent leaf.
     * @param key of adding child.
     * @return added child-leaf.
     */
    public Leaf<E> addChild(Leaf parent, E key) {
        Leaf<E> son = null;
        if (root == null) {
            root = parent;
            son = new Leaf<E>(key);
            parent.children.add(son);
        } else {
            Leaf<E> leaf = findParent(parent, root);
            if (leaf != null) {
                son = new Leaf(key);
                leaf.children.add(son);
            } else {
                throw new NoSuchParentException();
            }
        }
        return son;
    }

    /**
     * Get all keys of leafs except root-leaf.
     * @return list of keys.
     */
    public List<E> getChildren() {
        List<E> list = new ArrayList<E>();
        if (root != null) {
            getRecursiveListOfChildren(root, list);
        }
        return list;
    }

    /**
     * Get list of all children of the leaf.
     * @param leaf parent.
     * @param list for adding keys.
     * @return list of keys.
     */
    private List<E> getRecursiveListOfChildren(Leaf<E> leaf, List<E> list) {
        Leaf<E> currentRoot = leaf;
        for (int i = 0; i < currentRoot.children.size(); i++) {
            list.add(currentRoot.children.get(i).key);
            if (currentRoot.children.get(i).children.size() > 0) {
                getRecursiveListOfChildren(currentRoot.children.get(i), list);
            }
        }
        return list;
    }

    /**
     * Get all children's keys from parent.
     * @param leaf parent.
     * @return list of keys.
     */
    public List<E> getChildrenFromParent(Leaf<E> leaf) {
        Leaf<E> parent = this.findParent(leaf, root);
        List<E> list = new ArrayList<E>();
        for (int i = 0; i < parent.children.size(); i++) {
            list.add(parent.children.get(i).key);
        }
        return list;
    }

    /**
     * Get reference for parent.
     * @param parent for adding leaf.
     * @param currentRoot root of tree.
     * @return reference from
     */
    private Leaf<E> findParent(Leaf<E> parent, Leaf<E> currentRoot) {
        if (currentRoot.equals(parent)) {
            return currentRoot;
        }
        Leaf<E> leafForRecursion;
        Leaf<E> leafParent;
        for (int i = 0; i < currentRoot.children.size(); i++) {
            leafForRecursion = currentRoot.children.get(i);
            leafParent = findParent(parent, leafForRecursion);
            if (leafParent != null) {
                return leafParent;
            }
        }
        return null;
    }

    /**
     * Find element in tree.
     * @param key for searching.
     * @return true if tree contains key.
     */
    public boolean contains(E key) {
        Leaf<E> leaf = new Leaf<>(key);
        Leaf<E> element = findParent(leaf, root);
        return element != null;
    }

    /**
     * Method check tree for balance.
     * @return true if tree is balanced.
     */
    public boolean isBalancedTree() {
        return checkBalance(root);
    }

    /**
     * Additional recursive method for checking balance.
     * @param leaf check children for balance.
     * @return true if children leaf is balanced.
     */
    private boolean checkBalance(Leaf<E> leaf) {
        boolean result = true;
        if (leaf.children.size() != 2 && !leaf.children.isEmpty()) {
            result = false;
        } else {
            for (int i = 0; i < leaf.children.size(); i++) {
                result = checkBalance(leaf.children.get(i));
                if (!result) {
                    return false;
                }
            }
        }
        return result;
    }
}