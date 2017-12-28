<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>User info</title>
    </head>
    <body>
        <div align="center">
            <h2>Данные о пользователе</h2>
            <h2>${login}</h2>
            <table border=1 cellspacing=0 cellpadding=5>
                <c:if test="${sessionScope.role eq 'admin'}">
                    <tr>
                        <td>Password</td>
                        <td>${password}</td>
                    </tr>
                </c:if>
                <tr>
                    <td>Name</td>
                    <td>${name}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${email}</td>
                </tr>
                <tr>
                    <td>Date of creation</td>
                    <td>${date}</td>
                </tr>
                <c:if test="${sessionScope.role eq 'admin'}">
                    <tr>
                        <td>Role</td>
                        <td>${requestScope.role}</td>
                    </tr>
                </c:if>
                <tr>
                    <td>City</td>
                    <td>${city}</td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td>${country}</td>
                </tr>
            </table>
            <br>
            <br>
            <jsp:include page="BackToList.jsp" />
        </div>
    </body>
</html>