package ru.job4j.testTask;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class OrderBook.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 26.07.2017
 */
public class OrderBook {
    /**
     * Name of orderBook.
     */
    private String name;

    /**
     * TreeMap for BID.
     */
    private TreeMap<Float, Integer>  setBID;

    /**
     * TreeMap for ASK.
     */
    private TreeMap<Float, Integer>  setASK;

    /**
     * HashMap, that contains all orders from XML-file.
     */
    private HashMap<Integer, Order> allOrders;

    /**
     * List for BID-orders after deal (using as stack).
     */
    private LinkedList<Pair> pairsBID;

    /**
     * List for ASK-orders after deal (using as stack).
     */
    private LinkedList<Pair> pairsASK;

    /**
     * Constructor for orderBook.
     * @param name of orderBook.
     * @param allOrders for orderBook.
     */
    public OrderBook(String name, HashMap<Integer, Order> allOrders) {
        this.name = name;
        this.allOrders = allOrders;
        setBID = new TreeMap<>(Comparator.reverseOrder());
        setASK = new TreeMap<>();
        pairsBID = new LinkedList<>();
        pairsASK = new LinkedList<>();
    }

    /**
     * Add order to TreeMap.
     * @param order for adding.
     * @param treeMap map for adding.
     */
    private void addOrder(Order order, TreeMap<Float, Integer> treeMap) {
        Float price = order.getPrice();
        Integer volume = order.getVolume();
        Integer oldVolume = treeMap.get(price);
        if (oldVolume != null) {
            treeMap.put(price, oldVolume + volume);
        } else {
            treeMap.put(price, volume);
        }
    }

    /**
     * Add order to setBID.
     * @param order for adding.
     */
    public void addToSetBID(Order order) {
        addOrder(order, setBID);
    }

    /**
     * Add order to setASK.
     * @param order for adding.
     */
    public void addToSetASK(Order order) {
        addOrder(order, setASK);
    }

    /**
     * Adding into setBID or setASK.
     */
    public void addOrdersToBooks() {
        for (Map.Entry<Integer, Order> m : allOrders.entrySet()) {
            Order order = m.getValue();
            if (order.getOperation().equals("SELL")) {
                addToSetASK(order);
            } else if (order.getOperation().equals("BUY")) {
                addToSetBID(order);
            }
        }
    }

    /**
     * Show BID and ASK of orderBook and add pairs with price and volume of order to lists.
     */
    public void showAndFillLists() {
        System.out.println("\t\t\t\tBID");
        for (Map.Entry<Float, Integer> m : setBID.entrySet()) {
            Float price = m.getKey();
            Integer volume = m.getValue();
            System.out.println(String.format("Volume - %7d @ Price - %6.2f", volume, price));
            pairsBID.add(new Pair(price, volume));
        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tASK");
        for (Map.Entry<Float, Integer> m : setASK.entrySet()) {
            Float price = m.getKey();
            Integer volume = m.getValue();
            System.out.println(String.format("\t\t\t\t\t\t\t\t\tVolume - %7d @ Price - %6.2f", volume, price));
            pairsASK.add(new Pair(price, volume));
        }
    }

    /**
     * Match BID and ASK. (List using as stack).
     */
    public void matchBIDtoASK() {
            while (pairsASK.size() > 0 && pairsBID.size() > 0
                    && pairsBID.peek().getPrice() >= pairsASK.peek().getPrice()) {
                int volumeBID = pairsBID.peek().getVolume();
                int volumeASK = pairsASK.peek().getVolume();
                if (volumeBID > volumeASK) {
                    pairsBID.peek().setVolume(volumeBID - volumeASK);
                    pairsASK.poll();
                } else if (volumeBID < volumeASK) {
                    pairsASK.peek().setVolume(volumeASK - volumeBID);
                    pairsBID.poll();
                } else {
                    pairsASK.poll();
                    pairsBID.poll();
                }
            }

    }

    /**
     * Show BID and ASK orders after matching.
     */
    public void showAfterDeal() {
        System.out.println(String.format("%s\t\t\t\t\t\t\t   %s", System.lineSeparator(), name.toUpperCase()));
        System.out.println("\t\t   BID after deal");
        for (Pair p : pairsBID) {
            System.out.println(String.format("Volume - %7d @ Price - %6.2f", p.getVolume(), p.getPrice()));
        }
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tASK after deal");
        for (Pair p : pairsASK) {
            System.out.println(String.format("\t\t\t\t\t\t\t\t Volume - %7d @ Price - %6.2f", p.getVolume(), p.getPrice()));
        }
    }
}