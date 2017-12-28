<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <div align="center">
            <c:if test="${error == 'Credential error'}">
                <div style="background: red">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <h2>Input login and password</h2>
            <form action=${pageContext.servletContext.getContextPath()}/signin method="post">
                <table border=1 cellspacing=0 cellpadding=1>
                    <tr>
                        <td>
                            Login
                        </td>
                        <td>
                            <input type="text" name="login">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password
                        </td>
                        <td>
                            <input type="password" name="password">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>