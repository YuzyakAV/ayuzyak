<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            </table>
            <br>
            <br>
            <jsp:include page="BackToList.jsp" />
        </div>
    </body>
</html>