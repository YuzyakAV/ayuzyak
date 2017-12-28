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
 * �������, ���������� �� �������� ������������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 09.12.2017
 */
public class DeleteUser extends HttpServlet {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ����������, �������� ������-�������� UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * ����� ��� �������� ������������ � ����������� ����������� ��������.
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
        PrintWriter out = resp.getWriter();
        int deleteResult = users.deleteUser(login);
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        if (deleteResult > 0) {
            out.println("������������ ������");
        } else if (deleteResult == 0) {
            out.println("��������� ���� � ������ ���� ������");
        } else {
            out.println("��������� ������. ������������ �� ������ � ����");
        }
        out.println("<br><br><a href=");
        out.println(req.getContextPath() + "/userservlet>� ������ �������������</a>");
        out.println("</center>");
        out.println("</body></html>");
    }
}