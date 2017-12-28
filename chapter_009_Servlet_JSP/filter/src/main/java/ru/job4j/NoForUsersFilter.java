package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ������ ��� ���������� ������� � ��������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 06.12.2017
 */
public class NoForUsersFilter implements Filter {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ���������� ��� ���������� �������� �������.
     */
    private FilterConfig filterConfig;

    /**
     * ������������� ������� � ����������� ��������.
     *
     * @param filterConfig .
     * @throws ServletException .
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * ���� ������������ �� ����� ���� ������� � �������, ��� ��������� 403 ������.
     *
     * @param request  ������.
     * @param response �����.
     * @param chain    �������.
     * @throws IOException      .
     * @throws ServletException .
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (role != null && role.equals("user")) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Destroy().
     */
    @Override
    public void destroy() {

    }
}