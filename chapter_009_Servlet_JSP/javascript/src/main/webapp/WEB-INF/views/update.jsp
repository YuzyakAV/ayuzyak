<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update user</title>
    </head>
    <body>
        <div align="center">
            <h2>Редактировать пользователя ${login}</h2>
            <form name="createForm" action="updateuser" method="POST">
                <table>
                    <caption>Заполните поля, которые необходимо отредактировать</caption>
                    <tr>
                        <td>New login</td>
                        <td>
                            <input type="text" name="newlogin" >
                        </td>
                    </tr>
                    <tr>
                        <td>New password</td>
                        <td>
                            <input type="text" name="password" >
                        </td>
                    </tr>
                    <tr>
                        <td>New name</td>
                        <td>
                            <input type="text" name="name" >
                        </td>
                    </tr>
                    <tr>
                        <td>New email</td>
                        <td>
                            <input type="email" name="email" >
                        </td>
                    </tr>
                    <tr>
                        <td>New role</td>
                        <td>
                            <select name="roles">
                                <option value="1">Admin</option>
                                <option value="2" selected>User</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td>
                            <select name="countries" id="country">
                                <option disabled selected value> -- select -- </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>
                            <select name="cities" id="city">
                                <option disabled selected value> -- select -- </option>
                            </select>
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
        <script src="scripts/create.js"></script>
    </body>
</html>