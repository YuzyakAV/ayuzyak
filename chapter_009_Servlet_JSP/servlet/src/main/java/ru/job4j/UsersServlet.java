package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет, отвечающий за получение списка логинов всех пользователей и отображение этого списка, с возможностью
 * редактирования, удаления, а также создания нового пользователя.
 *
 * @author Ayzuyak
 * @version 1.0
 * @since 11.12.2017
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
     * Метод релизующий получение списка логинов пользователей с возможностью получения информации о них,
     * редактирования и удаления. Выводит данные в виде html-страницы.
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
        out.println("<h1>User's list</h1>");
        out.println("<table border=1 cellspacing=0 cellpadding=1>");
        out.println("<tr><th>№</th><th>User's login</th><th colspan=2>Action</th></tr>");
        List<String> usersList = users.getAll();
        for (int i = 0; i < usersList.size(); i++) {
            StringBuilder builder = new StringBuilder();
            String login = usersList.get(i);
            builder.append("<tr><td>");
            builder.append(String.valueOf(i + 1));
            builder.append("</td><td>");
            builder.append("<a href=");
            builder.append(req.getContextPath());
            builder.append("/getuser?login=");
            builder.append(login);
            builder.append(">");
            builder.append(login);
            builder.append("</a></td><td>");
            builder.append("<form action=updateuser method=GET><input type=hidden name=login value=");
            builder.append(login);
            builder.append("><input type=submit value=update></form></td><td>");
            builder.append("<form action=deleteuser method=POST><input type=hidden name=login value=");
            builder.append(login);
            builder.append("><input type=submit value=delete></form></td></tr>");
            out.println(builder.toString());
        }
        out.println("</table>");
        out.println("<br><h3><a href=");
        out.println(req.getContextPath() + "/createuser>Создать нового пользователя</a></h3>");
        out.println("</center></body></html>");
    }

    /**
     * Закрытие соединения при окончании работы сервера.
     */
    @Override
    public void destroy() {
        users.disconnectDB();
    }
}