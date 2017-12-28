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
 * Фильтр, отвечающий за аутентификацию пользователя.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 08.12.2017
 */
public class AuthenticateFilter implements Filter {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная для сохранения настроек фильтра.
     */
    private FilterConfig filterConfig;

    /**
     * Методы, вызывающийся при загрузки сервлета.
     *
     * @param filterConfig .
     * @throws ServletException .
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * Метод, определяющий авторизован ли пользователь.
     *
     * @param request  запрос.
     * @param response ответ.
     * @param chain    цепочка.
     * @throws IOException      .
     * @throws ServletException .
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!req.getRequestURI().contains("/signin")) {
            HttpSession session = req.getSession();
            HttpServletResponse res = (HttpServletResponse) response;
            if (session.getAttribute("login") == null) {
                res.sendRedirect(String.format("%s/signin", req.getContextPath()));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * destroy().
     */
    @Override
    public void destroy() {

    }
}