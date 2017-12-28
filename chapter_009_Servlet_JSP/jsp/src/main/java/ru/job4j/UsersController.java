package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ������� ������ ��������� ��������. �������� ������ �������������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 09.12.2017
 */
public class UsersController extends HttpServlet {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ����������, �������� ������-�������� UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * ��������� ������ �������������.
     *
     * @param req  ������.
     * @param resp �����.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> usersList = users.getAll();
        req.setAttribute("users", usersList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp");
        dispatcher.forward(req, resp);
    }
}