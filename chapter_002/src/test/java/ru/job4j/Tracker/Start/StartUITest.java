package ru.job4j.tracker.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.models.Item;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import com.google.common.base.Joiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class StartUITest.
 *
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017.
 */
public class StartUITest {

    /**
     * output for test.
     */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * method for setting stream.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * method for cleaning stream.
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }