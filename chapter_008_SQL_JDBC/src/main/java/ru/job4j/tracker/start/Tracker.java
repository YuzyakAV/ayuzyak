package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class Tracker.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 22.10.2017
 */
public class Tracker {
    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * Getter to connection.
     *
     * @return connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Setter for connection.
     *
     * @param connection to database.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * add item to items[].
     *
     * @param item - item for add.
     * @return item.
     */
    public Item add(Item item) {
        String taskName = item.getName();
        String desc = item.getDescription();
        String id = "";
        try {
            try (PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO tasks (name, description) VALUES (?, ?)")) {
                statement.setString(1, taskName);
                statement.setString(2, desc);
                statement.executeUpdate();
                try (Statement st = connection.createStatement()) {
                    ResultSet rs = st.executeQuery("SELECT MAX(task_id) FROM tasks");
                    while (rs.next()) {
                        id = rs.getString(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        item.setId(id);
        return item;
    }

    /**
     * search item by id.
     *
     * @param taskID - id number for search.
     * @return result - item with required id.
     */
    protected Item findById(String taskID) {
        Item result = null;
        try {
            int id = Integer.parseInt(taskID);
            try (PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT name, description FROM tasks WHERE task_id = ?")) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        String name = rs.getString("name");
                        String desc = rs.getString("description");
                        result = new Task(name, desc);
                    }
                }

            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * getting all items.
     *
     * @return result - items array.
     */
    public ArrayList<Item> getAll() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(
                        "SELECT task_id, name, description FROM tasks ORDER BY task_id");
                while (rs.next()) {
                    int id = rs.getInt("task_id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    Item task = new Task(name, description);
                    task.setId(String.valueOf(id));
                    items.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * update item.
     *
     * @param item - item for updating.
     */
    public void update(Item item) {
        try {
            String name = item.getName();
            String desc = item.getDescription();
            int id = Integer.parseInt(item.getId());
            try (PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE tasks SET name = ?, description = ? WHERE task_id = ?")) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, desc);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Input Integer number for task ID");
        }
    }

    /**
     * delete item.
     *
     * @param item - item for deleting.
     */
    public void delete(Item item) {
        String taskID = item.getId();
        try {
            int id = Integer.parseInt(taskID);
            try (PreparedStatement preparedStatement = connection.
                    prepareStatement("DELETE FROM comments WHERE task_id = ?");
                 PreparedStatement preparedStatementTasks = connection.
                         prepareStatement("DELETE FROM tasks WHERE task_id = ?")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                preparedStatementTasks.setInt(1, id);
                preparedStatementTasks.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * search item by name.
     *
     * @param key - item's name for search item.
     * @return result - item with required name.
     */
    public Item findByName(String key) {
        Item result = null;
        try (PreparedStatement preparedStatement = connection.
                prepareStatement("SELECT task_id, description FROM tasks WHERE name = ?")) {

            preparedStatement.setString(1, key);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.isBeforeFirst()) {
                if (rs.next()) {
                    int id = rs.getInt("task_id");
                    String desc = rs.getString("description");
                    result = new Task(key, desc);
                    result.setId(String.valueOf(id));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * add comment to item.
     *
     * @param item    - item for commenting.
     * @param comment - adding comment.
     */
    public void addComment(Item item, String comment) {
        String taskID = item.getId();
        try {
            int id = Integer.parseInt(taskID);
            try (PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT  INTO comments (comment, task_id) VALUES (?, ?)")) {
                preparedStatement.setString(1, comment);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * show comments of item.
     *
     * @param item - item for showing comments.
     * @return list of comments.
     */
    public ArrayList<String> showComments(Item item) {
        ArrayList<String> comments = new ArrayList<>();
        String taskID = item.getId();
        try (PreparedStatement preparedStatement = connection.
                prepareStatement("SELECT comment FROM comments WHERE task_id = ? ORDER BY time_of_creating")) {
            int id = Integer.parseInt(taskID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(resultSet.getString("comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    /**
     * checking tracker for containing item Id.
     *
     * @param item - item for check.
     * @return boolean - true if tracker contain item.
     */
    public boolean hasId(Item item) {
        boolean trackerHasId = false;
        try (PreparedStatement preparedStatement = connection.
                prepareStatement("SELECT task_id FROM tasks WHERE task_id = ?")) {
            Integer id = Integer.parseInt(item.getId());

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                trackerHasId = true;
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return trackerHasId;
    }
}