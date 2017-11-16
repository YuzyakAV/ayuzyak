package ru.job4j.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс реализующий слушателя приложения. При старте приложения загружается драйвер базы данных
 * и устанавливается соединение с базой. Перед окончанием работы приложения соединение закрывается.
 * @author Ayuzyak
 * @version 1.0
 * @since 15.11.2017
 */
public class AppEvent implements ServletContextListener {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная для соединение с базой данных.
     */
    private Connection connection;

    /**
     * Загрузка драйвера базы данных и установление соединения с ней при старте.
     *
     * @param sce событие в приложении для слушателя.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver");
            ServletContext context = sce.getServletContext();
            String database = context.getInitParameter("database");
            String postgresUser = context.getInitParameter("postgresUser");
            String postgresPassword = context.getInitParameter("postgresPassword");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + database, postgresUser, postgresPassword);
            context.setAttribute("connection", connection);
            if (connection != null) {
                LOGGER.info("Соединение с базой данных установлено");
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Закрытие соединения при окончании работы сервера.
     *
     * @param sce событие в приложении для слушателя.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connection.close();
            if (connection.isClosed()) {
                LOGGER.info("Соединение с базой данных закрыто");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}