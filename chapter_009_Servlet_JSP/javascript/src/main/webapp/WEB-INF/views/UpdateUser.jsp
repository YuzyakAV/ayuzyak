<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Self Update User</title>
    </head>
    <body>
        <div align="center">
            <h2>${sessionScope.login} здесь вы можете редактировать свой профиль</h2>
            <form name="createForm" action="userselfupdate" method=POST>
                <table>
                    <caption>Заполните поля, которые необходимо отредактировать</caption>
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
                            <input type="text" name="email" >
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
                    <input type="hidden" name="login" value=${sessionScope.login}>
                    <tr>
                        <td colspan=2 align="center">
                            <input type="submit" value="update">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <script src="scripts/create.js"></script>
    </body>
</html>