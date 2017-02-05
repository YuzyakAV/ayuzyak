package ru.job4j;

/**
 * Counter class.
 * @author ayuzyak.
 * @version 1.
 * @since 18.12.2016.
 */
public class Counter {

    /**
     * Calculate even numbers.
     * @param start - from
     * @param finish - to
     * @return
     */
    public int add(int start, int finish) {
        int summ = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 != 0) {
                 summ += i;
              }

         }
        return  summ;
    }
}
