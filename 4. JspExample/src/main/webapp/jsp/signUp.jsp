<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.ivmiit.models.User" %><%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 09.11.2018
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <th>User name</th>
        <th>Birthday</th>
    </tr>
    <c:forEach items="${usersFromServer}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.birthDay}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
