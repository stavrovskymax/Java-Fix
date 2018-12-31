<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.ivmiit.models.User" %><%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 09.11.2018
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ArrayList<User> users =
            (ArrayList) request.getAttribute("usersFromServer");
%>
<table>
    <tr>
        <th>User name</th>
        <th>Birthday</th>
    </tr>
    <% for (User user : users) {
    %>
    <tr>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getBirthDay()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
