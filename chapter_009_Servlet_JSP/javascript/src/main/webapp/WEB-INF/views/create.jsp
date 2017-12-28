<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>CRUD create</title>
        <link rel="stylesheet" type="text/css" href="styles/create.css">
    </head>
    <body>
        <div align="center">
            <h2>Создание пользователя</h2>
            <form name="createForm" action=createuser method="POST" onsubmit="return validateForm()">
                <table>
                    <tr>
                        <td>Login</td>
                        <td>
                            <input type="text" name="login" required >
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="text" name="password" required >
                        </td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td>
                            <input type="text" name="name" >
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>
                            <input type="email" name="email" >
                        </td>
                    </tr>
                    <tr>
                        <td>Role</td>
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
                            <select name="countries" id="country" required>
                                <option disabled selected value> -- select -- </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>
                            <select name="cities" id="city" required>
                                <option disabled selected value> -- select -- </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="create">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <script src="scripts/create.js"></script>
    </body>
</html>