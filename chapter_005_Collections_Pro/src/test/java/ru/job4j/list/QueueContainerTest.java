package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for QueueContainer.
 *
 * @author Ayuzyak
 * @since 02.06.2017
 * @version 1.0
 */
public class QueueContainerTest {
    /**
     * test method poll().
     * Getting element from queue.
     */
    @Test
    public void whenAddElementAndPollThenGetIt() {
        QueueContainer<Integer> container = new QueueContainer<>();
        container.add(150);
        container.add(200);
        container.add(500);
        final Integer resultElement = container.poll();
        final Integer checkElement = 150;
        assertThat(resultElement, is(checkElement));
    }
}