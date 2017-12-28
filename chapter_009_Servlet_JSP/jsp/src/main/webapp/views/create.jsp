<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>CRUD create</title>
    </head>
    <body>
        <div align="center">
            <h2>Создание пользователя</h2>
            <form action=createuser method=POST>
                <table>
                    <tr>
                        <td>Login</td>
                        <td>
                            <input type=text name=login >
                        </td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td>
                            <input type=text name=name >
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>
                            <input type=text name=email >
                        </td>
                    </tr>
                    <tr>
                        <td colspan=2 align=center>
                            <input type=submit value=create>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>