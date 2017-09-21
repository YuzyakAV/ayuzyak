package ru.job4j.wait_notify_notifyall;

import java.util.concurrent.TimeUnit;

/**
 * MyLock.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 21.09.2017
 */
public class MyLock {
    /**
     * Boolean for checking lock.
     */
    private boolean isLock = false;

    /**
     * Lock monitor.
     */
    public synchronized void lock() {
        try {
            while (isLock) {
                wait();
            }
            isLock = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unlock monitor.
     */
    public synchronized void unlock() {
        if (isLock) {
            isLock = false;
            notifyAll();
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /**
     * Main for showing locking and unlocking.
     *
     * @param args from cmdLine.
     */
    public static void main(String[] args) {
        MyLock lock = new MyLock();

        new Thread(() -> {
            try {
                System.out.println("Втростепенный поток ждет пока главный захватит блокировку");
                TimeUnit.MILLISECONDS.sleep(20);
                System.out.println("Второстепенный поток пытается захватить блокировку");
                lock.lock();
                System.out.println("Второстепенный поток захватил блокировку");
                System.out.println("Второстепенный поток делаает работу........");
                lock.unlock();
                System.out.println("Второстепенный поток отпускает блокировку и заканчивает свою работу");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        lock.lock();
        System.out.println("Основной поток захватил блокировку");
        try {
            System.out.println("Основной поток засыпает ненадолго");
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        System.out.println("Основной поток отпускает блокировку и заканчивает свою работу");
    }
}