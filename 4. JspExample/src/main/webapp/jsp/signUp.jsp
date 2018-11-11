<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.ivmiit.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please, Sign Up!
    </div>
    <form method="post" action="/signup">
        <label for="name">User name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <label for="birthday">Birthday
            <input class="input-field" type="text" id="birthday" name="birthday">
        </label>
        <input type="submit" value="Sign Up">
    </form>
</div>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Already registered!
    </div>
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
</div>
</body>
</html>
