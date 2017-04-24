package ru.job4j.sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for User.
 *
 * @author Ayuzyak
 * @since 22.04.2017
 * @version 1.0
 */
public class UserTest {
    /**
     * test compareTo if one user older than second.
     */
    @Test
    public void whenFirstUserOlderThanSecond() {
        User firstUser = new User("John", 80);
        User secondUser = new User("Ivan", 10);
        int resultAge = firstUser.compareTo(secondUser);
        int checkAge = 1;
        assertThat(resultAge, is(checkAge));
    }

    /**
     * test compareTo if one user and second are same age.
     */
    @Test
    public void whenOneUserAndSecondAreSameAge() {
        User firstUser = new User("Mary", 50);
        User secondUser = new User("Ivan", 50);
        int resultAge = firstUser.compareTo(secondUser);
        int checkAge = 0;
        assertThat(resultAge, is(checkAge));
    }

    /**
     * test compareTo if one user younger than second.
     */
    @Test
    public void whenOneUserYoungerThanSecond() {
        User firstUser = new User("John", 10);
        User secondUser = new User("Ivan", 40);
        int resultAge = firstUser.compareTo(secondUser);
        int checkAge = -1;
        assertThat(resultAge, is(checkAge));
    }
}