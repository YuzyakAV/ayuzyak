<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update user</title>
    </head>
    <body>
        <div align="center">
            <h2>Редактировать пользователя ${login}</h2>
            <form action="updateuser" method=POST>
                <table>
                    <caption>Заполните поля, которые необходимо отредактировать</caption>
                    <tr>
                        <td>New login</td>
                        <td>
                            <input type=text name=newlogin >
                        </td>
                    </tr>
                    <tr>
                        <td>New name</td>
                        <td>
                            <input type=text name=name >
                        </td>
                    </tr>
                    <tr>
                        <td>New email</td>
                        <td>
                            <input type=text name=email >
                        </td>
                    </tr>
                    <input type=hidden name=login value=${login}>
                    <tr>
                        <td colspan=2 align=center>
                            <input type=submit value=update>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>