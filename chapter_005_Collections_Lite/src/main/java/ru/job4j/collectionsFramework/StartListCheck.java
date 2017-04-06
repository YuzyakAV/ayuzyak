package ru.job4j.testingPerfomance;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.UUID;


/**
 * Class for testing speed collections.
 *
 * @author Ayuzyak
 * @since 31.03.2017
 * @version 1.0
 */

class TimeListCheck {

    /**
     * Add long.
     *
     * @param collection the collection
     * @param line       the line
     * @param amount     the amount
     * @return the long
     */
    public long add(Collection<String> collection, String line, int amount) {
        long startTimer = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            collection.add(line);
        }
        long stopTimer = System.nanoTime();
        return stopTimer - startTimer;
    }

    /**
     * Delete long.
     *
     * @param collection the collection
     * @param amount     the amount
     * @return the long
     */
    public long delete(Collection<String> collection, int amount) {
        Iterator<String> iter = collection.iterator();
        long startTimer = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            if (iter.hasNext()) {
                iter.next();
                iter.remove();
            }
        }
        long stopTimer = System.nanoTime();
        return stopTimer - startTimer;
    }

}

/**
 * The type Start list check.
 */
public class StartListCheck {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        TimeListCheck timeListCheck = new TimeListCheck();

        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        TreeSet<String> treeSet = new TreeSet<>();

        long addArrayList = 0;
        long addLinkedList = 0;
        long addTreeSet = 0;
        String testString;


        for (int j = 0; j < 10000; j++) {
            UUID uuid = UUID.randomUUID();
            testString = uuid.toString();
            addArrayList += timeListCheck.add(arrayList, testString, 100);
            addLinkedList += timeListCheck.add(linkedList, testString, 100);
            addTreeSet += timeListCheck.add(treeSet, testString, 100);
        }

        System.out.println("Add ArrayList: \t\t" + addArrayList);
        System.out.println("Add LinkedList: \t" + addLinkedList);
        System.out.println("Add TreeSet: \t\t" + addTreeSet);
        System.out.println();
        System.out.println("Remove ArrayList: \t\t" + timeListCheck.delete(arrayList, 1000));
        System.out.println("Remove LinkedList: \t\t" + timeListCheck.delete(linkedList, 1000));
        System.out.println("Remove TreeSet: \t\t" + timeListCheck.delete(treeSet, 1000));

    }
}