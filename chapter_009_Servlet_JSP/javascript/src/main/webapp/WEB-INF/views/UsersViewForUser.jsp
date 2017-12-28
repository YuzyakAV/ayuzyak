<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Info for users</title>
    </head>
    <body>
        <div align="center">
            <h1>Hi ${sessionScope.login}</h1>
            <jsp:include page="Logout.jsp"/>
            <br>
            <form action="userselfupdate" method="get">
                Edit own profile <input type="submit" value="update">
            </form>
            <br>
            <table border=1 cellspacing=0 cellpadding=1>
                <tr>
                    <th>¹</th>
                    <th>User's login</th>
                </tr>

                <c:forEach var="user" items="${users}" varStatus="loopCount" >
                    <c:if test="${sessionScope.login ne user}">
                        <tr>
                            <td>${loopCount.count}</td>
                            <td>
                                <a href=${pageContext.request.getContextPath()}/getuser?login=${user}>${user}</a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </body>
</html>