package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test DropAbuse.
 * @author Ayuzyak
 * @since 20.03.2017
 * @version 1.0
 */
public class DropAbuseTest {
    /**
     * Test stream with abuse words: Ball, Letter, Word, дорога.
     */
    @Test
    public void whenStreamHasAbuseWords() {
        Joiner joiner = Joiner.on(System.getProperty("line.separator"));
        final String sourceText = joiner.join("Fly, ball little letter bird, fly!",
                "Fly into twordhe blue sky!", "One, twwordo, three, дорога", "You are ball free!");
        byte[] bytesIn = sourceText.getBytes();
        byte[] bytesOut;
        String[] abuse = new String[]{"ball", "letter", "word", "дорога"};
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesIn);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            DropAbuse dropAbuse = new DropAbuse();
            dropAbuse.dropAbuses(byteArrayInputStream, byteArrayOutputStream, abuse);

            final String resultText = String.valueOf(byteArrayOutputStream);
            final String checkText = joiner.join("Fly,  little  bird, fly!",
                    "Fly into the blue sky!", "One, two, three, ", "You are  free!");
            assertThat(resultText, is(checkText));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}