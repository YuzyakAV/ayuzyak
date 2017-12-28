package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Сервлет, отвечающий за отображение информации о пользователе.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 05.12.2017
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
        if (user.isExist()) {
            String name = user.getName();
            String login = user.getLogin();
            String email = user.getEmail();
            String password = user.getPassword();
            String role = users.getRole(login, password);
            Calendar calendar = user.getCreateDate();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            String date = sdf.format(calendar.getTime());
            req.setAttribute("name", name);
            req.setAttribute("login", login);
            req.setAttribute("email", email);
            req.setAttribute("date", date);
            req.setAttribute("password", password);
            req.setAttribute("role", role);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/userinfo.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/notfound.jsp");
            dispatcher.forward(req, resp);
        }
    }
}