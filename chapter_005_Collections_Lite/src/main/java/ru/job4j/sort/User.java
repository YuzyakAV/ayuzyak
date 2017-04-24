package ru.job4j.sort;

/**
 * Class User.
 *
 * @author Ayuzyak
 * @since 22.04.2017
 * @version 1.0
 */
public class User implements Comparable<User> {
    /**
     * User name.
     */
    private String name;

    /**
     * User age.
     */
    private int age;

    /**
     * User constructor.
     * @param name of user.
     * @param age of user.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Comparing users by age.
     * @param o - other user.
     * @return - 1 if this user older than other, 0 if age is same and -1 if this user younger than other.
     */
    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }

    /**
     * Age getter.
     * @return age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return name;
    }
}