package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for SimpleArraySet.
 *
 * @author Ayuzyak
 * @since 06.06.2017
 * @version 1.0
 */
public class SimpleArraySetTest {
    /**
     * test adding three same elements.
     * When invoke two times next() from iterator then should throws NoSuchElementException,
     * because Set contains only unique elements
     */
    @Test
    public void whenAddElementThenGetIt() {
        SimpleArraySet<Integer> set = new SimpleArraySet<>();
        set.add(100);
        set.add(100);
        set.add(100);
        Iterator<Integer> iterator = set.iterator();
        String resultException = null;
        iterator.next();
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            resultException = "Unique";
        }
        final String checkException = "Unique";
        assertThat(resultException, is(checkException));
    }
}