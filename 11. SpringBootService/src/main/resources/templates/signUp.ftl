<html>
<head>
    <title>Sign Up</title>
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign Up!
    </div>
    <form method="post" action="/signup">
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
</div>
</body>
</html>