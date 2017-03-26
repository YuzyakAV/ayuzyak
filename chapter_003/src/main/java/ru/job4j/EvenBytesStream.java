package ru.job4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class EvenBytesStream.
 * @author Ayuzyak
 * @since 20.03.2017
 * @version 1.0
 */
public class EvenBytesStream {
    /**
     * Check stream's bytes are even or odd.
     * @param in - InputStream.
     * @return true if stream's bytes are even.
     */
    public boolean isNumber(InputStream in) {
        boolean isEvenByteStream = true;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            int number = 0;
            try {
                number = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("No number in stream or stream has more than one number");
            }
            if (number == 0 || number % 2 != 0) {
                isEvenByteStream = false;
            }
        } catch (IOException e) {
            System.out.println("Stream error");
        }
        return isEvenByteStream;
    }
}