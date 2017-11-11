package ru.job4j.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * ��� ������ �������.
 */
public class EchoServlet extends HttpServlet {
    /**
     * ������.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServlet.class);

    /**
     * ���������������� ����� doGet().
     *
     * @param req request.
     * @param resp response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("Hello World!!!");
        out.write("<br>" + new Date());
    }
}