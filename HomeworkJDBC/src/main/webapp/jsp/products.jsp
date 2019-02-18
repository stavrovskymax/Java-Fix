<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please, add User!
    </div>
    <form method="post" action="/products">
        <label for="firstName">First name
            <input class="input-field" type="text" id="firstName" name="firstName">
        </label>
        <label for="lastName">Last name
            <input class="input-field" type="text" id="lastName" name="lastName">
        </label>
        <input type="submit" value="Add user">
    </form>
</div>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Already registered!
    </div>
    <table>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Model car</th>
        </tr>
        <c:forEach items="${usersFromServer}" var="user">
            <c:forEach items="${user.cars}" var="car">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${car.model}</td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
</div>
</body>
</html>
