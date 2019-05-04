<html>
<head>
    <title>Profile</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>

<div class="form-style-2">
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
    <div class="form-style-2-heading">
        Add car
    </div>
    <div class="form-style-2">
        <form method="post" action="/profile">
            <label for="model">Model
                <input class="input-field" type="text" id="model" name="model">
            </label>
            <input type="submit" value="Add car">
        </form>
    </div>
    <div class="form-style-2">
        <input type="submit" value="Logout" onclick="location.href='/logout'">
    </div>
</div>
</body>
</html>