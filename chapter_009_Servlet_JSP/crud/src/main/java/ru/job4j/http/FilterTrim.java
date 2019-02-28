package ru.job4j.http;

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
 * Êëàññ, îòñåêàþùèé ïðîáåëû â íà÷àëå è êîíöå ïîëåé äëÿ ââîäà äàííûõ íà web-ñòðàíèöàõ.
 * @author Ayuzyak
 * @version 1.0
 * @since 02.12.2017
 */
public class FilterTrim implements Filter {
    /**
     * Ëîããåð.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Ïåðåìåííàÿ äëÿ 
     */
    private FilterConfig filterConfig;

    /**
     * Ìåòîä äëÿ èíèöèàëèçàöèè ôèëüòðà.
     *
     * @param filterConfig íàñòðîéêè ôèëüòðà.
     * @throws ServletException .
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        LOGGER.info("Ôèëüòð ïî îáðåçêå ïðîáåëîâ â íà÷àëå è â êîíöå èíèöèàëèçèðîâàí");
    }

    /**
     * Îòñå÷åíèå ïðîáåëîâ â ïîëÿõ ôîðì, à òàêæå óñàòíîâêà ïóñòûõ çíà÷åíèé ñòðîê, åñëè ïîëå íå çàïîëíåíî.
     *
     * @param request  çàïðîñ.
     * @param response îòâåò.
     * @param chain    ïåðåìåííàÿ äëÿ ïåðåäà÷è îáðàáîòêè request è response ñëåäóþùåìó ôèëüòðó.
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
     * Çàïóñêàåòñÿ ïðè äåèíèöèàëèçàöèè ôèëüòðà.
     */
    @Override
    public void destroy() {
        LOGGER.info("Ôèëüòð ïî îáðåçêå ïðîáåëîâ â íà÷àëå è â êîíöå äåèíèöèàëèçèðîâàí");
        filterConfig = null;
    }
}
