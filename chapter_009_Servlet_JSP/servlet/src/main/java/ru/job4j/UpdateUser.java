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
 * �������, ���������� �� ���������� ���������� � ������������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 10.12.2017
 */
public class UpdateUser extends HttpServlet {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ����������, �������� ������-�������� UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * ����� ��� ����������� �������������� ���������� � ������������.
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
        String login = req.getParameter("login");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        out.println("<h2>������������� ������������ ");
        out.println(login);
        out.println("</h2><form method=POST><table>");
        out.println("<caption>��������� ����, ������� ���������� ���������������</caption><tr><td>New login</td>");
        out.println("<td><input type=text name=newlogin ></td></tr>");
        out.println("<tr><td>New name</td><td><input type=text name=name ></td></tr>");
        out.println("<tr><td>New email</td><td><input type=text name=email ></td></tr>");
        out.println("<input type=hidden name=login value=");
        out.println(login);
        out.println("><tr><td colspan=2 align=center><input type=submit value=update></td></tr>");
        out.println("<tr><td><br></td></tr></table></form>");
        out.println("</center>");
        out.println("</body></html>");
    }

    /**
     * ����� ��� ����������� ���������� ���������� ���������� � ������������.
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
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String newLogin = req.getParameter("newlogin");
        String email = req.getParameter("email");
        int updateResult = users.updateUser(login, name, newLogin, email);
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        if (updateResult > 0) {
            out.println("���������� � ������������ ���������");
        } else if (updateResult == 0) {
            out.println("��������� ���� � ������ ���� ������");
        } else if (updateResult == -1) {
            out.println("������������ �� ������ � ����");
        } else if (updateResult == -2) {
            out.println("�� ���� �� ����� ����������� �� ���� ��������");
        }
        out.println("<br><br><a href=");
        out.println(req.getContextPath() + "/userservlet>� ������ �������������</a>");
        out.println("</center>");
        out.println("</body></html>");
    }
}