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
 * Класс-синглтон реализующий бизнес-логику приложения.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 05.12.2017
 */
@ThreadSafe
public class UserStore {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Объект UserStore.
     */
    private static final UserStore STORE = new UserStore();

    /**
     * Соединение с базой данных.
     */
    private Connection connection;

    /**
     * Приватный конструктор класса-синглтона.
     */
    private UserStore() {
    }

    /**
     * Получение объекта класса-синглтона.
     *
     * @return STORE (синглтон).
     */
    public static UserStore getInstance() {
        return STORE;
    }

    /**
     * Метод для добавления пользователя.
     *
     * @param name  имя пользователя.
     * @param login логин для входа.
     * @param email адрес электронной почты.
     * @param password пароль для входа.
     * @param roleID роль пользователя.
     * @return true если операция по добавлению пользователя прошла успешно.
     */
    public synchronized boolean addUser(String name, String login, String email, String password, int roleID) {
        int addResult = 0;
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users "
                + "(name, login, email, password, role_id) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setInt(5, roleID);
            addResult = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return addResult != 0;
    }

    /**
     * Метод для редактирования данных о пользователе.
     *
     * @param login    логин пользователя.
     * @param newName  имя для замены существующего.
     * @param newLogin логин для замены существующего.
     * @param newEmail адрес электронной почты для замены существующей.
     * @param newPassword пароль для замены существующего.
     * @param newRoleID роль пользователя для замены существующей.
     * @return число равное:
     * 1 если запись присутствет в базе данных и она успешно отредактирована,
     * 0 если запись присутствет в базе данных, но отредактировать её не удалось,
     * -1 если запись о пользователе не имеется в базе данных,
     * -2 если все аргументы (поля для редактирования) пустые.
     */
    public synchronized int updateUser(String login, String newName, String newLogin, String newEmail,
                                       String newPassword, int newRoleID) {
        int updateResult = 0;
        try (PreparedStatement fullStatement = connection
                .prepareStatement("UPDATE users SET name = ?, login = ?, email = ?, password = ?, "
                        + "role_id = ? WHERE login = ?");
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            connection.setAutoCommit(false);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (newName.isEmpty() && newLogin.isEmpty() && newEmail.isEmpty() && newPassword.isEmpty()
                        && newRoleID == 0) {
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
                    if (newPassword.isEmpty()) {
                        newPassword = resultSet.getString("password");
                    }
                    fullStatement.setString(1, newName);
                    fullStatement.setString(2, newLogin);
                    fullStatement.setString(3, newEmail);
                    fullStatement.setString(4, newPassword);
                    fullStatement.setInt(5, newRoleID);
                    fullStatement.setString(6, login);
                    updateResult = fullStatement.executeUpdate();
                    connection.commit();
                }
            } else {
                updateResult = -1;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                LOGGER.info("Сбой при обновлении пользователя. Попытка откатиться и снова его обновить");
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.info("Откат не удался");
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
     * Метод для удаления записи о пользователе из базы данных.
     *
     * @param login логин для входа.
     * @return число равное:
     * 1 (или > 1, но так как поле login в базе данных уникальное, то удаляется одна запись) если запись
     * присутствет в базе данных и она успешно удалена(),
     * 0 если запись присутствет в базе данных, но удалить её не удалось,
     * -1 если запись о пользователе не имеется в базе данных.
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
                LOGGER.info("Сбой при удалении пользователя. Попытка откатиться и снова его удалить");
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.info("Откат не удался");
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
     * Метод для получения данных о пользователе.
     * Если пользователя нет в БД, то возвращает User с установленным полем exist в false
     *
     * @param login логин пользователя.
     * @return данные о пользователе.
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
                user.setPassword(resultSet.getString("password"));
                user.setRoleID(resultSet.getInt("role_id"));
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
     * Проверка присутствия записи о пользователе в базе данных.
     *
     * @param login логин для проверки.
     * @return true если пользователь есть в базе.
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
     * Получение списка логинов всех пользователей.
     *
     * @return список логинов пользователей.
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
     * Соединение с базой данных. Используется пул соединений.
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
     * Закрытие соединения.
     */
    public void disconnectDB() {
        try {
            connection.close();
            if (connection.isClosed()) {
                LOGGER.info("Соединение с базой данных закрыто");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Проверка правильности логина и пароля.
     *
     * @param login    логин.
     * @param password пароль.
     * @return true если данные введены верно.
     */
    public boolean isCredential(String login, String password) {
        boolean exists = false;
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return exists;
    }

    /**
     * Получить название роли из базы данных для пользователя.
     *
     * @param login    логин.
     * @param password пароль.
     * @return имя роли.
     */
    public String getRole(String login, String password) {
        String role = "";
        try (PreparedStatement statement = connection
                .prepareStatement(
                        "SELECT role FROM users u INNER JOIN roles r ON u.role_id = r.id"
                                + " WHERE u.login = ? AND u.password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return role;
    }
}