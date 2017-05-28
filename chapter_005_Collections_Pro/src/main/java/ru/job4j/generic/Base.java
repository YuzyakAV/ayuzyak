package ru.job4j.generic;

/**
 * Base.
 *
 * @author Ayuzyak
 * @since 26.05.2017
 * @version 1.0
 */
public abstract class Base {

    /**
     * ID.
     */
    private String id;

    /**
     * Setter for ID.
     * @param id for setting.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for ID.
     * @return ID.
     */
    public String getId() {
        return id;
    }
}