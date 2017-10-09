package ru.job4j.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class SQLTableCreator for inserting data to database.
 * @author Ayuzyak
 * @since 08.10.2017
 * @version 1.0
 */
public class SQLTableCreator {
    /**
     * Number of rows for inserting to database.
     */
    private int maxCount;

    /**
     * Database URL.
     */
    private String url;

    /**
     * URL getter.
     * @return URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Default URL setter.
     */
    public void setUrl() {
        File file = new File(".");
        String path = file.getAbsolutePath();
        path = path.substring(0, path.length() - 1)
                .replaceAll("\\\\", "/");
        this.url = "jdbc:sqlite:" + path + "/test.db";
    }

    /**
     * URL setter.
     * @param url of database.
     */
    public void setUrl(String url) {
        this.url = "jdbc:sqlite:" + url;
    }

    /**
     * MaxCount getter.
     * @return maxCount.
     */
    public int getMaxCount() {
        return maxCount;
    }

    /**
     * MaxCount setter.
     * @param maxCount for inserting.
     */
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * Get connection to database.
     * @return Connection.
     * @throws ClassNotFoundException if class not loading.
     * @throws SQLException when database has connection trouble.
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(url);
    }

    /**
     * Insert integer from 0 to maxCount to database.
     * @param con database connection.
     */
    public void insertRows(Connection con) {
        try {
            boolean autoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS TEST");
            statement.executeUpdate("CREATE TABLE TEST (FIELD INT)");
            String command = "INSERT INTO test (field) VALUES (%s)";
            for (int i = 1; i <= maxCount; i++) {
                statement.addBatch(String.format(command, i));
            }
            int[] counts = statement.executeBatch();
            if (counts.length != maxCount) {
                con.rollback();
            } else {
                con.commit();
            }
            con.setAutoCommit(autoCommit);
            System.out.println("Данные успешно добавлены в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}