package ru.job4j.tracker.start;

/**
 * Class BaseAction.
 * @author yuzyak.
 * @since 07.03.2017
 * @version 1.0
 */
public abstract class BaseAction implements UserAction {
    /**
     * name.
     */
    private String name;

    /**
     * constructor for BaseAction.
     * @param name of object's.
     */
    public BaseAction(String name) {
        this.name = name;
    }

    /**
     * key for actions.
     * @return int key.
     */
    @Override
    public abstract int key();

    /**
     * method for execute action.
     * @param input for enter information.
     * @param tracker of tasks.
     */
    @Override
    public abstract void execute(Input input, Tracker tracker);

    /**
     * method for print information.
     * @return String information.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), name);
    }
}