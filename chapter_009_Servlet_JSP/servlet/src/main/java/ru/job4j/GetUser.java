package ru.job4j;

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
 * Сервлет, отвечающий за отображение информации о пользователе.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 10.12.2017
 */
public class GetUser extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная, хранящая объект-синглтон UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Метод для получения иноформации о пользователе из базы данных и отображения ее на html-странице.
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
        String loginParametr = req.getParameter("login");
        User user = users.getUser(loginParametr);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        if (user.isExist()) {
            String name = user.getName();
            String login = user.getLogin();
            String email = user.getEmail();
            Calendar date = user.getCreateDate();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            StringBuilder builder = new StringBuilder();
            builder.append("<h2>Данные о пользователе</h2><h2>");
            builder.append(login);
            builder.append("</h2><table border=1 cellspacing=0 cellpadding=5><tr><td>Name</td><td>");
            builder.append(name);
            builder.append("</td></tr><tr><td>Email</td><td>");
            builder.append(email);
            builder.append("</td></tr><tr><td>Date of creation</td><td>");
            builder.append(sdf.format(date.getTime()));
            builder.append("</td></tr></table>");
            out.println(builder.toString());
        } else {
            out.println("Произошла ошибка. Пользователь не найден в базе");
        }
        out.println("<br><br><a href=");
        out.println(req.getContextPath() + "/userservlet>К списку пользователей</a>");
        out.println("</center>");
        out.println("</body></html>");
    }
}