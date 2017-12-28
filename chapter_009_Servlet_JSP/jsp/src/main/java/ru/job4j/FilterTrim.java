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
 * Класс, отсекающий пробелы в начале и конце полей для ввода данных на web-страницах.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 08.12.2017
 */
public class FilterTrim implements Filter {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная для сохранения настроек фильтра.
     */
    private FilterConfig filterConfig;

    /**
     * Метод для инициализации фильтра.
     *
     * @param filterConfig настройки фильтра.
     * @throws ServletException .
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        LOGGER.info("Фильтр по обрезке пробелов в начале и в конце инициализирован");
    }

    /**
     * Отсечение пробелов в полях форм, а также усатновка пустых значений строк, если поле не заполнено.
     *
     * @param request  запрос.
     * @param response ответ.
     * @param chain    переменная для передачи обработки request и response следующему фильтру.
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
     * Запускается при деинициализации фильтра.
     */
    @Override
    public void destroy() {
        LOGGER.info("Фильтр по обрезке пробелов в начале и в конце деинициализирован");
        filterConfig = null;
    }
}