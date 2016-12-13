package ru.job4j;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Point Test class.
  * @author ayuzyak.
  * @version 1.
  * @since 12.12.2016.
*/
public class PointTest {

    /**
     *  Distance test for points A(1,1), B(2,1).
     */
    @Test
    public void whenOneOneAndFourOneThenThree() {
        Point a = new Point(1,1);
        assertThat(a.distanceTo(new Point(4,1)), closeTo(3.0, 0.5));
    }

    /**
     *  Distance test for points A(10,5), B(15,11).
     */
    @Test
    public void whenTenFiveAndFifteenElevenSevenThenEight() {
        Point a = new Point(10,5);
        assertThat(a.distanceTo(new Point(15,11)), closeTo(8.0, 0.5));
    }
}