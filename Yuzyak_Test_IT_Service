package ru.job4j.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * User.
 */
public class User implements Serializable {
    /**
     * User name.
     */
    private String name;

    /**
     * User surname.
     */
    private String surname;

    /**
     * Constructor.
     *
     * @param name    .
     * @param surname .
     */
    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    /**
     * Getter for name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     *
     * @param name .
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for surname.
     *
     * @return surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for name.
     *
     * @param surname .
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Equals.
     *
     * @param o .
     * @return true if name and surname are same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname);
    }

    /**
     * HashCode.
     *
     * @return hashCode by name and surname.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
