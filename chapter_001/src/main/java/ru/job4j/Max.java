package ru.job4j;

/**
 * Class Max.
 * @author ayuzyak.
 * @version 1.
 * @since 14.12.2016.
*/
public class Max {
    /**
     * Get max from two.
     * @param first.
     * @param second.
     * @return.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Get max from three.
     * @param first.
     * @param second.
     * @param third.
     * @return.
     */
    public int max(int first, int second, int third) {
        return  max(max(first, second), third);
    }
}