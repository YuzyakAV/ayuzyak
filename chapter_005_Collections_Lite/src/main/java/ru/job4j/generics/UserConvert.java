package ru.job4j.generics;

import java.util.HashMap;
import java.util.List;

/**
 * Class for convert List of users to HashMap.
 *
 * @author Ayuzyak
 * @since 18.04.2017
 * @version 1.0
 */
public class UserConvert {
    /**
     * Convert List of users to HashMap.
     * @param list of Users.
     * @return HashMap with key - user ID and value - user.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}