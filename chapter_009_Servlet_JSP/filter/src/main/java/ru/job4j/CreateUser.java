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
 * �������, ���������� �� �������� ������������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 05.12.2017
 */
public class CreateUser extends HttpServlet {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ����������, �������� ������-�������� UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * �����, ������������� ��� ������������� �������� � ��������������� � ������� users ���������� � ����� ������.
     *
     * @throws ServletException .
     */
    @Override
    public void init() throws ServletException {
        users.connectDB();
    }

    /**
     * ����� ��� �������� ������������.
     *
     * @param req  ������.
     * @param resp �����.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = new String(req.getParameter("name")
                .getBytes("iso-8859-1"), "utf-8");
        String login = new String(req.getParameter("login")
                .getBytes("iso-8859-1"), "utf-8");
        String email = new String(req.getParameter("email")
                .getBytes("iso-8859-1"), "utf-8");
        String password = new String(req.getParameter("password")
                .getBytes("iso-8859-1"), "utf-8");
        int role = Integer.parseInt(req.getParameter("roles"));
        boolean addResult = users.addUser(name, login, email, password, role);
        if (addResult) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/successcreate.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/failcreate.jsp");
            dispatcher.forward(req, resp);
        }
    }

    /**
     * ����� ��� ��������������� ������� �� �������� �������� ������������.
     *
     * @param req  ������.
     * @param resp �����.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/create.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * �������� ���������� ��� ��������� ������ �������.
     */
    @Override
    public void destroy() {
        users.disconnectDB();
    }
}