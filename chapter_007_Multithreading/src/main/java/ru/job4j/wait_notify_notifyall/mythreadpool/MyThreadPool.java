package ru.job4j.wait_notify_notifyall.mythreadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * MyThreadPool.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 19.09.2017
 */
public class MyThreadPool {
    /**
     * Array of threads.
     */
    private Thread[] threads;

    /**
     * BlockingQueue of works.
     */
    private BlockingQueue<Work> workers = new LinkedBlockingQueue<>();

    /**
     * Constructor for MyThreadPool. Initialize number of threads equals number of processors.
     */
    public MyThreadPool() {
        threads = new Thread[Runtime.getRuntime().availableProcessors()];
    }

    /**
     * Adding work to pool.
     * @param work with work.
     */
    public void add(Work work) {
        try {
            workers.put(work);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the threads and does all the work from the queue.
     */
    public void execute() {
        CountDownLatch starter = new CountDownLatch(threads.length);

        /**
         * Local inner class for thread.
         */
        class Workable implements Runnable {
            @Override
            public void run() {
                try {
                    starter.await();
                    System.out.println(Thread.currentThread().getName() + " начал работу");
                    while (!workers.isEmpty()) {
                        workers.take().doWork();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " закончил работу");
            }
        }

        for (int i = 1; i <= threads.length; i++) {
            new Thread(new Workable(), "Поток № " + i).start();
            starter.countDown();
        }
    }

    /**
     * Main for showing executing.
     * @param args from cmdLine.
     */
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool();
        for (int i = 0; i < 30; i++) {
            pool.add(new Work());
        }
        pool.execute();
    }
}