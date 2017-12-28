package ru.job4j.cities;

import ru.job4j.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * �������, ������������ � ������� json ������ �����.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 12.12.2017
 */
public class CountryServlet extends HttpServlet {
    /**
     * ����� ����������� ������ � ����������� ������ �����.
     *
     * @param req  ������.
     * @param resp �����.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, String> countries = UserStore.getInstance().getAllCountries();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Map.Entry<Integer, String> pair : countries.entrySet()) {
            builder.append(parseToJson(pair.getKey(), pair.getValue()));
            builder.append(", ");
        }
        builder.delete(builder.lastIndexOf(","), builder.length());
        builder.append("]");
        String json = builder.toString();
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
    }

    /**
     * ������� ������ � JSON-������.
     *
     * @param id      ������.
     * @param country ������.
     * @return ������ � JSON-�������.
     */
    private String parseToJson(int id, String country) {
        return "{\"id\": " + id + ", \"country\": \"" + country + "\"}";
    }
}