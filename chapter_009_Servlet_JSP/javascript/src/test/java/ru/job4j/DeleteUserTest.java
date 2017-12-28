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
 * Тестирование сервлета для удаления пользователя.
 */
public class DeleteUserTest {
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
     * Тестирование удаления информации о пользователе.
     *
     * @throws ServletException .
     * @throws IOException      .
     */
    @Test
    public void deleteUser() throws ServletException, IOException {
        DeleteUser delete = new DeleteUser();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String login = "loginTest";
        String name = "nameTest";
        String email = "emailTest";
        String password = "passwordTest";
        int cityID = 1;
        int role = 2;

        UserStore.getInstance().addUser(name, login, email, password, role, cityID);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getRequestDispatcher("/WEB-INF/views/successdelete.jsp"))
                .thenReturn(mock(RequestDispatcher.class));

        delete.doPost(request, response);

        User user = UserStore.getInstance().getUser(login);

        assertNull(user.getLogin());
        assertNull(user.getName());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertFalse(user.isExist());

        verify(request, atLeastOnce()).getParameter("login");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/views/successdelete.jsp");
    }
}