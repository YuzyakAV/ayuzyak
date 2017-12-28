package ru.job4j.cities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * �������, ������������ � ������� json ������ �������.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 12.12.2017
 */
public class CityServlet extends HttpServlet {
    /**
     * ������.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * ��������� �������� id ������, � �� ���� ��������� ������ �������, ��������������� ������ ������.
     *
     * @param req  ������.
     * @param resp �����.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cityID = Integer.parseInt(req.getParameter("cities"));
        Map<Integer, String> countries = UserStore.getInstance().getCitiesOfCountry(cityID);
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
     * ������� ����� � JSON-������.
     *
     * @param id   �����.
     * @param city �����.
     * @return ������ � JSON-�������.
     */
    private String parseToJson(int id, String city) {
        return "{\"id\": " + id + ", \"city\": \"" + city + "\"}";
    }
}