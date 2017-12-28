package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет, отвечающий за создание пользователя.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 09.12.2017
 */
public class CreateUser extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная, хранящая объект-синглтон UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Метод для создания пользователя и отображения страницы с полями для создания нового пользователя.
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
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        out.println("<h2>Создание пользователя</h2>");
        out.println("<form method=POST><table><tr><td>Login</td><td><input type=text name=login ></td></tr>");
        out.println("<tr><td>Name</td><td><input type=text name=name ></td></tr>");
        out.println("<td>Email</td><td><input type=text name=email ></td></tr>");
        out.println("<tr><td colspan=2 align=center><input type=submit value=create></td></tr></table></form>");
        out.println("</center>");
        out.println("</body></html>");
    }

    /**
     * Метод отбражающий результат создания пользователя.
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
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        PrintWriter out = resp.getWriter();
        boolean addResult = users.addUser(name, login, email);
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        if (addResult) {
            out.println("Пользователь " + login + " создан");
        } else {
            out.println("Пользователь с именем " + login + " уже существует");
        }
        out.println("<br><br><a href=");
        out.println(req.getContextPath() + "/userservlet>К списку пользователей</a>");
        out.println("</center>");
        out.println("</body></html>");
    }
}