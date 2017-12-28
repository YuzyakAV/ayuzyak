package ru.job4j;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Тестирование сервлета для получения информации о пользователе.
 */
public class GetUserTest {
    /**
     * Соединение с бд.
     */
    @BeforeClass
    public static void connect() {
        UserStore.getInstance().connectDB();
    }

    /**
     * Закрытие соединения с бд.
     */
    @AfterClass
    public static void disconnect() {
        UserStore.getInstance().disconnectDB();
    }

    /**
     * Тестирование получения информации пользователе.
     *
     * @throws ServletException .
     * @throws IOException      .
     */
    @Test
    public void getUser() throws ServletException, IOException {
        GetUser userGet = new GetUser();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String login = "loginTest";
        String name = "nameTest";
        String email = "emailTest";
        String password = "passwordTest";
        int role = 2;
        int cityID = 2;

        UserStore.getInstance().addUser(name, login, email, password, role, cityID);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getRequestDispatcher("/WEB-INF/views/userinfo.jsp"))
                .thenReturn(mock(RequestDispatcher.class));

        userGet.doGet(request, response);

        User user = UserStore.getInstance().getUser(login);

        assertEquals(name, user.getName());
        assertEquals(login, user.getLogin());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRoleID());
        assertEquals("Belgorod", user.getCity());

        verify(request, atLeastOnce()).getParameter("login");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/views/userinfo.jsp");

        UserStore.getInstance().deleteUser(login);
    }
}