package ru.job4j.test;

import java.util.Objects;

/**
 * Class User.
 *
 * @author Ayuzyak
 * @since 02.05.2017
 * @version 1.0
 */
public class User {
    /**
     * User name.
     */
    private String name;

    /**
     * User passport number.
     */
    private int passport;

    /**
     * User constructor.
     * @param name of user.
     * @param passport number of user.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Pasport number getter.
     * @return passport number.
     */
    public int getPassport() {
        return passport;
    }

    /**
     * Comparing user's.
     * @param o other user.
     * @return true if name and passport are same.
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
        return passport == user.passport
                && Objects.equals(name, user.name);
    }

    /**
     * Generates a hash code.
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }
}