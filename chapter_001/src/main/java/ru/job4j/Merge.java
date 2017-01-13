package ru.job4j;

import java.util.Arrays;

/**
 * Merge array class.
 * @author ayuzyak.
 * @version 1.
 * @since 10.01.2017.
 */
public class Merge {

    /**
     * Merge two arrays into own.
     * @param array1 - first array
     * @param array2 - second array
     * @return merged array
     */
    public int[] merge(int[] array1, int[] array2) {
        int length1 = array1.length, length2 = array2.length;
        int one = 0, two = 0;
        int length = length1 + length2;

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            if (one < length1 && two < length2) {
                result[i] = ((array1[one] > array2[two]) ? array2[two++] : array1[one++]);
            } else if (one < length1) {
                result[i] = array1[one++];
            } else {
                result[i] = array2[two++];
            }
        }
        return result;
    }

    /**
     * Merge sort array.
     * @param array - entire array
     * @return sorted array
     */
    public int[] sort(int[] array) {
        int length = array.length;

        if (length < 2) {
            return array;
        }

        int half = length / 2;

        return merge(
                sort(Arrays.copyOfRange(array, 0, half)),
                sort(Arrays.copyOfRange(array, half, length))
        );
    }
}