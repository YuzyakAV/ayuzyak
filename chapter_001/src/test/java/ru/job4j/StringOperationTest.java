package com.antviktorov;

import org.junit.Test;
import ru.job4j.StringOperations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * StringOperations test.
 * @author ayuzyak.
 * @version 1.
 * @since 10.01.2017.
 */
public class StringOperationsTest {

    /**
     * Check if origin string contains substring.
     */
    @Test
    public void whenStringContainsSubstring() {
        StringOperations operations = new StringOperations();
        assertThat(operations.contains("Vladimir Putin","Vladimir"), is(true));
    }

    /**
     * Check if origin string not contains substring.
     */
    @Test
    public void whenStringNotContainsSubstring() {
        StringOperations operations = new StringOperations();
        assertThat(operations.contains("Dmitry Medvedev","Vladimir"), is(false));
    }
}