package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test for BaseStore.
 *
 * @author Ayuzyak
 * @since 28.05.2017
 * @version 1.0
 */
public class BaseStoreTest {
    /**
     * BaseStore.
     */
    private BaseStore<User> store;
    /**
     * User #1.
     */
    private User firstUser = new User();

    /**
     * User #2.
     */
    private User secondUser = new User();

    /**
     * User #3.
     */
    private User thirdUser = new User();

    /**
     * Method for initialize store.
     */
    @Before
    public void prepareBaseStore() {
        store = new UserStore();
        firstUser = new User();
        secondUser = new User();
        thirdUser = new User();
        firstUser.setId("001");
        secondUser.setId("002");
        thirdUser.setId("007");
        store.add(firstUser);
        store.add(secondUser);
        store.add(thirdUser);
    }

    /**
     * test method add().
     * Adding element and get it.
     */
    @Test
    public void whenAddElementThenGetItForCheck() {
        User fourthUser = new User();
        fourthUser.setId("010");
        store.add(fourthUser);
        SimpleArray<User> resultList = store.getSimpleArray();
        User resultUser = resultList.get(3);
        User checkUser = fourthUser;
        assertThat(resultUser, is(checkUser));
    }

    /**
     * test method update().
     * Update element and get it.
     */
    @Test
    public void whenUpdateElementThenGetItForCheck() {
        User fourthUser = new User();
        fourthUser.setId("100");
        store.update("002", fourthUser);
        SimpleArray<User> resultList = store.getSimpleArray();
        User resultUser = resultList.get(1);
        User checkUser = fourthUser;
        assertThat(resultUser, is(checkUser));
    }

    /**
     * test method delete().
     * Delete element and get size of BaseStore.
     */
    @Test
    public void whenDeleteElementThenGetSizeForCheck() {
        store.delete("002");
        SimpleArray<User> resultList = store.getSimpleArray();
        final int resultSize = resultList.getSize();
        final int checkSize = 2;
        assertThat(resultSize, is(checkSize));
    }
}