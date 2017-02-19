package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import ru.job4j.tracker.models.Item;

import java.util.ArrayList;


/**
 * Class Tracker.
 * @author ayuzyak.
 * @version 1.
 * @since 07.02.2017
 */
public class TrackerTest {

    /**
     * Test method for checking add() and getAll().
     */
    @Test
    public void whenAddItemsToTracker() {
        Tracker tracker = new Tracker();
        final int checkItemsLength = 1;
        Item[] checkItems = new Item[checkItemsLength];
        final int pos_1 = 1;
        checkItems[pos_1] = tracker.add(new Item("Name_1", "Desc_1"));
        Item[] resultItems = tracker.getAll();
        assertThat(resultItems, is(checkItems));
    }

    /**
     * Test method for findById().
     */
    @Test
    public void whenFindId() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name_1", "Desc_1");
        tracker.add(item);
        String id = "N1";
        item.setId(id);
        Item resultItem = tracker.findById(id);
        assertThat(resultItem, is(item));
    }

    /**
     * Test method for update().
     */
    @Test
    public void whenUpdateTracker() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name_1", "Desc_1");
        tracker.add(item);
        Item[] items = tracker.getAll();
        String id = items[1].getId();
        Item checkItem = new Item("Item", "Description");
        checkItem.setId(id);
        tracker.update(checkItem);
        Item[] resultItems = tracker.getAll();
        assertThat(resultItems[1], is(checkItem));
    }

    /**
     * Test method for delete().
     */
    @Test
    public void whenDeleteItemFromTracker() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name_1", "Desc_1");
        tracker.add(item);
        Item[] items = tracker.getAll();
        String id = items[1].getId();
        Item deleteItem = new Item("Item", "Description");
        deleteItem.setId(id);
        tracker.delete(deleteItem);
        Item[] resultItems = tracker.getAll();
        Item checkItem = null;
        assertThat(resultItems[1], is(checkItem));
    }

    /**
     * Test method for findByName().
     */
    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name_1", "Desc_1");
        tracker.add(item);
        String name = "Name_1";
        Item resultItem = tracker.findByName(name);
        assertThat(resultItem, is(item));
    }

    /**
     * Test method for addComment().
     */
    @Test
    public void whenAddCommentToItem() {
        Tracker tracker = new Tracker();
        Item itemForComment = new Item("Name_1", "Desc_1");
        tracker.add(itemForComment);
        Item[] items = tracker.getAll();
        String id = items[1].getId();
        Item checkItem = new Item("Name_1", "Desc_1");
        checkItem.setId(id);
        ArrayList<String> checkComments = checkItem.getComments();
        checkComments.add("Test");
        checkComments.add("Comments");
        tracker.addComment(checkItem, "Test");
        tracker.addComment(checkItem, "Comments");
        Item[] resultItems = tracker.getAll();
        ArrayList<String> resultComments = resultItems[0].getComments();
        assertThat(resultComments, is(checkComments));
    }
}