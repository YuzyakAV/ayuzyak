package ru.job4j.tracker.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.models.Item;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import com.google.common.base.Joiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class StartUITest.
 *
 * @author ayuzyak.
 * @since 20.02.2017
 * @version 1
 */
public class StartUITest {

    /**
     * output for test.
     */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * method for setting stream.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * method for cleaning stream.
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    /**
     * Test for checking add task.
     */
    @Test
    public void whenAddNewTask() {
        String[] actionTask = new String[] {"0", "Task#1", "Long description", "8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.init();
        Tracker tracker = start.getTracker();
        Item[] items = tracker.getAll();
        String name = items[0].getName();
        String desc = items[0].getDescription();
        assertThat(name + desc, is("Task#1Long description"));
    }

    /**
     * Test for editing task.
     */
    @Test
    public void whenEditTask() {
        Tracker tracker = new Tracker();
        String taskName = "Task#1";
        String taskDescription = "Long description";
        Item taskForAdd = new Item(taskName, taskDescription);
        tracker.add(taskForAdd);
        String id = taskForAdd.getId();
        String[] actionTask = new String[] {"1", id, "Task#777", "Short description", "8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.setTracker(tracker);
        start.init();
        Item[] items = tracker.getAll();
        String nameForTest = items[0].getName();
        String descForTest = items[0].getDescription();
        assertThat(nameForTest + descForTest, is("Task#777Short description"));
    }

    /**
     * Test for deleting task.
     */
    @Test
    public void whenDeleteTask() {
        Tracker tracker = new Tracker();
        String taskName = "Task#1";
        String taskDescription = "Long description";
        Item taskForAdd = new Item(taskName, taskDescription);
        tracker.add(taskForAdd);
        String id = taskForAdd.getId();
        String[] actionTask = new String[] {"2", id, "8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.setTracker(tracker);
        start.init();
        Item[] items = tracker.getAll();
        Item checkItem = null;
        assertThat(items[0], is(checkItem));
    }

    /**
     * Test for showing all tasks.
     */
    @Test
    public void whenShowAllTasks() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Task#1", "Desc#1"));
        tracker.add(new Item("Task#2", "Desc#2"));
        tracker.add(new Item("Task#3", "Desc#3"));
        String[] actionTask = new String[] {"3", "8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.setTracker(tracker);
        start.init();
        Joiner joiner = Joiner.on(System.getProperty("line.separator"));
        String menu = joiner.join("Choose action from 0 to 8", "0. Add the new item.",
                "1. Edit the item.", "2. Delete the item.", "3. Show all items.",
                "4. Find task by name.", "5. Find task by ID.", "6. Add comment to task.",
                "7. Show all comments of task.", "8. Exit.");
        String listOfTasks = joiner.join("Task - Task#1 with ID - " + tracker.getAll()[0].getId(),
                "Task - Task#2 with ID - " + tracker.getAll()[1].getId(), "Task - Task#3 with ID - " + tracker.getAll()[2].getId());
        assertThat(output.toString(), is(joiner.join("Welcome to tracker!", menu, "The List of tasks: ", listOfTasks, menu, "Good bye", "")));
    }

    /**
     * Test for finding item by name.
     */
    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("Task#1", "Desc#1");
        Item secondItem = new Item("Task#2", "Desc#2");
        Item thirdItem = new Item("Task#3", "Desc#3");
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        String[] actionTask = new String[] {"4", "Task#2", "8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.setTracker(tracker);
        start.init();
        String id = secondItem.getId();
        Joiner joiner = Joiner.on(System.getProperty("line.separator"));
        String menu = joiner.join("Choose action from 0 to 8", "0. Add the new item.",
                "1. Edit the item.", "2. Delete the item.", "3. Show all items.",
                "4. Find task by name.", "5. Find task by ID.", "6. Add comment to task.",
                "7. Show all comments of task.", "8. Exit.");
        String yourTaskId = "Your task was found. Task ID is: " + id;
        assertThat(output.toString(), is(joiner.join("Welcome to tracker!", menu, yourTaskId, menu, "Good bye", "")));
    }

    /**
     * Test for finding item by ID.
     */
    @Test
    public void whenFindItemByID() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("Task#1", "Desc#1");
        Item secondItem = new Item("Task#2", "Desc#2");
        Item thirdItem = new Item("Task#3", "Desc#3");
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        String id = secondItem.getId();
        String[] actionTask = new String[] {"5", id, "8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.setTracker(tracker);
        start.init();
        Joiner joiner = Joiner.on(System.getProperty("line.separator"));
        String menu = joiner.join("Choose action from 0 to 8", "0. Add the new item.",
                "1. Edit the item.", "2. Delete the item.", "3. Show all items.",
                "4. Find task by name.", "5. Find task by ID.", "6. Add comment to task.",
                "7. Show all comments of task.", "8. Exit.");
        String yourTaskId = "Your task was found. Task name is: Task#2";
        assertThat(output.toString(), is(joiner.join("Welcome to tracker!", menu, yourTaskId, menu, "Good bye", "")));
    }

    /**
     * Test for adding comment to task.
     */
    @Test
    public void whenAddCommentToTask() {
        Tracker tracker = new Tracker();
        String taskName = "Task#1";
        String taskDescription = "Long description";
        Item taskForAdd = new Item(taskName, taskDescription);
        tracker.add(taskForAdd);
        String id = taskForAdd.getId();
        String[] actionTask = new String[] {"6", id, "Add comment", "8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.setTracker(tracker);
        start.init();
        Item[] items = tracker.getAll();
        ArrayList<String> comments = items[0].getComments();
        String comment = comments.get(0);
        assertThat(comment, is("Add comment"));
    }

    /**
     * Test for showing all comments of task.
     */
    @Test
    public void whenShowAllCommentsOfTask() {
        Tracker tracker = new Tracker();
        String taskName = "Task#1";
        String taskDescription = "Long description";
        Item taskForAdd = new Item(taskName, taskDescription);
        ArrayList<String> comments = taskForAdd.getComments();
        comments.add("Long Text");
        comments.add("Short Text");
        tracker.add(taskForAdd);
        String id = taskForAdd.getId();
        String[] actionTask = new String[] {"7", id, "8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.setTracker(tracker);
        start.init();
        Joiner joiner = Joiner.on(System.getProperty("line.separator"));
        String menu = joiner.join("Choose action from 0 to 8", "0. Add the new item.",
                "1. Edit the item.", "2. Delete the item.", "3. Show all items.",
                "4. Find task by name.", "5. Find task by ID.", "6. Add comment to task.",
                "7. Show all comments of task.", "8. Exit.");
        String comment = joiner.join("Comment #1 - " + "Long Text", "Comment #2 - " + "Short Text");
        assertThat(output.toString(), is(joiner.join("Welcome to tracker!", menu, comment, menu, "Good bye", "")));
    }

    /**
     * Test for checking exit.
     */
    @Test
    public void whenUseExitFromProgramm() {
        String[] actionTask = new String[] {"8"};
        Input input = new StubInput(actionTask);
        StartUI start = new StartUI(input);
        start.init();
        Joiner joiner = Joiner.on(System.getProperty("line.separator"));
        String menu = joiner.join("Choose action from 0 to 8", "0. Add the new item.",
                "1. Edit the item.", "2. Delete the item.", "3. Show all items.",
                "4. Find task by name.", "5. Find task by ID.", "6. Add comment to task.",
                "7. Show all comments of task.", "8. Exit.");
        assertThat(output.toString(), is(joiner.join("Welcome to tracker!", menu, "Good bye", "")));
    }
}