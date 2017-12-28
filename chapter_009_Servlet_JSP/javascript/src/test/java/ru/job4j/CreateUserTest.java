package ru.job4j;

import org.junit.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Тестирование сервлета для создания пользователя.
 */
public class CreateUserTest {
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
     * Тестирование добавления пользоваетля.
     *
     * @throws ServletException .
     * @throws IOException      .
     */
    @Test
    public void addUser() throws ServletException, IOException {
        CreateUser createUser = new CreateUser();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String login = "loginTest";

        when(request.getParameter("name")).thenReturn("nameTest");
        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("email")).thenReturn("email@test.ru");
        when(request.getParameter("password")).thenReturn("passwordTest");
        when(request.getParameter("roles")).thenReturn("2");
        when(request.getParameter("cities")).thenReturn("2");
        when(request.getRequestDispatcher("/WEB-INF/views/successcreate.jsp"))
                .thenReturn(mock(RequestDispatcher.class));

        createUser.doPost(request, response);

        User user = UserStore.getInstance().getUser(login);
        assertThat(login, is(user.getLogin()));
        assertThat(user.getCity(), is("Belgorod"));
        assertThat(user.getName(), is("nameTest"));

        verify(request, atLeastOnce()).getParameter("name");
        verify(request, atLeastOnce()).getParameter("login");
        verify(request, atLeastOnce()).getParameter("email");
        verify(request, atLeastOnce()).getParameter("password");
        verify(request, atLeastOnce()).getParameter("roles");
        verify(request, atLeastOnce()).getParameter("cities");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/views/successcreate.jsp");

        UserStore.getInstance().deleteUser(login);
    }
}