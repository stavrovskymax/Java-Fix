<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Sign Up!
    </div>
    <form method="post" action="/signup">
        <label for="name">User name
            <input class="input-field" type="text" name="name" id="name">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" name="password" id="password">
        </label>
        <label for="birthday">Birthday
            <input class="input-field" type="text" name="birthday" id="birthday">
        </label>
        <input type="submit" value="SignUp">
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
                <td>${user.birthday}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
