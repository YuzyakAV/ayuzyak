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
 * �������, ���������� �� ��������� ������ ������� ���� ������������� � ����������� ����� ������, � ������������
 * ��������������, ��������, � ����� �������� ������ ������������.
 *
 * @author Ayzuyak
 * @version 1.0
 * @since 11.12.2017
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
     * ����� ���������� ��������� ������ ������� ������������� � ������������ ��������� ���������� � ���,
     * �������������� � ��������. ������� ������ � ���� html-��������.
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
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>");
        out.println("<h1>User's list</h1>");
        out.println("<table border=1 cellspacing=0 cellpadding=1>");
        out.println("<tr><th>�</th><th>User's login</th><th colspan=2>Action</th></tr>");
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
        out.println(req.getContextPath() + "/createuser>������� ������ ������������</a></h3>");
        out.println("</center></body></html>");
    }

    /**
     * �������� ���������� ��� ��������� ������ �������.
     */
    @Override
    public void destroy() {
        users.disconnectDB();
    }
}