package ru.job4j.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Мой первый сервлет.
 */
public class EchoServlet extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переопределенный метод doGet().
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