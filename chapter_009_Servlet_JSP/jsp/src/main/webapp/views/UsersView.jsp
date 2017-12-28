<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>CRUD-JSP</title>
    </head>
    <body>
        <div align="center">
            <h1>User's list</h1>
            <table border=1 cellspacing=0 cellpadding=1>
                <tr>
                    <th>№</th>
                    <th>User's login</th>
                    <th colspan=2>Action</th>
                </tr>

                <c:forEach var="user" items="${users}" varStatus="loopCount" >
                    <tr>
                        <td>${loopCount.count}</td>
                        <td>
                            <a href=${pageContext.request.getContextPath()}/getuser?login=${user}>${user}</a>
                        </td>
                        <td>
                            <form action=updateuser method=GET>
                                <input type=hidden name=login value=${user}>
                                <input type=submit value=update>
                            </form>
                        </td>
                        <td>
                            <form action=deleteuser method=POST>
                                <input type=hidden name=login value=${user}>
                                <input type=submit value=delete>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <h3><a href=createuser>Создать нового пользователя</a></h3>
        </div>
    </body>
</html>