package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, отвечающий за удаление пользователя.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 06.12.2017
 */
public class DeleteUser extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Метод для удаления пользователя.
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
        String login = new String(req.getParameter("login")
                .getBytes("iso-8859-1"), "utf-8");
        int deleteResult = UserStore.getInstance().deleteUser(login);
        if (deleteResult > 0) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/successdelete.jsp");
            dispatcher.forward(req, resp);
        } else if (deleteResult == 0) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dbcrash.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/notfound.jsp");
            dispatcher.forward(req, resp);
        }
    }
}