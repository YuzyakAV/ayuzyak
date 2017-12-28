package ru.job4j;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Calendar;
import java.util.Enumeration;

/**
 * Класс с данными о пользователе.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 10.12.2017
 */
public class User {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Логин пользователя.
     */
    private String login;

    /**
     * Адрес электронной почты пользователя.
     */
    private String email;

    /**
     * Дата создания пользователя.
     */
    private Calendar createDate;

    /**
     * Переменная для отображения существует пользователь в базе данных или нет.
     */
    private boolean exist = true;

    /**
     * Геттер для имени.
     *
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для имени.
     *
     * @param name имя пользователя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер для логина.
     *
     * @return логин.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Сеттер для логина.
     *
     * @param login логин пользователя.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Геттер для электронной почты.
     *
     * @return адрес почты.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Сеттер для адреса почты.
     *
     * @param email адрес электронной почты пользователя.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Геттер для даты создания записи о пользователе в базе данных.
     *
     * @return дата создания.
     */
    public Calendar getCreateDate() {
        return createDate;
    }

    /**
     * Сеттер для даты создания.
     *
     * @param createDate дата создания.
     */
    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    /**
     * Геттер для существования записи о пользователе в базе данных.
     *
     * @return true если запись о пользователе суествует.
     */
    public boolean isExist() {
        return exist;
    }

    /**
     * Сеттер для существования записи о пользователе в базе данных.
     *
     * @param exist для установки существования записи.
     */
    public void setExist(boolean exist) {
        this.exist = exist;
    }
}