package ru.job4j;

import java.util.Arrays;

/**
 * General class for array operations.
 * @author ayuzyak.
 * @version 1.
 * @since 22.12.2016.
 */
public class ArrayOperations {

    /**
     * Bubble sorting.
     * @param array - entire array
     * @return
     */
    public int[] bubble(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if(!swapped)
                break;
        }

        return array;
    }

    /**
     * Turn arrat on CW 90.
     * @param values - provided multidemential array
     * @return
     */
    public int[][] turnArray(int values[][]) {

        int i;
        int n = values.length;

        // Check matrix if it's possible to turn
        for (i = 0; i < n; i++ ) {
            if (n != values[i].length) {
                throw new Error("Provided array isn't squarable.");
            }
        }

        int j;
        int result[][] = new int[n][n];

        // Turn array on CW 90
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < n; j++)
                result[i][j] = values[n - j - 1][i];
        }

        return result;
    }

    /**
     * Remove duplicates from array.
     * @param array - provided array
     * @return
     */
    public String[] removeDuplicates(String array[]) {

        int length = array.length;
        // Duplicates amount
        int duplicates = 0;
        int i = 0;

        while (i < length - duplicates) {
            int j = i + 1;
            while (j < length - duplicates) {
                if (array[i].equals(array[j])) {
                    int k = j;
                    //Shift array to the left
                    while (k < length - 1) {
                        array[k] = array[k + 1];
                        array[k + 1] = null;
                        k++;
                    }
                    duplicates++;
                }
                j++;
            }
            i++;
        }

        return Arrays.copyOf(array, array.length - duplicates);
    }
}