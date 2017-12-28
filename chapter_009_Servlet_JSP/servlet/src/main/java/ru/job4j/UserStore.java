package ru.job4j;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * �����-�������� ����������� ������-������ ����������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 10.12.2017
 */
@ThreadSafe
public class UserStore {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ������ UserStore.
     */
    private static final UserStore STORE = new UserStore();

    /**
     * ���������� � ����� ������.
     */
    private Connection connection;

    /**
     * ��������� ����������� ������-���������.
     */
    private UserStore() {
    }

    /**
     * ��������� ������� ������-���������.
     *
     * @return STORE (��������).
     */
    public static UserStore getInstance() {
        return STORE;
    }

    /**
     * ����� ��� ���������� ������������.
     *
     * @param name  ��� ������������.
     * @param login ����� ��� �����.
     * @param email ����� ����������� �����.
     * @return true ���� �������� �� ���������� ������������ ������ �������.
     */
    public synchronized boolean addUser(String name, String login, String email) {
        int addResult = 0;
        try (PreparedStatement statement = connection
                .prepareStatement("INSERT INTO users (name, login, email) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, email);
            addResult = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return addResult != 0;
    }

    /**
     * ����� ��� �������������� ������ � ������������.
     *
     * @param login    ����� ������������.
     * @param newName  ��� ��� ������ �������������.
     * @param newLogin ����� ��� ������ �������������.
     * @param newEmail ����� ����������� ����� ��� ������ ������������.
     * @return ����� ������:
     * 1 ���� ������ ����������� � ���� ������ � ��� ������� ���������������,
     * 0 ���� ������ ����������� � ���� ������, �� ��������������� � �� �������,
     * -1 ���� ������ � ������������ �� ������� � ���� ������,
     * -2 ���� ��� ��������� (���� ��� ��������������) ������.
     */
    public synchronized int updateUser(String login, String newName, String newLogin, String newEmail) {
        int updateResult = 0;
        try (PreparedStatement fullStatement = connection
                .prepareStatement("UPDATE users SET name = ?, login = ?, email = ?"
                        + "WHERE login = ?");
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            connection.setAutoCommit(false);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (newName.isEmpty() && newLogin.isEmpty() && newEmail.isEmpty()) {
                    updateResult = -2;
                } else {
                    if (newName.isEmpty()) {
                        newName = resultSet.getString("name");
                    }
                    if (newLogin.isEmpty()) {
                        newLogin = login;
                    }
                    if (newEmail.isEmpty()) {
                        newEmail = resultSet.getString("email");
                    }
                    fullStatement.setString(1, newName);
                    fullStatement.setString(2, newLogin);
                    fullStatement.setString(3, newEmail);
                    fullStatement.setString(4, login);
                    updateResult = fullStatement.executeUpdate();
                    connection.commit();
                }
            } else {
                updateResult = -1;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                LOGGER.info("���� ��� ���������� ������������. ������� ���������� � ����� ��� ��������");
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.info("����� �� ������");
                LOGGER.error(e1.getMessage(), e1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return updateResult;
    }

    /**
     * ����� ��� �������� ������ � ������������ �� ���� ������.
     *
     * @param login ����� ��� �����.
     * @return ����� ������:
     * 1 (��� > 1, �� ��� ��� ���� login � ���� ������ ����������, �� ��������� ���� ������) ���� ������
     * ����������� � ���� ������ � ��� ������� �������(),
     * 0 ���� ������ ����������� � ���� ������, �� ������� � �� �������,
     * -1 ���� ������ � ������������ �� ������� � ���� ������.
     */
    public synchronized int deleteUser(String login) {
        int deleteResult = 0;
        try (PreparedStatement statement = connection
                .prepareStatement("DELETE FROM users WHERE login = ?")) {
            connection.setAutoCommit(false);
            if (validateUser(login)) {
                statement.setString(1, login);
                deleteResult = statement.executeUpdate();
                connection.commit();
            } else {
                deleteResult = -1;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                LOGGER.info("���� ��� �������� ������������. ������� ���������� � ����� ��� �������");
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.info("����� �� ������");
                LOGGER.error(e1.getMessage(), e1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return deleteResult;
    }

    /**
     * ����� ��� ��������� ������ � ������������.
     * ���� ������������ ��� � ��, �� ���������� User � ������������� ����� exist � false
     *
     * @param login ����� ������������.
     * @return ������ � ������������.
     */
    public synchronized User getUser(String login) {
        User user = new User();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(resultSet.getTimestamp("createdate").getTime());
                user.setCreateDate(date);
            } else {
                user.setExist(false);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * �������� ����������� ������ � ������������ � ���� ������.
     *
     * @param login ����� ��� ��������.
     * @return true ���� ������������ ���� � ����.
     */
    private synchronized boolean validateUser(String login) {
        boolean check = false;
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return check;
    }

    /**
     * ��������� ������ ������� ���� �������������.
     *
     * @return ������ ������� �������������.
     */
    public List<String> getAll() {
        List<String> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT login FROM users")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return userList;
    }

    /**
     * ���������� � ����� ������. ������������ ��� ����������.
     */
    public void connectDB() {
        try {
            Properties dataBaseProperties = new Properties();
            try (InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties")) {
                dataBaseProperties.load(in);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
            String driver = dataBaseProperties.getProperty("driver");
            String url = dataBaseProperties.getProperty("url");
            String user = dataBaseProperties.getProperty("user");
            String password = dataBaseProperties.getProperty("password");
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            dataSource.setMinIdle(5);
            dataSource.setMinIdle(100);
            dataSource.setMaxOpenPreparedStatements(100);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * �������� ����������.
     */
    public void disconnectDB() {
        try {
            connection.close();
            if (connection.isClosed()) {
                LOGGER.info("���������� � ����� ������ �������");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}