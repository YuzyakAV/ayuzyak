package ru.job4j.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for UserConvert.
 *
 * @author Ayuzyak
 * @since 18.04.2017
 * @version 1.0
 */
public class UserConvertTest {
    /**
     * Test convert list of users to HashMap with key - user ID and value - user.
     */
    @Test
    public void whenConvertListOfUsersToHashMap() {
        User firstUser = new User("John", "New York");
        User secondUser = new User("Ivan", "Moscow");
        User thirdUser = new User("Mary", "London");
        List<User> srcList = new ArrayList<>();
        srcList.add(firstUser);
        srcList.add(secondUser);
        srcList.add(thirdUser);
        UserConvert userConvert = new UserConvert();
        Map<Integer, User> resultMap = userConvert.process(srcList);
        Map<Integer, User> checkMap = new HashMap<>();
        checkMap.put(firstUser.getId(), firstUser);
        checkMap.put(secondUser.getId(), secondUser);
        checkMap.put(thirdUser.getId(), thirdUser);
        assertThat(resultMap, is(checkMap));
    }
}