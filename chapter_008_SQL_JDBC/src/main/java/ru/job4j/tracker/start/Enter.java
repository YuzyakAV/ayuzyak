package ru.job4j.tracker.start;

/**
 * Class Enter.
 * @author Ayuzyak
 * @since 20.10.2017
 * @version 1.0
 */
public class Enter {

    /**
     * input for data.
     */
    private Input input;

    /**
     * Constructor with input.
     * @param input - tracker for edit.
     */
    public Enter(Input input) {
        this.input = input;
    }

    /**
     * execute enter.
     * @param question - asking information.
     * @return String.
     */
    public String execute(String question) {
        return input.ask(question);
    }
}