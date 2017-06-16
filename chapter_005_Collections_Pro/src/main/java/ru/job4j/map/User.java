package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class User.
 *
 * @author Ayuzyak
 * @since 12.06.2017
 * @version 1.0
 */
public class User {
    /**
     * User name.
     */
    private String name;

    /**
     * Number of children.
     */
    private int children;

    /**
     * Date of birthday.
     */
    private Calendar birthday;

    /**
     * User constructor.
     * @param name - User name.
     * @param children - number of children.
     * @param birthday - date of birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Overriding method equals().
     * @param o - object for compare.
     * @return true if objects are same.
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
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

}