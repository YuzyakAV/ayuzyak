package ru.job4j.sort;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for SortUser.
 *
 * @author Ayuzyak
 * @since 22.04.2017
 * @version 1.0
 */
public class SortUserTest {
    /**
     * test convert List of users to TreeSet and sort users by age.
     */
    @Test
    public void whenListOfUsersConvertToTreeSet() {
        final User firstUser = new User("John", 50);
        final User secondUser = new User("Sergei", 20);
        final User thirdUser = new User("Elvis", 30);
        final User fourthUser = new User("Anna", 15);
        List<User> srcList = new ArrayList<>();
        srcList.add(firstUser);
        srcList.add(secondUser);
        srcList.add(thirdUser);
        srcList.add(fourthUser);
        SortUser sortUser = new SortUser();
        Set<User> resultSet = sortUser.sort(srcList);
        LinkedHashSet<User> checkSet = new LinkedHashSet<>();
        checkSet.add(fourthUser);
        checkSet.add(secondUser);
        checkSet.add(thirdUser);
        checkSet.add(firstUser);
        assertThat(resultSet, is(checkSet));
    }

    /**
     * test sort List by hashCode.
     */
    @Test
    public void whenListOfUsersSortByUsersHashCode() {
        final User firstUser = new User("John", 50);
        final User secondUser = new User("Sergei", 20);
        final User thirdUser = new User("Elvis", 30);
        final User fourthUser = new User("Anna", 15);
        List<User> srcList = new ArrayList<>();
        srcList.add(firstUser);
        srcList.add(secondUser);
        srcList.add(thirdUser);
        srcList.add(fourthUser);
        List<User> checkList = new ArrayList<>(srcList);
        SortUser sortUser = new SortUser();
        List<User> resultList = sortUser.sortHash(srcList);
        for (int i = 0; i < checkList.size() - 1; i++) {
            for (int j = i + 1; j < checkList.size(); j++) {
                if (checkList.get(i).hashCode() > checkList.get(j).hashCode()) {
                    User temp = checkList.get(i);
                    checkList.set(i, checkList.get(j));
                    checkList.set(j, temp);
                }
            }
        }
        assertThat(resultList, is(checkList));
    }

    /**
     * test sort List by name length.
     */
    @Test
    public void whenListOfUsersSortByNameLength() {
        final User firstUser = new User("John", 50);
        final User secondUser = new User("Sergei", 20);
        final User thirdUser = new User("Elvis", 30);
        final User fourthUser = new User("Anna", 15);
        List<User> srcList = new ArrayList<>();
        srcList.add(firstUser);
        srcList.add(secondUser);
        srcList.add(thirdUser);
        srcList.add(fourthUser);
        List<User> checkList = new ArrayList<>();
        SortUser sortUser = new SortUser();
        List<User> resultList = sortUser.sortLength(srcList);
        checkList.add(firstUser);
        checkList.add(fourthUser);
        checkList.add(thirdUser);
        checkList.add(secondUser);
        assertThat(resultList, is(checkList));
    }
}