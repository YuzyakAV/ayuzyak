package ru.job4j;

/**
 * StringOperations class
 * @author ayuzyak.
 * @version 1.
 * @since 10.01.2017.
 */
public class StringOperations {

    /**
     * Check if stirng contains substring.
     * @param origin - entire string
     * @param sub - substring
     * @return boolean result
     */
    boolean contains(String origin, String sub) {

        boolean result = false;

        char[] originArray = origin.toCharArray();
        char[] subArray = sub.toCharArray();

        int originLength = originArray.length;
        int subLength = subArray.length;

        int subIndex = 0;

        for (int i = 0; i < originLength; i++) {
            if (subArray[subIndex] == originArray[i]) {
                if (subIndex == subLength - 1) {
                    result = true;
                    break;
                }
                subIndex++;
            } else {
                subIndex = 0;
            }
        }

        return result;
    }
}