<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href=
   <c:choose>
       <c:when test="${pageContext.request.getContextPath().isEmpty() == 'true' }">
               /
        </c:when>
        <c:otherwise>
            ${pageContext.request.getContextPath()}
        </c:otherwise>
    </c:choose>
>К списку пользователей</a>