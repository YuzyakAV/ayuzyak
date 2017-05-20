package ru.job4j.iterator;

/**
 * Class for check numbers.
 *
 * @author Ayuzyak
 * @since 20.05.2017
 * @version 1.0
 */
public class SimpleN {
    /**
     * Static method for check number for simplicity.
     * @param n - number for check.
     * @return true if number is simple.
     */
    public static boolean checkSimple(int n) {
        if (n == 0 || n == 1) {
            return false;
        }
        boolean isSimple = true;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                isSimple = false;
                i = n;
            }
        }
        return isSimple;
    }
}