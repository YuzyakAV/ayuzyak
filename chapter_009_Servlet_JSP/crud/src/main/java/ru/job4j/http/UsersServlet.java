package ru.job4j.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Сервлет отображающий работу с базой данных.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 04.12.2017
 */
public class UsersServlet extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная, хранящая объект-синглтон UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Метод, выполняющийся при инициализации сервлета и устанавливающий в объекте users соединение с базой данных.
     *
     * @throws ServletException .
     */
    @Override
    public void init() throws ServletException {
        users.connectDB();
    }

    /**
     * Метод релизующий получение данных о пользователя, выводит данные в виде html-страницы.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String loginParametr = (String) req.getAttribute("login");
        User user = users.getUser(loginParametr);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        if (user.isExist()) {
            String name = user.getName();
            String login = user.getLogin();
            String email = user.getEmail();
            Calendar date = user.getCreateDate();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");

            out.println("<center>\n"
                    + "\t\t\t<table>\n"
                    + "\t\t\t\t<caption>Данные о пользователе</caption>\n"
                    + "\t\t\t\t\t<tr>\n"
                    + "\t\t\t\t\t\t<td>Name</td>\n"
                    + "\t\t\t\t\t\t<td>" + name + "</td>\n"
                    + "\t\t\t\t\t</tr>\n"
                    + "\t\t\t\t\t<tr>\n"
                    + "\t\t\t\t\t\t<td>Login</td>\n"
                    + "\t\t\t\t\t\t<td>" + login + "</td>\n"
                    + "\t\t\t\t\t</tr>\n"
                    + "\t\t\t\t\t<tr>\n"
                    + "\t\t\t\t\t\t<td>Email</td>\n"
                    + "\t\t\t\t\t\t<td>" + email + "</td>\n"
                    + "\t\t\t\t\t</tr>\n"
                    + "\t\t\t\t\t<tr>\n"
                    + "\t\t\t\t\t\t<td>Date of creation</td>\n"
                    + "\t\t\t\t\t\t<td>" + sdf.format(date.getTime()) + "</td>\n"
                    + "\t\t\t\t\t</tr>\n"
                    + "\t\t\t</table>\n"
                    + "\t\t</center>");
        } else {
            out.println("<center>");
            out.println("Пользователь не найден в базе");
            out.println("</center>");
        }
        out.println("</body></html>");
        LOGGER.info("Вызван метод GET");
    }

    /**
     * Метод для создания пользователя. Выводит информацию о создании в виде html-страницы.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String name = (String) req.getAttribute("name");
        String login = (String) req.getAttribute("login");
        String email = (String) req.getAttribute("email");
        PrintWriter out = resp.getWriter();
        boolean addResult = users.addUser(name, login, email);
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        if (addResult) {
            out.println("Пользователь " + login + " создан");
        } else {
            out.println("Пользователь с именем " + login + " уже существует");
        }
        out.println("</center>");
        out.println("</body></html>");
        LOGGER.info("Вызван метод POST");
    }

    /**
     * Метод для редактирования данных о пользователе. Должен выводить информацию о редактировании
     * в виде html-страницы.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String login = (String) req.getAttribute("login");
        String name = (String) req.getAttribute("name");
        String newLogin = (String) req.getAttribute("newlogin");
        String email = (String) req.getAttribute("email");
        int updateResult = users.updateUser(login, name, newLogin, email);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        if (updateResult > 0) {
            out.println("Пользователь обновлен");
        } else if (updateResult == 0) {
            out.println("Произошел сбой в работе базы данных");
        } else if (updateResult == -1) {
            out.println("Пользователь не найден в базе");
        } else if (updateResult == -2) {
            out.println("Ни одно из полей пользвателя не было изменено");
        }
        out.println("</center>");
        out.println("</body></html>");
        LOGGER.info("Вызван метод PUT");
    }

    /**
     * Метод для удаления записи о пользователе из базы данных. Должен выводит информацию об удалении
     * в виде html-страницы.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String login = (String) req.getAttribute("login");
        PrintWriter out = resp.getWriter();
        int deleteResult = users.deleteUser(login);
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        if (deleteResult > 0) {
            out.println("Пользователь удален");
        } else if (deleteResult == 0) {
            out.println("Произошел сбой в работе базы данных");
        } else {
            out.println("Пользователь не найден в базе");
        }
        out.println("</center>");
        out.println("</body></html>");
        LOGGER.info("Вызван метод DELETE");
    }

    /**
     * Закрытие соединения при окончании работы сервера.
     */
    @Override
    public void destroy() {
        users.disConnectDB();
    }
}