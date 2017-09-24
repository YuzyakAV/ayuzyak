package ru.job4j.nonblockingcache;

/**
 * Model.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 23.09.2017
 */
public class Model {
    /**
     * Static ID for all models.
     */
    private static int commonID = 0;

    /**
     * ID for model.
     */
    private int id;

    /**
     * Model name.
     */
    private String name;

    /**
     * Model version.
     */
    private volatile int version = 0;

    /**
     * Constructor.
     * @param name for model.
     */
    public Model(String name) {
        this.name = name;
        this.id = ++commonID;
    }

    /**
     * Setter for name.
     * @param name model name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Increment version if model was edited.
     * @return #version.
     */
    public int changeVersion() {
        return ++version;
    }

    /**
     * Getter for version.
     * @return #version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * ToString method.
     * @return String description.
     */
    @Override
    public String toString() {
        return "Model{"
                + "name='" + name + '\''
                + ", version=" + version + '}';
    }

    /**
     * Equals.
     * @param o other object.
     * @return true if models are same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Model model = (Model) o;

        return id == model.id;
    }

    /**
     * HashCode.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return id;
    }
}