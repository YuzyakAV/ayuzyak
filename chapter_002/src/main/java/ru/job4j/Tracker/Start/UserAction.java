package ru.job4j.tracker.start;

/**
 * Interface UserAction.
 * @author yuzyakav
 * @since 06.03.2016
 * @version 1.0
 */
public interface UserAction {
    /**
     * key for actions.
     * @return int key.
     */
    int key();

    /**
     * method for execute action.
     * @param input for enter information.
     * @param tracker of tasks.
     */
    void execute(Input input, Tracker tracker);

    /**
     * method for print information.
     * @return String information.
     */
    String info();
}