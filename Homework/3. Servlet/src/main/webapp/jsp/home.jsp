<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<span style="color:${cookie.color.value}">Hello</span>
<form method="post" action="/home">
    <label for="color">
        <select name="color" id="color">
            <option value="Red">Red</option>
            <option value="Black">Black</option>
            <option value="White">White</option>
        </select>
    </label>
    <input type="submit" value="Color send">
</form>
</body>
</html>
