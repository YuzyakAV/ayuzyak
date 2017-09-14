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
            System.out.println(Thread.currentThread().getName() + " ��������� � ������� �������");
            queue.add(elem);
            System.out.println(Thread.currentThread().getName()
                    + " ��������� ��������� � ���, ��� ��� ���� ��������� ������ " + elem);
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
                System.out.println(Thread.currentThread().getName() + " ���� ���� �������� ������ � �������");
                wait();
            }
            T element = queue.remove();
            System.out.println(Thread.currentThread().getName() + " ��������� � " + element);
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
                    System.out.println(Thread.currentThread().getName() + " ������ ��� ������ ����� ����������");
                    TimeUnit.MILLISECONDS.sleep(100);
                    producerConsumer.produce("����� �������");
                    producerConsumer.produce("������� ����������");
                    producerConsumer.produce("������ ����������");
                    producerConsumer.produce("������� ��������");
                    System.out.println(Thread.currentThread().getName() + " ����� ������ ������� � ����� �����������");
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "�������� � �������� ��� ��������� �����");
                    producerConsumer.produce("������ �������");
                    producerConsumer.produce("�������� ����");
                    producerConsumer.produce("������������� �����");
                    System.out.println(Thread.currentThread().getName() + " ��� ���� ��� ������ ����������");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " ���������� ���������� ��������");
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
                    System.out.println(Thread.currentThread().getName() + " ����� �����");
                }

            }
        }

        Thread boss = new Thread(producer, "���������");
        Thread worker1 = new Thread(new Consumer(), "������");
        Thread worker2 = new Thread(new Consumer(), "�������");
        Thread worker3 = new Thread(new Consumer(), "�����");
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