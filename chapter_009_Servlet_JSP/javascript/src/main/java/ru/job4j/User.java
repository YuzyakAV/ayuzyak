package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

/**
 * Класс с данными о пользователе.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 05.12.2017
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
     * Пароль пользователя.
     */
    private String password;

    /**
     * Адрес электронной почты пользователя.
     */
    private String email;

    /**
     * Роль пользователя.
     */
    private int roleID;

    /**
     * Город пользователя.
     */
    private String city;

    /**
     * Страна пользователя.
     */
    private String country;

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

    /**
     * Геттер для пароля.
     * @return пароль.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Сеттер для пароля.
     * @param password пароль пользователяю
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Геттер для роли.
     * @return роль.
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * Сеттер для роли.
     * @param roleID роль пользователя.
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    /**
     * Геттер для города.
     * @return город.
     */
    public String getCity() {
        return city;
    }

    /**
     * Сеттер для города.
     * @param city город.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Геттер для страны.
     * @return страна.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Сеттер для страны.
     * @param country страна.
     */
    public void setCountry(String country) {
        this.country = country;
    }
}