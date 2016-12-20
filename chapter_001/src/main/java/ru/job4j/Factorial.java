package ru.job4j;

/**
** Counter class.
 * @author ayuzyak.
 * @version 1.
 * @since 18.12.2016.
 */
public class Factorial {

    /**
     * Calculate factorial
     * @param n - factorial
     * @return
     */
    public int calculate(int n) {
        int factorial = 1;
        while (n > 0) {
            factorial *= n;
            n--;
        }
        return factorial;
    }
}