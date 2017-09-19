package ru.job4j.wait_notify_notifyall.mythreadpool;

/**
 * Work.
 *
 * @author Ayuzyak
 * @since 19.09.2017
 * @version 1.0
 */
public class Work {
    /**
     * ID for all work.
     */
    private static int commonID = 0;

    /**
     * ID for this work.
     */
    private int id;

    /**
     * Constructor.
     */
    public Work() {
        this.id = ++commonID;
    }

    /**
     * Do some work.
     */
    public void doWork() {
        double sillySum = 0;
        for (int i = 0; i < 90_000_000; i++) {
            sillySum = sillySum + (Math.PI + Math.E) / sillySum;
        }
        System.out.println("Рабочий № " + id + " выполнил свою работу");
    }
}