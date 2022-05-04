<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/coffee-style.css">
<head>
    <meta charset="UTF-8">
    <title>Coffee - Log in</title>
</head>
<body>
<div class="header">
    <h1>Log In to the Coffee!</h1>
</div>

<form method="post" action="coffee-servlet">
    <input name="action" value="login" style="visibility: hidden"/><br>
    <label for="login">Login:</label><br>
    <input name="login" id="login"/><br>
    <label for="password">Password:</label><br>
    <input name="password" type="password" id="password"/><br><br>
    <button class="big-button" type="submit">Log in!</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/register.jsp">Or you can register a new account</a>
<p class="footer">© Zmitser Horbach, 2022</p>
</body>
</html>