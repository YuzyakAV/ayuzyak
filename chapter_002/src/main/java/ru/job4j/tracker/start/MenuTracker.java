package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.models.Task;

import java.util.ArrayList;

/**
 * Class MenuTracker.
 * @author Ayuzyak
 * @since 26.03.2017
 * @version 1.0
 */
public class MenuTracker {
    /**
     * variable of input.
     */
    private Input input;

    /**
     * task tracker.
     */
    private Tracker tracker;

    /**
     * menu of actions.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();

    /**
     * constructor for MenuTracker.
     * @param input for enter information.
     * @param tracker of tasks.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * fill menu of actions.
     */
    public void fillActions() {
        this.actions.add(this.new AddItem("Add the new item."));
        this.actions.add(new MenuTracker.EditItem("Edit the item."));
        this.actions.add(new DeleteItem("Delete the item."));
        this.actions.add(new ShowItems("Show all items."));
        this.actions.add(new FindItemByName("Find task by name."));
        this.actions.add(new FindItemById("Find task by ID."));
        this.actions.add(new AddComment("Add comment to task."));
        this.actions.add(new ShowComments("Show all comments of task."));
    }

    /**
     * method for getting array of actions.
     * @param action to add.
     */
    public void addAction(UserAction action) {
        this.actions.add(action);
    }

    /**
     * method for getting array of actions.
     * @return actions - int[].
     */
    public ArrayList<Integer> getRangeActions() {
        ArrayList<Integer> rangeActions = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            rangeActions.add(actions.get(i).key());
        }
        return rangeActions;
    }

    /**
     * method for execute action.
     * @param key for select action.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * method for show user actions.
     */
    public void show() {
        System.out.println("Choose action from 0 to 8");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Inner class to add task.
     */
    private class AddItem extends BaseAction {

        /**
         * constructor for AddItem.
         * @param name of object's.
         */
        AddItem(String name) {
            super(name);
        }
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 0;
        }

        /**
         * method for execute adding.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter the task's name: ");
            String desc = input.ask("Please enter the task's desc: ");
            Item item = new Task(name, desc);
            tracker.add(item);
            countAddedTask++;
            if (countAddedTask <= tracker.getAll().size()) {
                System.out.println("You have successfully added the task. Your task id is: " + item.getId());
            } else {
                System.out.println("Sorry you can't add task. List of task is full.");
            }
        }
    }

    /**
     * Inner static class to edit task.
     */
    private static class EditItem extends BaseAction {

        /**
         * constructor for EditItem.
         * @param name of object's.
         */
        EditItem(String name) {
            super(name);
        }
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 1;
        }

        /**
         * method for execute editing.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter ID of edit task - ");
            Item item = new Task();
            item.setId(id);
            if (tracker.hasId(item)) {
                String name = input.ask("Change name of task - ");
                String desc = input.ask("Change description of task - ");
                item = new Task(name, desc);
                item.setId(id);
                tracker.update(item);
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }
    }

    /**
     * Inner class to show all tasks.
     */
    private class ShowItems extends BaseAction {

        /**
         * constructor for ShowItems.
         * @param name of object's.
         */
        ShowItems(String name) {
            super(name);
        }
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 3;
        }

        /**
         * method for execute showing.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("The List of tasks: ");
            ArrayList<Item> items = tracker.getAll();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null) {
                    System.out.println("Task - " + items.get(i).getName() + " with ID - " + items.get(i).getId());
                }
            }
        }
    }

    /**
     * Inner class to find tasks by name.
     */
    private class FindItemByName extends BaseAction {
        /**
         * constructor for FindItemByName.
         * @param name of object's.
         */
        FindItemByName(String name) {
            super(name);
        }

        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 4;
        }

        /**
         * method for execute finding by name.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter name of task to search - ");
            Item item = tracker.findByName(name);
            if (item != null) {
                System.out.println("Your task was found. Task ID is: " + item.getId());
            } else {
                System.out.println("!!!There is not task with this name!!!");
            }
        }
    }

    /**
     * Inner class to find tasks by ID.
     */
    private class FindItemById extends BaseAction {
        /**
         * constructor for FindItemById.
         * @param name of object's.
         */
        FindItemById(String name) {
            super(name);
        }

        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 5;
        }

        /**
         * method for execute finding by ID.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter ID of task to search - ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Your task was found. Task name is: " + item.getName());
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }
    }

    /**
     * Inner class to add comment to task.
     */
    private class AddComment extends BaseAction {
        /**
         * constructor for AddComment.
         * @param name of object's.
         */
        AddComment(String name) {
            super(name);
        }

        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 6;
        }

        /**
         * method for execute adding comment.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id of task to comment - ");
            Item item = new Task();
            item.setId(id);
            if (tracker.hasId(item)) {
                String comment = input.ask("Enter your comment - ");
                tracker.addComment(item, comment);
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }
    }

    /**
     * Inner class to show comments of task.
     */
    private class ShowComments extends BaseAction {
        /**
         * constructor for ShowComments.
         * @param name of object's.
         */
        ShowComments(String name) {
            super(name);
        }

        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 7;
        }

        /**
         * method for execute showing comments.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id of task to show comments - ");
            Item item = new Task();
            item.setId(id);
            if (tracker.hasId(item)) {
                ArrayList<String> comments = tracker.showComments(item);
                if (comments.size() > 0) {
                    for (int i = 1; i <= comments.size(); i++) {
                        System.out.println("Comment #" + i + " - " + comments.get(i - 1));
                    }
                } else {
                    System.out.println(("This task is not got comments yet"));
                }
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }
    }
}

/**
 * Outer class to delete task.
 */
class DeleteItem extends BaseAction {
    /**
     * constructor for DeleteItem.
     * @param name of object's.
     */
    DeleteItem(String name) {
        super(name);
    }

    /**
     * key for choose.
     * @return int key.
     */
    public int key() {
        return 2;
    }

    /**
     * method for execute deleting task.
     * @param input for enter information.
     * @param tracker for tasks.
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter id task to delete - ");
        Item item = new Task();
        item.setId(id);
        if (tracker.hasId(item)) {
            tracker.delete(item);
            System.out.println("Item deleted.");
        } else {
            System.out.println("!!!There is not task with this ID!!!");
        }
    }
}
