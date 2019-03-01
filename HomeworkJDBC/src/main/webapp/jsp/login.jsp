<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <form method="post" action="/login">
        <div class="form-style-2-heading">
            Sign in, please!
        </div>
        <label for="login">Login
            <input class="input-field" type="text" id="login" name="login">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <p><font color="red"><c:out value="${requestScope.errorLoginOrPasswordIncorrect}"></c:out></font></p>
        <input type="submit" value="Sign in">
    </form>
</div>
</body>
</html>