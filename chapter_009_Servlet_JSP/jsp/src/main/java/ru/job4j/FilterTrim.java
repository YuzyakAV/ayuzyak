package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * �����, ���������� ������� � ������ � ����� ����� ��� ����� ������ �� web-���������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 08.12.2017
 */
public class FilterTrim implements Filter {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ���������� ��� ���������� �������� �������.
     */
    private FilterConfig filterConfig;

    /**
     * ����� ��� ������������� �������.
     *
     * @param filterConfig ��������� �������.
     * @throws ServletException .
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        LOGGER.info("������ �� ������� �������� � ������ � � ����� ���������������");
    }

    /**
     * ��������� �������� � ����� ����, � ����� ��������� ������ �������� �����, ���� ���� �� ���������.
     *
     * @param request  ������.
     * @param response �����.
     * @param chain    ���������� ��� �������� ��������� request � response ���������� �������.
     * @throws IOException      .
     * @throws ServletException .
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String newLogin = request.getParameter("newlogin");
        String email = request.getParameter("email");
        if (name != null) {
            request.setAttribute("name", name.trim());
        } else {
            request.setAttribute("name", "");
        }
        if (login != null) {
            request.setAttribute("login", login.trim());
        } else {
            request.setAttribute("login", "");
        }
        if (newLogin != null) {
            request.setAttribute("newlogin", newLogin.trim());
        } else {
            request.setAttribute("newlogin", "");
        }
        if (email != null) {
            request.setAttribute("email", email.trim());
        } else {
            request.setAttribute("email", "");
        }
        chain.doFilter(request, response);
    }

    /**
     * ����������� ��� ��������������� �������.
     */
    @Override
    public void destroy() {
        LOGGER.info("������ �� ������� �������� � ������ � � ����� �����������������");
        filterConfig = null;
    }
}