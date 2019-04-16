<html>
<head>
    <title>Products</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign Up!
    </div>
    <form method="post" action="/products">
        <label for="firstName">First name
            <input class="input-field" type="text" id="firstName" name="firstName">
        </label>
        <label for="lastName">Last name
            <input class="input-field" type="text" id="lastName" name="lastName">
        </label>
        <label for="model">Model car
            <input class="input-field" type="text" id="model" name="model">
        </label>
        <label for="login">Login
            <input class="input-field" type="text" id="login" name="login">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="Add user">
    </form>
    <#if errorLoginExists??>
        <div style="color: red">
            ${errorLoginExists}
        </div>
    </#if>
</div>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Already in System!
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