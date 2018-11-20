<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Sign Up!
    </div>
    <form method="post" action="/login">
        <label for="name">User name
            <input class="input-field" type="text" name="name" id="name">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" name="password" id="password">
        </label>
        <input type="submit" value="SignUp">
    </form>
</div>
</body>
</html>
