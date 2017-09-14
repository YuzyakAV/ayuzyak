package ru.job4j.wait_notify_notifyall;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * ProducerConsumer.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 14.09.2017
 * @param <T> for producing and consuming.
 */
public class ProducerConsumer<T> {
    /**
     * Queue for storing elements.
     */
    private Queue<T> queue;

    /**
     * Constructor.
     */
    public ProducerConsumer() {
        queue = new LinkedList<T>();
    }

    /**
     * Producing elements.
     *
     * @param elem for produce.
     */
    public void produce(T elem) {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " добавляет в очередь задание");
            queue.add(elem);
            System.out.println(Thread.currentThread().getName()
                    + " оповещает работника о том, что для него появилась работа " + elem);
            notify();
        }
    }

    /**
     * Consuming elements.
     *
     * @throws InterruptedException when invoke wait().
     */
    public void consume() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " ждет пока появится работа в очереди");
                wait();
            }
            T element = queue.remove();
            System.out.println(Thread.currentThread().getName() + " приступил к " + element);
        }
    }

    /**
     * Main for checking.
     *
     * @param args from cmdLine.
     */
    public static void main(String[] args) {
        ProducerConsumer<String> producerConsumer = new ProducerConsumer<>();

        Runnable producer = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " думает чем занять своих работников");
                    TimeUnit.MILLISECONDS.sleep(100);
                    producerConsumer.produce("Копка траншеи");
                    producerConsumer.produce("Заливка фундамента");
                    producerConsumer.produce("Уборка территории");
                    producerConsumer.produce("Посадка деревьев");
                    System.out.println(Thread.currentThread().getName() + " устал давать задания и хочет передохнуть");
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "отдохнул и придумал еще несколько задач");
                    producerConsumer.produce("Чистка уборной");
                    producerConsumer.produce("Доставка пива");
                    producerConsumer.produce("Приготовление ужина");
                    System.out.println(Thread.currentThread().getName() + " ждёт пока все задачи выполнятся");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " отправляет работников отдыхать");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        /**
         * Local inner class for consuming.
         */
        class Consumer implements Runnable {
            @Override
            public void run() {
                try {
                    while (!Thread.interrupted()) {
                        producerConsumer.consume();
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " пошел спать");
                }

            }
        }

        Thread boss = new Thread(producer, "Начальник");
        Thread worker1 = new Thread(new Consumer(), "Васёк");
        Thread worker2 = new Thread(new Consumer(), "Колян");
        Thread worker3 = new Thread(new Consumer(), "Толик");
        worker1.start();
        worker2.start();
        worker3.start();
        boss.start();
        try {
            boss.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker1.interrupt();
        worker2.interrupt();
        worker3.interrupt();
    }
}