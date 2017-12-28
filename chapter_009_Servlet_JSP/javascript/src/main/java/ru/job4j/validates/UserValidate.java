package ru.job4j.validates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет, проверяющий присутствие логина в базе данных.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 13.12.2017
 */
public class UserValidate extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Проверяет присутствие логина в базе данных и отправка результата.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("checklogin");
        boolean userExist = UserStore.getInstance().validateUser(login);
        String json = "{\"exist\": " + userExist + "}";
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
    }
}