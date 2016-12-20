package ru.job4j;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Paint Test class.
 * @author ayuzyak.
 * @version 1.
 * @since 20.12.2016.
 */
public class PaintTest {

    /**
     * Build pyramid with height = 0
     */
    @Test
    public void whenBuidPyramidHeightZeroThenPyramid() {
        Paint paint = new Paint();
        assertEquals(paint.piramid(0), "");
    }

    /**
     * Build pyramid with height = 1
     */
    @Test
    public void whenBuidPyramidHeightOneThenPyramid() {
        Paint paint = new Paint();
        assertEquals(paint.piramid(1), "^");
    }

    /**
     * Build pyramid with height = 2
     */
    @Test
    public void whenBuidPyramidHeightTwoThenPyramid() {
        Paint paint = new Paint();
        assertEquals(
                paint.piramid(2),
                " ^" + System.getProperty("line.separator") + "^^" + System.getProperty("line.separator")
        );
    }

    /**
     * Build pyramid with height = 3
     */
    @Test
    public void whenBuidPyramidHeightThreeThenPyramid() {
        Paint paint = new Paint();
        assertEquals(
                paint.piramid(3),
                "  ^" + System.getProperty("line.separator") + " ^^" +
                        System.getProperty("line.separator") + "^^^" +
                        System.getProperty("line.separator")
        );
    }
}