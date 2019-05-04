<html>
<head>
    <title>Users</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>

<div class="form-style-2">
    <div class="form-style-2-heading">
        All registered users!
    </div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Model car</th>
        </tr>
        <#list usersFromServer as user>
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.model}</td>
            </tr>
        </#list>
    </table>
</div>
<div class="form-style-2">
    <input type="submit" value="Logout" onclick="location.href='/logout'">
</div>
</body>
</html>