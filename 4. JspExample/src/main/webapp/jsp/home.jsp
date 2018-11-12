<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12.11.2018
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span style="color: #00CC00">Hello</span>
<form method="post" action="/home">
    <label for="color">
        <select name="color" id="color">
            <option value="red">Red</option>
            <option value="black">Black</option>
            <option value="white">White</option>
        </select>
    </label>
    <input type="submit" value="Color send">
</form>
</body>
</html>
