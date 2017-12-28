package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

/**
 * ����� � ������� � ������������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 06.12.2017
 */
public class User {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ��� ������������.
     */
    private String name;

    /**
     * ����� ������������.
     */
    private String login;

    /**
     * ������ ������������.
     */
    private String password;

    /**
     * ����� ����������� ����� ������������.
     */
    private String email;

    /**
     * ���� ������������.
     */
    private int roleID;

    /**
     * ���� �������� ������������.
     */
    private Calendar createDate;

    /**
     * ���������� ��� ����������� ���������� ������������ � ���� ������ ��� ���.
     */
    private boolean exist = true;

    /**
     * ������ ��� �����.
     *
     * @return ���.
     */
    public String getName() {
        return name;
    }

    /**
     * ������ ��� �����.
     *
     * @param name ��� ������������.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ������ ��� ������.
     *
     * @return �����.
     */
    public String getLogin() {
        return login;
    }

    /**
     * ������ ��� ������.
     *
     * @param login ����� ������������.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * ������ ��� ����������� �����.
     *
     * @return ����� �����.
     */
    public String getEmail() {
        return email;
    }

    /**
     * ������ ��� ������ �����.
     *
     * @param email ����� ����������� ����� ������������.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * ������ ��� ���� �������� ������ � ������������ � ���� ������.
     *
     * @return ���� ��������.
     */
    public Calendar getCreateDate() {
        return createDate;
    }

    /**
     * ������ ��� ���� ��������.
     *
     * @param createDate ���� ��������.
     */
    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    /**
     * ������ ��� ������������� ������ � ������������ � ���� ������.
     *
     * @return true ���� ������ � ������������ ���������.
     */
    public boolean isExist() {
        return exist;
    }

    /**
     * ������ ��� ������������� ������ � ������������ � ���� ������.
     *
     * @param exist ��� ��������� ������������� ������.
     */
    public void setExist(boolean exist) {
        this.exist = exist;
    }

    /**
     * ������ ��� ������.
     * @return ������.
     */
    public String getPassword() {
        return password;
    }

    /**
     * ������ ��� ������.
     * @param password ������ �������������
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * ������ ��� ����.
     * @return ����.
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * ������ ��� ����.
     * @param roleID ���� ������������.
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}