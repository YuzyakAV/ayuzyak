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
 * Тестирование сервлета для обновления админом информации о пользователе.
 */
public class UpdateUserTest {
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
     * Тестирование обновления админом информации о пользователе.
     *
     * @throws ServletException .
     * @throws IOException      .
     */
    @Test
    public void updateUser() throws ServletException, IOException {
        UpdateUser update = new UpdateUser();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String login = "loginTest";
        String name = "nameTest";
        String email = "emailTest";
        String password = "passwordTest";
        int role = 2;
        int cityID = 2;

        UserStore.getInstance().addUser(name, login, email, password, role, cityID);

        String newLogin = "newLoginTest";
        String newName = "newNameTest";
        String newEmail = "newEmailTest";
        String newPassword = "newPasswordTest";
        String newRole = "1";
        String newCityId = "1";

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("name")).thenReturn(newName);
        when(request.getParameter("email")).thenReturn(newEmail);
        when(request.getParameter("newlogin")).thenReturn(newLogin);
        when(request.getParameter("password")).thenReturn(newPassword);
        when(request.getParameter("roles")).thenReturn(newRole);
        when(request.getParameter("cities")).thenReturn(newCityId);
        when(request.getRequestDispatcher("/WEB-INF/views/successupdate.jsp"))
                .thenReturn(mock(RequestDispatcher.class));

        update.doPost(request, response);

        User user = UserStore.getInstance().getUser(newLogin);

        assertEquals(user.getLogin(), newLogin);
        assertEquals(user.getName(), newName);
        assertEquals(user.getEmail(), newEmail);
        assertEquals(user.getPassword(), newPassword);
        assertEquals(user.getRoleID(), Integer.parseInt(newRole));
        assertEquals(user.getCity(), "Moscow");

        verify(request, atLeastOnce()).getParameter("login");
        verify(request, atLeastOnce()).getParameter("name");
        verify(request, atLeastOnce()).getParameter("email");
        verify(request, atLeastOnce()).getParameter("newlogin");
        verify(request, atLeastOnce()).getParameter("password");
        verify(request, atLeastOnce()).getParameter("roles");
        verify(request, atLeastOnce()).getParameter("cities");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/views/successupdate.jsp");

        UserStore.getInstance().deleteUser(newLogin);
    }
}