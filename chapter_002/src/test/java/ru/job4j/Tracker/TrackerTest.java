package ru.job4j.tracker;

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
        final int checkItemsLength = 10;
        Item[] checkItems = new Item[checkItemsLength];
        final int pos_0 = 0;
        final int pos_1 = 1;
        final int pos_2 = 2;
        final int pos_3 = 3;
        final int pos_4 = 4;
        final int pos_5 = 5;
        final int pos_6 = 6;
        final int pos_7 = 7;
        final int pos_8 = 8;
        final int pos_9 = 9;
        checkItems[pos_0] = tracker.add(new Item("Name_1", "Desc_1"));
        checkItems[pos_1] = tracker.add(new Item("Name_2", "Desc_2"));
        checkItems[pos_2] = tracker.add(new Item("Name_3", "Desc_3"));
        checkItems[pos_3] = tracker.add(new Item("Name_4", "Desc_4"));
        checkItems[pos_4] = tracker.add(new Item("Name_5", "Desc_5"));
        checkItems[pos_5] = tracker.add(new Item("Name_6", "Desc_6"));
        checkItems[pos_6] = tracker.add(new Item("Name_7", "Desc_7"));
        checkItems[pos_7] = tracker.add(new Item("Name_8", "Desc_8"));
        checkItems[pos_8] = tracker.add(new Item("Name_9", "Desc_9"));
        checkItems[pos_9] = tracker.add(new Item("Name_10", "Desc_10"));
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
        String id = "N23";
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
        String id = items[0].getId();
        Item checkItem = new Item("Item", "Description");
        checkItem.setId(id);
        tracker.update(checkItem);
        Item[] resultItems = tracker.getAll();
        assertThat(resultItems[0], is(checkItem));
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
        String id = items[0].getId();
        Item deleteItem = new Item("Item", "Description");
        deleteItem.setId(id);
        tracker.delete(deleteItem);
        Item[] resultItems = tracker.getAll();
        Item checkItem = null;
        assertThat(resultItems[0], is(checkItem));
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
        String id = items[0].getId();
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