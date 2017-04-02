package ru.job4j.collectionsFramework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.HashSet;

/**
 * Class for testing speed collections.
 *
 * @author Ayuzyak
 * @since 31.03.2017
 * @version 1.0
 */
public class CollectionRating {
    /**
     * Measures the time for insert elements to collection.
     * @param collection for rating.
     * @param line for insert.
     * @param amount of insert.
     * @return the insertion time in ms.
     */
    public long add(Collection<String> collection, String line, int amount) {
        long start = System.currentTimeMillis();

        while (amount > 0) {
            collection.add(line);
            amount--;
        }

        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * Measures the time for delete elements from collection by iterator.
     * @param collection for rating.
     * @param amount of delete.
     * @return the time delete in ms.
     */
    public long deleteByIterator(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();

        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext() && amount > 0) {
            iterator.next();
            iterator.remove();
            amount--;
        }

        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * Measures the time for delete elements from collection by index.
     * @param list for rating.
     * @param amount of delete.
     * @return the time delete in ms.
     */
    public long deleteByIndex(List<String> list, int amount) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < amount && i < list.size(); i++) {
            list.remove(0);
        }

        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * Testing operations insert and delete.
     * @param args of command-line arguments.
     */
    public static void main(String[] args) {
        CollectionRating collectionRating = new CollectionRating();
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        TreeSet<String> treeSet = new TreeSet<>();
        HashSet<String> hashSet = new HashSet<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        System.out.println("Time of insert:");
        System.out.println("ArrayList = " + collectionRating.add(arrayList, "String", 6_000_000) + " ms");
        System.out.println("LinkedList = " + collectionRating.add(linkedList, "String", 6_000_000) + " ms");
        System.out.println("TreeSet = " + collectionRating.add(treeSet, "String", 6_000_000) + " ms");
        System.out.println("HashSet = " + collectionRating.add(hashSet, "String", 6_000_000) + " ms");
        System.out.println("LinkedHashSet = " + collectionRating.add(linkedHashSet, "String", 6_000_000) + " ms");

        arrayList.clear();
        linkedList.clear();
        treeSet.clear();
        hashSet.clear();
        linkedHashSet.clear();
        collectionRating.add(arrayList, "String", 200_000);
        collectionRating.add(linkedList, "String", 200_000);
        collectionRating.add(treeSet, "String", 200_000);
        collectionRating.add(hashSet, "String", 200_000);
        collectionRating.add(linkedHashSet, "String", 200_000);
        System.out.println();
        System.out.println("Time of delete by iterator:");
        System.out.println("ArrayList = " + collectionRating.deleteByIterator(arrayList, 150_000) + " ms");
        System.out.println("LinkedList = " + collectionRating.deleteByIterator(linkedList, 150_000) + " ms");
        System.out.println("TreeSet = " + collectionRating.deleteByIterator(treeSet, 150_000) + " ms");
        System.out.println("HashSet = " + collectionRating.deleteByIterator(hashSet, 150_000) + " ms");
        System.out.println("LinkedHashSet = " + collectionRating.deleteByIterator(linkedHashSet, 150_000) + " ms");

        arrayList.clear();
        linkedList.clear();
        collectionRating.add(arrayList, "String", 200_000);
        collectionRating.add(linkedList, "String", 200_000);
        System.out.println();
        System.out.println("Time of delete by index:");
        System.out.println("ArrayList = " + collectionRating.deleteByIndex(arrayList, 150_000) + " ms");
        System.out.println("LinkedList = " + collectionRating.deleteByIndex(linkedList, 150_000) + " ms");
    }
}