package ru.job4j;

/**
 * Turn array class.
 * @author ayuzyak.
 * @version 1.
 * @since 21.12.2016.
 */
public class Turn {

    /**
     * Reverse array method.
     * @param array - entire array
     * @return
     */
    public int[] back(int[] array) {

        int startIndex = 0;
        int endIndex = array.length - 1;

        while (startIndex < endIndex) {
            int temp = array[startIndex];

            array[startIndex] = array[endIndex];
            array[endIndex] = temp;

            startIndex++;
            endIndex--;
        }

        return array;
    }
}