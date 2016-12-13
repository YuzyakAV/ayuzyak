package ru.job4j;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Triangle Test class.
 * @author ayuzyak.
 * @version 1.
 * @since 12.12.2016.
*/
public class TriangleTest {

    /**
     * Area should be 4.5
     */
    @Test
    public void whenProvidedPointsThenArea() {
        Triangle triangle = new Triangle(new Point(1,1), new Point(2.5, 4), new Point(4,1));
        assertThat(triangle.area(), closeTo(4.0, 0.5));
    }
}