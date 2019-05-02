<html>
<head>
    <title>Profile</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>

<div class="form-style-2-heading">${user.firstName} ${user.lastName}</div>

<div class="form-style-2">
    <table>
        <tr>
            <th>My car</th>
        </tr>
        <#list cars as car>
            <tr>
                <td>${car.model}</td>
            </tr>
        </#list>
    </table>
</div>
<div class="form-style-2">
    <input type="submit" value="Logout" onclick="location.href='/logout'">
</div>
</body>
</html>