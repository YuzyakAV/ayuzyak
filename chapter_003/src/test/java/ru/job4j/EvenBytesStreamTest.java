package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test EvenBytesStream.
 *
 * @author Ayuzyak
 * @since 21.03.2017
 * @version 1.0
 */
public class EvenBytesStreamTest {
    /**
     * Test stream with even bytes.
     */
    @Test
    public void whenBytesStreamIsEven() {
        final String number = "1000";
        byte[] byteArray = number.getBytes();
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray)) {
            EvenBytesStream evenBytesStream = new EvenBytesStream();
            final boolean resultEven = evenBytesStream.isNumber(byteArrayInputStream);
            final boolean checkEven = true;
            assertThat(resultEven, is(checkEven));
        } catch (IOException e) {
            System.out.println("Stream error");
        }
    }
    /**
     * Test stream with odd bytes.
     */
    @Test
    public void whenBytesStreamIsOdd() {
        final String number = "1001";
        byte[] byteArray = number.getBytes();
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray)) {
            EvenBytesStream evenBytesStream = new EvenBytesStream();
            final boolean resultOdd = evenBytesStream.isNumber(byteArrayInputStream);
            final boolean checkOdd = false;
            assertThat(resultOdd, is(checkOdd));
        } catch (IOException e) {
            System.out.println("Stream error");
        }
    }
}