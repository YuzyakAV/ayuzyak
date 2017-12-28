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
 * ������� ������������ ������ � ����� ������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 04.12.2017
 */
public class UsersServlet extends HttpServlet {
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
     * ����� ���������� ��������� ������ � ������������, ������� ������ � ���� html-��������.
     *
     * @param req  ������.
     * @param resp �����.
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
                    + "\t\t\t\t<caption>������ � ������������</caption>\n"
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
            out.println("������������ �� ������ � ����");
            out.println("</center>");
        }
        out.println("</body></html>");
        LOGGER.info("������ ����� GET");
    }

    /**
     * ����� ��� �������� ������������. ������� ���������� � �������� � ���� html-��������.
     *
     * @param req  ������.
     * @param resp �����.
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
            out.println("������������ " + login + " ������");
        } else {
            out.println("������������ � ������ " + login + " ��� ����������");
        }
        out.println("</center>");
        out.println("</body></html>");
        LOGGER.info("������ ����� POST");
    }

    /**
     * ����� ��� �������������� ������ � ������������. ������ �������� ���������� � ��������������
     * � ���� html-��������.
     *
     * @param req  ������.
     * @param resp �����.
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
            out.println("������������ ��������");
        } else if (updateResult == 0) {
            out.println("��������� ���� � ������ ���� ������");
        } else if (updateResult == -1) {
            out.println("������������ �� ������ � ����");
        } else if (updateResult == -2) {
            out.println("�� ���� �� ����� ����������� �� ���� ��������");
        }
        out.println("</center>");
        out.println("</body></html>");
        LOGGER.info("������ ����� PUT");
    }

    /**
     * ����� ��� �������� ������ � ������������ �� ���� ������. ������ ������� ���������� �� ��������
     * � ���� html-��������.
     *
     * @param req  ������.
     * @param resp �����.
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
            out.println("������������ ������");
        } else if (deleteResult == 0) {
            out.println("��������� ���� � ������ ���� ������");
        } else {
            out.println("������������ �� ������ � ����");
        }
        out.println("</center>");
        out.println("</body></html>");
        LOGGER.info("������ ����� DELETE");
    }

    /**
     * �������� ���������� ��� ��������� ������ �������.
     */
    @Override
    public void destroy() {
        users.disConnectDB();
    }
}